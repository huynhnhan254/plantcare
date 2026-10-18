package com.plantcare.dtos;

public record PlantDetailsDto(
        Long id,
        String commonName,
        String scientificName,
        String family,
        String imageUrl,
        Integer year,
        String author,
        String observations,
        String familyCommonName,
        String bibliography,
        String status,
        String rank
) {
}
