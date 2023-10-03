package org.knowm.xchange.poloniex2.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexTrade {

  /**
   * Trade id
   */
  @JsonProperty("id")
  String id;

  /**
   * Trade price
   */
  @JsonProperty("price")
  String price;

  /**
   * Base units traded
   */
  @JsonProperty("quantity")
  String quantity;

  /**
   * Quote units traded
   */
  @JsonProperty("amount")
  String amount;

  /**
   * Taker's trade side (BUY, SELL)
   */
  @JsonProperty("takerSide")
  TradeSide takerSide;

  /**
   * Time the trade was pushed
   */
  @JsonProperty("ts")
  Long ts;

  /**
   * Time the trade was created
   */
  @JsonProperty("createTime")
  Long createTime;
}
