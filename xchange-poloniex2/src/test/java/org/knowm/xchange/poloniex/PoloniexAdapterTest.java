package org.knowm.xchange.poloniex;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.poloniex2.PoloniexAdapters;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalances;

import static org.assertj.core.api.Assertions.assertThat;

public class PoloniexAdapterTest {

    @Test
    public void testAccountInfoAdapter() throws IOException {

        InputStream is =
                PoloniexAdapterTest.class.getResourceAsStream(
                        "/org/knowm/xchange/poloniex/dto/account/example-account-data.json");

        ObjectMapper mapper = new ObjectMapper();
        List<PoloniexAccountBalances> allBalances = mapper.readValue(is,
                mapper.getTypeFactory().constructCollectionType(List.class, PoloniexAccountBalances.class)
        );

        AccountInfo accountInfo = PoloniexAdapters.adaptPoloniexAccountInfo(allBalances, "Bruce Wayne");
        assertThat(accountInfo.getUsername()).isEqualTo("Bruce Wayne");
        Wallet wallet = accountInfo.getWallet();
        assertThat(wallet.getBalance(Currency.TRX).getCurrency()).isEqualTo(Currency.TRX);
        assertThat(wallet.getBalance(Currency.TRX).getTotal()).isEqualTo("93660.265596793475");
        assertThat(wallet.getBalance(Currency.TRX).getAvailable()).isEqualTo("93640.421767943475");
        assertThat(wallet.getBalance(Currency.TRX).getFrozen()).isEqualTo("19.84382885");

        assertThat(wallet.getBalance(Currency.getInstance("ELON")).getCurrency())
                .isEqualTo(Currency.getInstance("ELON"));
        assertThat(wallet.getBalance(Currency.getInstance("ELON")).getTotal()).isEqualTo("100037.9449");
        assertThat(wallet.getBalance(Currency.getInstance("ELON")).getAvailable()).isEqualTo("100037.9449");
        assertThat(wallet.getBalance(Currency.getInstance("ELON")).getFrozen()).isEqualTo("0.00");

        assertThat(wallet.getBalance(Currency.USDC).getCurrency()).isEqualTo(Currency.USDC);
        assertThat(wallet.getBalance(Currency.USDC).getTotal()).isEqualTo("100663.813609427831705");
        assertThat(wallet.getBalance(Currency.USDC).getAvailable()).isEqualTo("78086.768609427831705");
        assertThat(wallet.getBalance(Currency.USDC).getFrozen()).isEqualTo("22577.045");
    }
}
