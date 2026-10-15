package com.plantcare.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TreflePlantDto(
        Long id,

        @JsonProperty("common_name")
        String commonName,

        @JsonProperty("scientific_name")
        String scientificName,

        String family,

        @JsonProperty("image_url")
        String imageUrl
) {
}
