package org.knowm.xchange.poloniex2;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCurrencyInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexSymbolInfo;
import org.knowm.xchange.poloniex2.service.PoloniexAccountService;
import org.knowm.xchange.poloniex2.service.PoloniexMarketDataService;
import org.knowm.xchange.poloniex2.service.PoloniexMarketDataServiceRaw;
import org.knowm.xchange.utils.nonce.CurrentTimeIncrementalNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class PoloniexExchange extends BaseExchange implements Exchange {

    private final SynchronizedValueFactory<Long> timestampFactory =
            new CurrentTimeIncrementalNonceFactory(TimeUnit.MILLISECONDS);
    protected static ResilienceRegistries RESILIENCE_REGISTRIES = PoloniexResilience.createRegistries();

    @Override
    protected void initServices() {
        this.marketDataService = new PoloniexMarketDataService(this, RESILIENCE_REGISTRIES);
        this.accountService = new PoloniexAccountService(this, RESILIENCE_REGISTRIES);
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {
        ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
        exchangeSpecification.setSslUri("https://api.poloniex.com/");
        exchangeSpecification.setHost("poloniex.com");
        exchangeSpecification.setPort(80);
        exchangeSpecification.setExchangeName("Poloniex");
        exchangeSpecification.setExchangeDescription("Poloniex is a bitcoin and altcoin exchange.");

        return exchangeSpecification;
    }

    public SynchronizedValueFactory<Long> getTimestampFactory() {
        return timestampFactory;
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        throw new UnsupportedOperationException(
                "Poloniex uses timestamp/recvwindow rather than a nonce");
    }

    @Override
    public ResilienceRegistries getResilienceRegistries() {
        return RESILIENCE_REGISTRIES;
    }

    @Override
    public void remoteInit() throws IOException, ExchangeException {
        List<PoloniexSymbolInfo> allSymbols = ((PoloniexMarketDataServiceRaw) marketDataService).allSymbols();
        List<PoloniexCurrencyInfo> allCurrencies = ((PoloniexMarketDataServiceRaw) marketDataService).allCurrencies();

        exchangeMetaData = PoloniexAdapters.adaptToExchangeMetaData(allSymbols, allCurrencies);
    }
}
