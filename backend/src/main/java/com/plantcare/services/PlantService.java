package com.plantcare.services;

import com.plantcare.client.TreflePlantApiClient;
import com.plantcare.dtos.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final TreflePlantApiClient trefleClient;

    public PlantService(TreflePlantApiClient trefleClient) {
        this.trefleClient = trefleClient;
    }

    public List<PlantDto> searchPlants(String query) {
        TrefleSearchResponse response =
                trefleClient.searchPlants(query);

        return response.data().stream()
                .map(plant -> new PlantDto(
                        plant.commonName(),
                        plant.scientificName(),
                        plant.family(),
                        plant.imageUrl()
                ))
                .toList();
    }
}
