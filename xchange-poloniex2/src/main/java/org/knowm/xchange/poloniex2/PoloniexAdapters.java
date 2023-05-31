package org.knowm.xchange.poloniex2;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.InstrumentMetaData;
import org.knowm.xchange.dto.meta.WalletHealth;
import org.knowm.xchange.instrument.Instrument;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalance;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalances;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCurrencyInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCurrencyNetworkInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexSymbolInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexTicker;

@UtilityClass
public class PoloniexAdapters {

  public static AccountInfo adaptPoloniexAccountInfo(List<PoloniexAccountBalances> accountBalances,
      String userName) {
    List<Wallet> wallets = accountBalances
        .stream()
        .map(PoloniexAdapters::adaptPoloniexAccountWallet)
        .collect(Collectors.toList());

    return new AccountInfo(userName, wallets);
  }

  public static Wallet adaptPoloniexAccountWallet(PoloniexAccountBalances poloniexAccountBalances) {
    return Wallet.Builder.from(
            poloniexAccountBalances.getBalances()
                .stream()
                .map(PoloniexAdapters::adaptPoloniexBalance)
                .collect(Collectors.toList())
        ).features(new HashSet<>(Collections.singletonList(Wallet.WalletFeature.TRADING)))
        .id("spot")
        .build();
  }

  private static Balance adaptPoloniexBalance(PoloniexAccountBalance poloniexAccountBalance) {
    return new Balance.Builder()
        .currency(Currency.getInstance(poloniexAccountBalance.getCurrency()))
        .available(poloniexAccountBalance.getAvailable())
        .frozen(poloniexAccountBalance.getHold())
        .build();
  }

  public static ExchangeMetaData adaptToExchangeMetaData(List<PoloniexSymbolInfo> allSymbols,
      List<PoloniexCurrencyInfo> allCurrencies) {
    Map<Instrument, InstrumentMetaData> instrumentMetaData = new HashMap<>(allSymbols.size());
    Map<Currency, CurrencyMetaData> currencyMetaData = new HashMap<>(allCurrencies.size());

    for (PoloniexSymbolInfo symbol : allSymbols) {
      Instrument instrument = new CurrencyPair(symbol.getBaseCurrencyName(),
          symbol.getQuoteCurrencyName());
      InstrumentMetaData metaData = new InstrumentMetaData.Builder()
          .minimumAmount(new BigDecimal(symbol.getSymbolTradeLimit().getMinQuantity()))
          .priceScale(symbol.getSymbolTradeLimit().getPriceScale())
          .volumeScale(symbol.getSymbolTradeLimit().getAmountScale())
          .build();

      instrumentMetaData.put(instrument, metaData);
    }

    for (PoloniexCurrencyInfo currencyInfo : allCurrencies) {
      Currency currency = Currency.getInstance(currencyInfo.getCoin());
      PoloniexCurrencyNetworkInfo networkInfo = Optional.ofNullable(currencyInfo.getNetworkList())
          .filter(list -> !list.isEmpty())
          .map(list -> list.get(0))
          .orElse(null);
      if (networkInfo != null) {
        CurrencyMetaData metaData = new CurrencyMetaData(
            Integer.valueOf(networkInfo.getDecimals()),
            new BigDecimal(networkInfo.getWithdrawFee()),
            new BigDecimal(networkInfo.getWithdrawMin()),
            toWalletHealth(currencyInfo, networkInfo)
        );
        currencyMetaData.put(currency, metaData);
      }
    }

    return new ExchangeMetaData(
        instrumentMetaData,
        currencyMetaData,
        null,
        null,
        false
    );
  }

  private WalletHealth toWalletHealth(PoloniexCurrencyInfo currencyInfo,
      PoloniexCurrencyNetworkInfo networkInfo) {
    if (currencyInfo.isDelisted() || !currencyInfo.isTradeEnable()) {
      return WalletHealth.OFFLINE;
    }

    if (!networkInfo.isDepositEnable()) {
      return WalletHealth.DEPOSITS_DISABLED;
    }

    if (!networkInfo.isWithdrawalEnable()) {
      return WalletHealth.WITHDRAWALS_DISABLED;
    }

    return WalletHealth.ONLINE;
  }

  public static Ticker adaptPoloniexTicker(PoloniexTicker poloniexTicker) {
    return new Ticker.Builder()
        .instrument(PoloniexUtils.toCurrencyPair(poloniexTicker.getSymbol()))
        .open(poloniexTicker.getOpen())
        .last(poloniexTicker.getClose())
        .bid(poloniexTicker.getBid())
        .ask(poloniexTicker.getAsk())
        .high(poloniexTicker.getHigh())
        .low(poloniexTicker.getLow())
        .volume(poloniexTicker.getQuantity())
        .quoteVolume(poloniexTicker.getAmount())
        .timestamp(Date.from(poloniexTicker.getTs()))
        .bidSize(poloniexTicker.getBidQuantity())
        .askSize(poloniexTicker.getAskQuantity())
        .percentageChange(poloniexTicker.getDailyChange())
        .build();
  }
}
