package org.knowm.xchange.poloniex2.service;

import static org.knowm.xchange.poloniex2.PoloniexResilience.PUBLIC_HEAVY_REQUEST_LIMITER;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.poloniex2.PoloniexExchange;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCurrencyInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexSymbolInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexTicker;

public class PoloniexMarketDataServiceRaw extends PoloniexBaseService {

  protected PoloniexMarketDataServiceRaw(PoloniexExchange exchange,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }

  public List<PoloniexSymbolInfo> allSymbols() throws IOException {
    return decorateApiCall(poloniex::allSymbols)
        .withRetry(retry("allSymbols"))
        .withRateLimiter(rateLimiter(PUBLIC_HEAVY_REQUEST_LIMITER))
        .call();
  }

  public List<PoloniexCurrencyInfo> allCurrencies() throws IOException {
    return decorateApiCall(poloniex::allCurrencies)
        .withRetry(retry("allCurrencies"))
        .withRateLimiter(rateLimiter(PUBLIC_HEAVY_REQUEST_LIMITER))
        .call();
  }

  public List<PoloniexTicker> allTickers() throws IOException {
    return decorateApiCall(poloniex::allTickers)
        .withRetry(retry("allTickers"))
        .withRateLimiter(rateLimiter(PUBLIC_HEAVY_REQUEST_LIMITER))
        .call();
  }

  public PoloniexTicker ticker(String symbol) throws IOException {
    return decorateApiCall(() -> poloniex.ticker(symbol))
        .withRetry(retry("ticker"))
        .withRateLimiter(rateLimiter(PUBLIC_HEAVY_REQUEST_LIMITER))
        .call();
  }
}
