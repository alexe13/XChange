package org.knowm.xchange.poloniex2.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexSymbolTradeLimit {

    /**
     * Symbol name
     */
    @JsonProperty("symbol")
    String symbol;

    /**
     * Decimal precision for price
     */
    @JsonProperty("priceScale")
    int priceScale;

    /**
     * Decimal precision for quantity
     */
    @JsonProperty("quantityScale")
    int quantityScale;

    /**
     * Decimal precision for amount
     */
    @JsonProperty("amountScale")
    int amountScale;

    /**
     * Minimum required quantity
     */
    @JsonProperty("minQuantity")
    String minQuantity;

    /**
     * Minimum required amount
     */
    @JsonProperty("minAmount")
    String minAmount;

    /**
     * Maximum allowed bid price (market bound)
     */
    @JsonProperty("highestBid")
    String highestBid;

    /**
     * Minimum allowed ask price (market bound)
     */
    @JsonProperty("lowestAsk")
    String lowestAsk;
}
