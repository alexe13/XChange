package org.knowm.xchange.poloniex2.service;

import java.io.IOException;
import java.util.List;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.poloniex2.PoloniexExchange;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalances;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountInfo;

import static org.knowm.xchange.poloniex2.PoloniexResilience.NON_RESOURCE_INTENSIVE_LIMITER;

public class PoloniexAccountServiceRaw extends PoloniexBaseService {


    protected PoloniexAccountServiceRaw(PoloniexExchange exchange,
                                        ResilienceRegistries resilienceRegistries) {
        super(exchange, resilienceRegistries);
    }

    public List<PoloniexAccountInfo> accounts() throws IOException {
        return decorateApiCall(() -> poloniexAuthenticated.accounts(apiKey, getTimestampFactory(), signatureCreator))
                .withRetry(retry("accounts"))
                .withRateLimiter(rateLimiter(NON_RESOURCE_INTENSIVE_LIMITER))
                .call();
    }

    public List<PoloniexAccountBalances> accountBalances() throws IOException {
        return decorateApiCall(
                () -> poloniexAuthenticated.accountBalances(
                        apiKey,
                        getTimestampFactory(),
                        signatureCreator)
        )
                .withRetry(retry("accountBalances"))
                .withRateLimiter(rateLimiter(NON_RESOURCE_INTENSIVE_LIMITER))
                .call();
    }

}
