package org.knowm.xchange.poloniex2.dto.marketdata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexCurrencyInfo {

    /**
     * Currency id
     */
    @JsonProperty("id")
    int id;

    /**
     * Currency symbol
     */
    @JsonProperty("coin")
    String coin;

    /**
     * Designates whether (true) or not (false) this currency has been delisted from the exchange
     */
    @JsonProperty("delisted")
    boolean delisted;

    /**
     * Designates whether (true) or not (false) this currency can be traded on the exchange
     */
    @JsonProperty("tradeEnable")
    boolean tradeEnable;

    /**
     * Currency name
     */
    @JsonProperty("name")
    String name;

    /**
     * Indicates if this currency supports collateral in cross margin
     */
    @JsonProperty("supportCollateral")
    boolean supportCollateral;

    /**
     * Indicates if this currency supports borrows in cross margin
     */
    @JsonProperty("supportBorrow")
    boolean supportBorrow;

    /**
     * The networks the currency runs on
     */
    @JsonProperty("networkList")
    List<PoloniexCurrencyNetworkInfo> networkList;
}
