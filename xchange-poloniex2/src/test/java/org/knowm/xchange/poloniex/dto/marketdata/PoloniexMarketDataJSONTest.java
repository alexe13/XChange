package org.knowm.xchange.poloniex.dto.marketdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCrossMargin;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCurrencyInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCurrencyNetworkInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexSymbolInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexSymbolState;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexSymbolTradeLimit;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexTicker;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexTrade;
import org.knowm.xchange.poloniex2.dto.marketdata.TradeSide;

public class PoloniexMarketDataJSONTest {

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testSymbolsUnmarshal() throws IOException {
    InputStream is =
        this.getClass().getResourceAsStream(
            "/org/knowm/xchange/poloniex/dto/marketdata/example-symbol-data.json");

    List<PoloniexSymbolInfo> symbols = mapper.readValue(is,
        mapper.getTypeFactory().constructCollectionType(List.class, PoloniexSymbolInfo.class)
    );
    Assertions.assertThat(symbols).hasSize(2)
        .containsExactlyInAnyOrder(
            PoloniexSymbolInfo.builder()
                .symbol("BTC_USDT")
                .baseCurrencyName("BTC")
                .quoteCurrencyName("USDT")
                .displayName("BTC/USDT")
                .state(PoloniexSymbolState.NORMAL)
                .visibleStartTime(1659018819512L)
                .tradableStartTime(1659018819512L)
                .symbolTradeLimit(
                    PoloniexSymbolTradeLimit.builder()
                        .symbol("BTC_USDT")
                        .priceScale(2)
                        .quantityScale(6)
                        .amountScale(2)
                        .minQuantity("0.000001")
                        .minAmount("1")
                        .highestBid("0")
                        .lowestAsk("0")
                        .build()
                )
                .crossMargin(
                    PoloniexCrossMargin.builder()
                        .supportCrossMargin(true)
                        .maxLeverage(3)
                        .build()
                )
                .build(),
            PoloniexSymbolInfo.builder()
                .symbol("ETH_USDT")
                .baseCurrencyName("ETH")
                .quoteCurrencyName("USDT")
                .displayName("ETH/USDT")
                .state(PoloniexSymbolState.NORMAL)
                .visibleStartTime(1659018820007L)
                .tradableStartTime(1659018820007L)
                .symbolTradeLimit(
                    PoloniexSymbolTradeLimit.builder()
                        .symbol("ETH_USDT")
                        .priceScale(2)
                        .quantityScale(6)
                        .amountScale(2)
                        .minQuantity("0.000001")
                        .minAmount("1")
                        .highestBid("0")
                        .lowestAsk("0")
                        .build()
                )
                .crossMargin(
                    PoloniexCrossMargin.builder()
                        .supportCrossMargin(true)
                        .maxLeverage(3)
                        .build()
                )
                .build()
        );

  }

  @Test
  public void testCurrenciesUnmarshall() throws IOException {
    InputStream is =
        this.getClass().getResourceAsStream(
            "/org/knowm/xchange/poloniex/dto/marketdata/example-currency-data.json");
    List<PoloniexCurrencyInfo> currencies = mapper.readValue(is,
        mapper.getTypeFactory().constructCollectionType(List.class, PoloniexCurrencyInfo.class)
    );

    Assertions.assertThat(currencies).hasSize(2)
        .containsExactlyInAnyOrder(
            PoloniexCurrencyInfo.builder()
                .id(1)
                .coin("1CR")
                .delisted(true)
                .tradeEnable(false)
                .name("1CRedit")
                .networkList(Collections.singletonList(
                    PoloniexCurrencyNetworkInfo.builder()
                        .id(1)
                        .coin("1CR")
                        .name("1CRedit")
                        .currencyType("address")
                        .blockchain("1CR")
                        .withdrawalEnable(false)
                        .depositEnable(false)
                        .depositAddress(null)
                        .withdrawMin(null)
                        .decimals("8")
                        .withdrawFee("0.01000000")
                        .minConfirm(10000)
                        .build()
                )).build(),
            PoloniexCurrencyInfo.builder()
                .id(446)
                .coin("AAVE")
                .delisted(false)
                .tradeEnable(true)
                .name("Aave")
                .supportCollateral(false)
                .supportBorrow(false)
                .networkList(Collections.singletonList(
                    PoloniexCurrencyNetworkInfo.builder()
                        .id(446)
                        .coin("AAVE")
                        .name("Ethereum")
                        .currencyType("address")
                        .blockchain("ETH")
                        .withdrawalEnable(true)
                        .depositEnable(true)
                        .depositAddress(null)
                        .withdrawMin(null)
                        .decimals("18")
                        .withdrawFee("0.22553338")
                        .minConfirm(12)
                        .build()
                )).build()
        );
  }

