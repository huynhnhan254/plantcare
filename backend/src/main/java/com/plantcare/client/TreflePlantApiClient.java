
package com.plantcare.client;

import tools.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TreflePlantApiClient {

    private final RestClient restClient;
    private final String token;

    public TreflePlantApiClient(
            @Value("${trefle.api.base-url}") String baseUrl,
            @Value("${trefle.api.token}") String token) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        this.token = token;
    }

    public JsonNode searchPlants(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/plants/search")
                        .queryParam("q", query)
                        .queryParam("token", token)
                        .build())
                .retrieve()
                .body(JsonNode.class);
    }
}
