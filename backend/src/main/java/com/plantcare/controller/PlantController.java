package com.plantcare.controller;

import com.plantcare.dtos.PlantDto;
import com.plantcare.services.PlantService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/search")
    public List<PlantDto> searchPlants(
            @RequestParam
            @jakarta.validation.constraints.NotBlank
            String q) {

        return plantService.searchPlants(q.trim());
    }
}
