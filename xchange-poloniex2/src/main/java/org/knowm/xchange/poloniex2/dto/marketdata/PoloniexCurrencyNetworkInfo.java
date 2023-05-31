package org.knowm.xchange.poloniex2.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexCurrencyNetworkInfo {

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
     * Currency name
     */
    @JsonProperty("name")
    String name;

    /**
     * Currency type: address or address-payment-id
     */
    @JsonProperty("currencyType")
    String currencyType;

    /**
     * The networks the currency runs on
     */
    @JsonProperty("blockchain")
    String blockchain;

    /**
     * Designates whether (true) or not (false) this currency can be withdrawn
     */
    @JsonProperty("withdrawalEnable")
    boolean withdrawalEnable;

    /**
     * Designates whether (true) or not (false) this currency can be allowed to deposit
     */
    @JsonProperty("depositEnable")
    boolean depositEnable;

    /**
     * If available, the deposit address for this currency
     */
    @JsonProperty("depositAddress")
    String depositAddress;

    /**
     * The minimum withdrawal amount, or a number less than or equal to 0 if none
     */
    @JsonProperty("withdrawMin")
    String withdrawMin;

    /**
     * Decimals of currency
     */
    @JsonProperty("decimals")
    String decimals;

    /**
     * Withdraw fee of currency
     */
    @JsonProperty("withdrawFee")
    String withdrawFee;

    /**
     * The minimum number of blocks necessary before a deposit can be credited to an account
     */
    @JsonProperty("minConfirm")
    int minConfirm;
}
