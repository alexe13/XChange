package org.knowm.xchange.poloniex2;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.poloniex2.dto.marketdata.TradeSide;

public class PoloniexUtils {

  private PoloniexUtils() {

  }

  public static CurrencyPair toCurrencyPair(String pair) {
    String[] currencies = pair.split("_");
    return new CurrencyPair(currencies[1], currencies[0]);
  }

  public static String toPoloniexSymbol(Instrument instrument) {
    return instrument.getBase() + "_" + instrument.getCounter();
  }

  public static OrderType getType(TradeSide side) {
    return side == TradeSide.SELL ? OrderType.ASK : OrderType.BID;
  }

}
