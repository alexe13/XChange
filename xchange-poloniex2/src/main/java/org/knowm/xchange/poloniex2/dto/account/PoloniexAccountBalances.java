package org.knowm.xchange.poloniex2.dto.account;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PoloniexAccountBalances {

    @JsonProperty("accountId")
    String accountId;

    @JsonProperty("accountType")
    PoloniexAccountType accountType;

    @JsonProperty("balances")
    List<PoloniexAccountBalance> balances;
}
