package org.knowm.xchange.poloniex2.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexTicker {

  @JsonProperty("symbol")
  String symbol;

  @JsonProperty("open")
  BigDecimal open;

  @JsonProperty("low")
  BigDecimal low;

  @JsonProperty("high")
  BigDecimal high;

  @JsonProperty("close")
  BigDecimal close;

  @JsonProperty("quantity")
  BigDecimal quantity;

  @JsonProperty("amount")
  BigDecimal amount;

  @JsonProperty("tradeCount")
  Integer tradeCount;

  @JsonProperty("startTime")
  Long startTime;

  @JsonProperty("closeTime")
  Long closeTime;

  @JsonProperty("displayName")
  String displayName;

  @JsonProperty("dailyChange")
  BigDecimal dailyChange;

  @JsonProperty("bid")
  BigDecimal bid;

  @JsonProperty("bidQuantity")
  BigDecimal bidQuantity;

  @JsonProperty("ask")
  BigDecimal ask;

  @JsonProperty("askQuantity")
  BigDecimal askQuantity;

  @JsonProperty("ts")
  Long ts;

  @JsonProperty("markPrice")
  BigDecimal markPrice;
}
