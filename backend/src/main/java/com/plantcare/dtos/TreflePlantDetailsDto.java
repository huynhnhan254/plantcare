package com.plantcare.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TreflePlantDetailsDto(
        Long id,

        @JsonProperty("common_name")
        String commonName,

        @JsonProperty("scientific_name")
        String scientificName,

        @JsonProperty("image_url")
        String imageUrl,

        Integer year,
        String author,
        String observations,

        @JsonProperty("family_common_name")
        String familyCommonName,

        String bibliography,

        @JsonProperty("main_species")
        MainSpecies mainSpecies
) {
    public record MainSpecies(
            String family,
            String status,
            String rank
    ) {
    }
}
