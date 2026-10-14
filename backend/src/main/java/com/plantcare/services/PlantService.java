package com.plantcare.services;

import tools.jackson.databind.JsonNode;
import com.plantcare.client.TreflePlantApiClient;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    private final TreflePlantApiClient trefleClient;

    public PlantService(TreflePlantApiClient trefleClient) {
        this.trefleClient = trefleClient;
    }

    public JsonNode searchPlants(String query) {
        return trefleClient.searchPlants(query);
    }
}
