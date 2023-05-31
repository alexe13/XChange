package org.knowm.xchange.poloniex2.service;

import java.io.IOException;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.poloniex2.PoloniexAdapters;
import org.knowm.xchange.poloniex2.PoloniexExchange;
import org.knowm.xchange.service.account.AccountService;

public class PoloniexAccountService extends PoloniexAccountServiceRaw implements AccountService {
    public PoloniexAccountService(PoloniexExchange exchange,
                                  ResilienceRegistries resilienceRegistries) {
        super(exchange, resilienceRegistries);
    }

    @Override
    public AccountInfo getAccountInfo() throws IOException {
        return PoloniexAdapters.adaptPoloniexAccountInfo(
                super.accountBalances(),
                exchange.getExchangeSpecification().getUserName()
        );
    }
}
