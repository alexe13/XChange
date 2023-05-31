package org.knowm.xchange.poloniex2.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Mac;

import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

public class PoloniexDigest extends BaseParamsDigest {

    /**
     * Constructor
     *
     * @param secretKeyBase64
     * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded
     *                                  key is invalid).
     */
    private PoloniexDigest(String secretKeyBase64) {

        super(secretKeyBase64, HMAC_SHA_256);
    }

    public static PoloniexDigest createInstance(String secretKeyBase64) {

        return secretKeyBase64 == null ? null : new PoloniexDigest(secretKeyBase64);
    }

    @Override
    public String digestParams(RestInvocation restInvocation) {
        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder
                .append(restInvocation.getHttpMethod())
                .append(System.lineSeparator())
                .append(restInvocation.getInvocationUrl().replace(restInvocation.getBaseUrl(), "/"))
                .append(System.lineSeparator())
                .append(
                        restInvocation.getHttpHeadersFromParams().entrySet().stream()
                                .filter(e -> !e.getKey().equals("key") && !e.getKey().equals("signature"))
                                .sorted(Map.Entry.comparingByKey())
                                .map(e -> e.getKey() + "=" + e.getValue())
                                .collect(Collectors.joining("&"))
                );
//        String message =
//                restInvocation
//                        .getParamValue(HeaderParam.class, APIConstants.API_HEADER_TIMESTAMP)
//                        .toString()
//                        + restInvocation.getHttpMethod()
//                        + pathWithQueryString
//                        + (restInvocation.getRequestBody() != null ? restInvocation.getRequestBody() : "");

        Mac mac = getMac();

        try {
            mac.update(payloadBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new ExchangeException("Digest encoding exception", e);
        }

        return Base64.getEncoder().encodeToString(mac.doFinal());
    }

}
