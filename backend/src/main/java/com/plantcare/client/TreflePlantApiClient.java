package com.plantcare.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.cache.annotation.Cacheable;
// import org.springframework.cache.annotation.CacheEvict;

import com.plantcare.dtos.TreflePlantDetailsResponse;
import com.plantcare.dtos.TrefleSearchResponse;

@Component
public class TreflePlantApiClient {

    private final RestClient restClient;
    private final String token;
    private final TrefleRateLimiter rateLimiter;

    public TreflePlantApiClient(
            @Value("${trefle.api.base-url}") String baseUrl,
            @Value("${trefle.api.token}") String token,
            TrefleRateLimiter rateLimiter) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        this.token = token;
        this.rateLimiter = rateLimiter;
    }

    @Cacheable(
        cacheNames = "plantSearches",
        key = "#query.trim().toLowerCase()")
    public TrefleSearchResponse searchPlants(String query) {
        rateLimiter.acquire();

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/plants/search")
                        .queryParam("q", query)
                        .queryParam("token", token)
                        .build())
                .retrieve()
                .body(TrefleSearchResponse.class);
    }

    @Cacheable (
        cacheNames = "plantDetails",
        key = "#id"
    )
    public TreflePlantDetailsResponse getPlantDetails(long id) {
        rateLimiter.acquire();

        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/plants/{id}")
                    .queryParam("token", token)
                    .build(id))
            .retrieve()
            .body(TreflePlantDetailsResponse.class);
    }
}