package org.knowm.xchange.poloniex2.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexSymbolInfo {

    /**
     * Symbol name
     */
    @JsonProperty("symbol")
    String symbol;

    /**
     * Base currency name
     */
    @JsonProperty("baseCurrencyName")
    String baseCurrencyName;

    /**
     * Quote currency name
     */
    @JsonProperty("quoteCurrencyName")
    String quoteCurrencyName;

    /**
     * Symbol display name
     */
    @JsonProperty("displayName")
    String displayName;

    /**
     * NORMAL, PAUSE, POST_ONLY
     */
    @JsonProperty("state")
    PoloniexSymbolState state;

    /**
     * Time since symbol is visible in the frontend
     */
    @JsonProperty("visibleStartTime")
    long visibleStartTime;

    /**
     * Time since symbol is tradable
     */
    @JsonProperty("tradableStartTime")
    long tradableStartTime;

    /**
     * Symbol market configuration
     */
    @JsonProperty("symbolTradeLimit")
    PoloniexSymbolTradeLimit symbolTradeLimit;

    /**
     * Symbol cross margin info
     */
    @JsonProperty("crossMargin")
    PoloniexCrossMargin crossMargin;
}
