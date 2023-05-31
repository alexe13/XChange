package org.knowm.xchange.poloniex2.service;

import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.poloniex2.Poloniex;
import org.knowm.xchange.poloniex2.PoloniexAuthenticated;
import org.knowm.xchange.poloniex2.PoloniexExchange;
import org.knowm.xchange.service.BaseResilientExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

public class PoloniexBaseService extends BaseResilientExchangeService<PoloniexExchange> implements BaseService {

    protected final String apiKey;
    protected final Poloniex poloniex;
    protected final PoloniexAuthenticated poloniexAuthenticated;
    protected final ParamsDigest signatureCreator;

    protected PoloniexBaseService(PoloniexExchange exchange,
                                  ResilienceRegistries resilienceRegistries) {
        super(exchange, resilienceRegistries);

        this.poloniex = ExchangeRestProxyBuilder.forInterface(
                        Poloniex.class, exchange.getExchangeSpecification())
                .build();
        this.poloniexAuthenticated =
                ExchangeRestProxyBuilder.forInterface(
                                PoloniexAuthenticated.class, exchange.getExchangeSpecification())
                        .build();
        this.apiKey = exchange.getExchangeSpecification().getApiKey();
        this.signatureCreator =
                PoloniexDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    }

    public SynchronizedValueFactory<Long> getTimestampFactory() {
        return exchange.getTimestampFactory();
    }
}
