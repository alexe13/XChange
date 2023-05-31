package org.knowm.xchange.poloniex2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountBalances;
import org.knowm.xchange.poloniex2.dto.account.PoloniexAccountInfo;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface PoloniexAuthenticated {

  @GET
  @Path("accounts")
  List<PoloniexAccountInfo> accounts(
      @HeaderParam("key") String apiKey,
      @HeaderParam("signTimestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("signature") ParamsDigest signature
  );

  @GET
  @Path("accounts/balances")
  List<PoloniexAccountBalances> accountBalances(
      @HeaderParam("key") String apiKey,
      @HeaderParam("signTimestamp") SynchronizedValueFactory<Long> timestamp,
      @HeaderParam("signature") ParamsDigest signature
  );
}
