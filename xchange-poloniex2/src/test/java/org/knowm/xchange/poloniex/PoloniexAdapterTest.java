package org.knowm.xchange.poloniex;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.poloniex2.PoloniexAdapters;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalances;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexTrade;

public class PoloniexAdapterTest {

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testAccountInfoAdapter() throws IOException {
    InputStream is =
        PoloniexAdapterTest.class.getResourceAsStream(
            "/org/knowm/xchange/poloniex/dto/account/example-account-data.json");

    List<PoloniexAccountBalances> allBalances = mapper.readValue(is,
        mapper.getTypeFactory().constructCollectionType(List.class, PoloniexAccountBalances.class)
    );

    AccountInfo accountInfo = PoloniexAdapters.adaptPoloniexAccountInfo(allBalances, "Bruce Wayne");
    assertThat(accountInfo.getUsername()).isEqualTo("Bruce Wayne");
    Wallet wallet = accountInfo.getWallet();
    assertThat(wallet.getBalance(Currency.TRX).getCurrency()).isEqualTo(Currency.TRX);
    assertThat(wallet.getBalance(Currency.TRX).getTotal()).isEqualTo("93660.265596793475");
    assertThat(wallet.getBalance(Currency.TRX).getAvailable()).isEqualTo("93640.421767943475");
    assertThat(wallet.getBalance(Currency.TRX).getFrozen()).isEqualTo("19.84382885");

    assertThat(wallet.getBalance(Currency.getInstance("ELON")).getCurrency())
        .isEqualTo(Currency.getInstance("ELON"));
    assertThat(wallet.getBalance(Currency.getInstance("ELON")).getTotal()).isEqualTo("100037.9449");
    assertThat(wallet.getBalance(Currency.getInstance("ELON")).getAvailable()).isEqualTo(
        "100037.9449");
    assertThat(wallet.getBalance(Currency.getInstance("ELON")).getFrozen()).isEqualTo("0.00");

    assertThat(wallet.getBalance(Currency.USDC).getCurrency()).isEqualTo(Currency.USDC);
    assertThat(wallet.getBalance(Currency.USDC).getTotal()).isEqualTo("100663.813609427831705");
    assertThat(wallet.getBalance(Currency.USDC).getAvailable()).isEqualTo("78086.768609427831705");
    assertThat(wallet.getBalance(Currency.USDC).getFrozen()).isEqualTo("22577.045");
  }

  @Test
  public void testAdaptPoloniexTrades() throws IOException {
    InputStream is =
        PoloniexAdapterTest.class.getResourceAsStream(
            "/org/knowm/xchange/poloniex/dto/marketdata/example-trades-data.json");

    List<PoloniexTrade> trades = mapper.readValue(is,
        mapper.getTypeFactory().constructCollectionType(List.class, PoloniexTrade.class)
    );

    Trades result = PoloniexAdapters.adaptPoloniexTrades(trades, CurrencyPair.BTC_USD);
    Assertions.assertThat(result.getTrades())
        .singleElement()
        .satisfies(actual -> {
              assertThat(actual.getId()).isEqualTo("194");
              assertThat(actual.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(1.9));
              assertThat(actual.getOriginalAmount()).isEqualByComparingTo(BigDecimal.valueOf(209.00));
              assertThat(actual.getType()).isEqualTo(OrderType.ASK);
              assertThat(actual.getInstrument()).isEqualTo(CurrencyPair.BTC_USD);
              assertThat(actual.getTimestamp()).isEqualTo(new Date(1648690080545L));
            }
        );
  }
}
