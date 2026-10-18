package com.plantcare.services;

import com.plantcare.client.TreflePlantApiClient;
import com.plantcare.dtos.*;
import org.springframework.stereotype.Service;
// import tools.jackson.databind.JsonNode;

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
                    plant.id(),
                    plant.commonName(),
                    plant.scientificName(),
                    plant.family(),
                    plant.imageUrl()
                ))
                .toList();
    }
    
    public PlantDetailsDto getPlantDetails(Long id) {
        TreflePlantDetailsResponse response = trefleClient.getPlantDetails(id);

        TreflePlantDetailsDto plant = response.data();

        return new PlantDetailsDto(
                id,
                plant.commonName(),
                plant.scientificName(),
                plant.mainSpecies() != null
                        ? plant.mainSpecies().family()
                        : null,
                plant.imageUrl(),
                plant.year(),
                plant.author(),
                plant.observations(),
                plant.familyCommonName(),
                plant.bibliography(),
                plant.mainSpecies() != null
                        ? plant.mainSpecies().status()
                        : null,
                plant.mainSpecies() != null
                        ? plant.mainSpecies().rank()
                        : null
        );
    }

}
