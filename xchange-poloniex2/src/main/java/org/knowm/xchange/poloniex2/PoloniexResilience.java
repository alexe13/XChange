package org.knowm.xchange.poloniex2;

import java.time.Duration;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.knowm.xchange.client.ResilienceRegistries;

public class PoloniexResilience {

    public static final String PUBLIC_REQUEST_LIMITER = "publicRequestLimiter";

    public static final String PUBLIC_HEAVY_REQUEST_LIMITER = "publicHeavyRequestLimiter";

    public static final String NON_RESOURCE_INTENSIVE_LIMITER = "nonResourceIntensiveLimiter";

    public static final String RESOURCE_INTENSIVE_LIMITER = "nonResourceIntensiveLimiter";

    public static ResilienceRegistries createRegistries() {
        ResilienceRegistries registries = new ResilienceRegistries();
        registries.rateLimiters()
                .rateLimiter(
                        PUBLIC_REQUEST_LIMITER,
                        RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                                .limitRefreshPeriod(Duration.ofSeconds(1))
                                .limitForPeriod(200)
                                .build()
                );

        registries.rateLimiters()
                .rateLimiter(
                        PUBLIC_HEAVY_REQUEST_LIMITER,
                        RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                                .limitRefreshPeriod(Duration.ofSeconds(1))
                                .limitForPeriod(10)
                                .build()
                );

        registries
                .rateLimiters()
                .rateLimiter(
                        NON_RESOURCE_INTENSIVE_LIMITER,
                        RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                                .limitRefreshPeriod(Duration.ofSeconds(1))
                                .limitForPeriod(50)
                                .build());
        registries
                .rateLimiters()
                .rateLimiter(
                        RESOURCE_INTENSIVE_LIMITER,
                        RateLimiterConfig.from(registries.rateLimiters().getDefaultConfig())
                                .limitRefreshPeriod(Duration.ofSeconds(1))
                                .limitForPeriod(10)
                                .build());
        return registries;
    }
}
