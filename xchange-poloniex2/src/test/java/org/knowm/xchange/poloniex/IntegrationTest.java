package org.knowm.xchange.poloniex;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.poloniex2.PoloniexExchange;

@Ignore
public class IntegrationTest {

    @Test
    public void test() throws IOException {
        ExchangeSpecification spec = new PoloniexExchange().getDefaultExchangeSpecification();

        spec.setApiKey(System.getenv("apiKey"));
        spec.setSecretKey(System.getenv("secretKey"));
        Exchange polo = ExchangeFactory.INSTANCE.createExchange(spec);

        AccountInfo accountInfo = polo.getAccountService().getAccountInfo();
        System.out.println(accountInfo);
    }
}
