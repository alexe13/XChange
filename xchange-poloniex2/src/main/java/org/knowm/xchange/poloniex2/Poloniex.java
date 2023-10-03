package org.knowm.xchange.poloniex2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexCurrencyInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexSymbolInfo;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexTicker;
import org.knowm.xchange.poloniex2.dto.marketdata.PoloniexTrade;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface Poloniex {

  @GET
  @Path("/markets")
  List<PoloniexSymbolInfo> allSymbols();

  @GET
  @Path("/markets/{symbol}")
  PoloniexSymbolInfo symbol(@PathParam("symbol") String symbol);

  @GET
  @Path("/v2/currencies")
  List<PoloniexCurrencyInfo> allCurrencies();

  @GET
  @Path("/v2/currencies/{currency}")
  PoloniexCurrencyInfo currency(@PathParam("currency") String currency);

  @GET
  @Path("/markets/ticker24h")
  List<PoloniexTicker> allTickers();

  @GET
  @Path("/markets/{symbol}/ticker24h")
  PoloniexTicker ticker(@PathParam("symbol") String symbol);

  @GET
  @Path("/markets/{symbol}/trades")
  List<PoloniexTrade> trades(@PathParam("symbol") String symbol);
}
