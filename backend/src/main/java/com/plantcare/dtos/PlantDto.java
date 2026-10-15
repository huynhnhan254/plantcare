package com.plantcare.dtos;

public record PlantDto(
        Long id,
        String commonName,
        String scientificName,
        String family,
        String imageUrl
) {
}
