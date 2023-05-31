package org.knowm.xchange.poloniex.dto.account;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalance;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalances;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountType;

public class PoloniexAccountJSONTest {

    @Test
    public void testBalanceUnmarshal() throws IOException {
        InputStream is =
                PoloniexAccountJSONTest.class.getResourceAsStream(
                        "/org/knowm/xchange/poloniex/dto/account/example-account-data.json");

        ObjectMapper mapper = new ObjectMapper();
        List<PoloniexAccountBalances> balances = mapper.readValue(is,
                mapper.getTypeFactory().constructCollectionType(List.class, PoloniexAccountBalances.class)
        );
        Assertions.assertThat(balances).hasSize(1);
        PoloniexAccountBalances balance1 = balances.get(0);
        Assertions.assertThat(balance1.getAccountId()).isEqualTo("123");
        Assertions.assertThat(balance1.getAccountType()).isEqualTo(PoloniexAccountType.SPOT);
        Assertions.assertThat(balance1.getBalances()).hasSize(3)
                .containsExactlyInAnyOrder(
                        PoloniexAccountBalance.builder()
                                .currencyId("60001")
                                .currency("TRX")
                                .available(new BigDecimal("93640.421767943475"))
                                .hold(new BigDecimal("19.84382885"))
                                .build(),
                        PoloniexAccountBalance.builder()
                                .currencyId("60002")
                                .currency("ELON")
                                .available(new BigDecimal("100037.9449"))
                                .hold(new BigDecimal("0.00"))
                                .build(),
                        PoloniexAccountBalance.builder()
                                .currencyId("60003")
                                .currency("USDC")
                                .available(new BigDecimal("78086.768609427831705"))
                                .hold(new BigDecimal("22577.045"))
                                .build()
                );
    }
}
