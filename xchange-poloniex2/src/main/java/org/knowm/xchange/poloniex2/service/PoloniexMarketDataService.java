package org.knowm.xchange.poloniex2.service;

import static org.knowm.xchange.poloniex2.PoloniexUtils.toPoloniexSymbol;

import java.util.List;
import java.util.stream.Collectors;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.poloniex2.PoloniexAdapters;
import org.knowm.xchange.poloniex2.PoloniexExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

public class PoloniexMarketDataService extends PoloniexMarketDataServiceRaw implements
    MarketDataService {

  public PoloniexMarketDataService(PoloniexExchange exchange,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }

  @Override
  public List<Ticker> getTickers(Params params) {
    return poloniex.allTickers()
        .stream()
        .map(PoloniexAdapters::adaptPoloniexTicker)
        .collect(Collectors.toList());
  }

  @Override
  public Ticker getTicker(Instrument instrument, Object... args) {
    return PoloniexAdapters.adaptPoloniexTicker(
        poloniex.ticker(toPoloniexSymbol(instrument))
    );
  }

  @Override
  public Trades getTrades(Instrument instrument, Object... args) {
    return PoloniexAdapters.adaptPoloniexTrades(
        poloniex.trades(toPoloniexSymbol(instrument)),
        instrument
    );
  }
}
