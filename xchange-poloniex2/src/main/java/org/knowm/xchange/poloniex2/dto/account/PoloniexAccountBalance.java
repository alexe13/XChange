package org.knowm.xchange.poloniex2.dto.account;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexAccountBalance {

    @JsonProperty("currencyId")
    String currencyId;

    @JsonProperty("currency")
    String currency;

    @JsonProperty("available")
    BigDecimal available;

    @JsonProperty("hold")
    BigDecimal hold;
}