  @Test
  public void testTickersUnmarshal() throws IOException {
    InputStream is =
        this.getClass().getResourceAsStream(
            "/org/knowm/xchange/poloniex/dto/marketdata/example-ticker-data.json");

    List<PoloniexTicker> symbols = mapper.readValue(is,
        mapper.getTypeFactory().constructCollectionType(List.class, PoloniexTicker.class)
    );
    Assertions.assertThat(symbols).hasSize(2)
        .containsExactlyInAnyOrder(
            PoloniexTicker.builder()
                .symbol("BTC_USDT")
                .open(BigDecimal.valueOf(30338.34))
                .low(BigDecimal.valueOf(30308.14))
                .high(BigDecimal.valueOf(32338.91))
                .close(BigDecimal.valueOf(30938.56))
                .quantity(BigDecimal.valueOf(0.1))
                .amount(BigDecimal.ZERO)
                .tradeCount(1)
                .startTime(Instant.ofEpochMilli(1648995780000L))
                .closeTime(Instant.ofEpochMilli(1649082121008L))
                .displayName("BTC/USDT")
                .dailyChange(BigDecimal.valueOf(0.0198))
                .bid(BigDecimal.valueOf(30338.34))
                .bidQuantity(BigDecimal.valueOf(0.01))
                .ask(BigDecimal.valueOf(30338.35))
                .askQuantity(BigDecimal.valueOf(0.01))
                .ts(Instant.ofEpochMilli(1649082180190L))
                .markPrice(BigDecimal.valueOf(30938.57))
                .build(),
            PoloniexTicker.builder()
                .symbol("ETH_USDT")
                .open(BigDecimal.valueOf(1817.69))
                .low(BigDecimal.valueOf(1723.50))
                .high(BigDecimal.valueOf(1897.12))
                .close(BigDecimal.valueOf(1723.50))
                .quantity(BigDecimal.valueOf(2))
                .amount(BigDecimal.ZERO)
                .tradeCount(5)
                .startTime(Instant.ofEpochMilli(1648995780000L))
                .closeTime(Instant.ofEpochMilli(1649082121009L))
                .displayName("ETH/USDT")
                .dailyChange(BigDecimal.valueOf(-0.0052))
                .bid(BigDecimal.valueOf(1817.70))
                .bidQuantity(BigDecimal.valueOf(0.01))
                .ask(BigDecimal.valueOf(1817.71))
                .askQuantity(BigDecimal.valueOf(0.01))
                .ts(Instant.ofEpochMilli(1649082180190L))
                .markPrice(BigDecimal.valueOf(1723.51))
                .build()
        );

  }

  @Test
  public void testTradesUnmarshal() throws IOException {
    InputStream is =
        this.getClass().getResourceAsStream(
            "/org/knowm/xchange/poloniex/dto/marketdata/example-trades-data.json");

    List<PoloniexTrade> trades = mapper.readValue(is,
        mapper.getTypeFactory().constructCollectionType(List.class, PoloniexTrade.class)
    );
    Assertions.assertThat(trades).singleElement()
        .isEqualTo(PoloniexTrade.builder()
            .id("194")
            .price("1.9")
            .quantity("110")
            .amount("209.00")
            .takerSide(TradeSide.SELL)
            .ts(1648690080545L)
            .createTime(1648634905695L)
            .build()
        );
  }
}
