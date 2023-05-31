package org.knowm.xchange.poloniex2.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexCrossMargin {

    /**
     * Indicates if symbol supports cross margin
     */
    @JsonProperty("supportCrossMargin")
    boolean supportCrossMargin;

    /**
     * Maximum supported leverage
     */
    @JsonProperty("maxLeverage")
    int maxLeverage;
}
