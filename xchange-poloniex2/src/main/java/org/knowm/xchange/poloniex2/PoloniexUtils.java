package org.knowm.xchange.poloniex2;

import org.knowm.xchange.currency.CurrencyPair;

public class PoloniexUtils {

  private PoloniexUtils() {

  }

  public static CurrencyPair toCurrencyPair(String pair) {
    String[] currencies = pair.split("_");
    return new CurrencyPair(currencies[1], currencies[0]);
  }
}
