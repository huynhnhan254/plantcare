package com.plantcare.dtos;

public record PlantDto(
        String commonName,
        String scientificName,
        String family,
        String imageUrl
) {
}
