package com.plantcare.dtos;

import java.util.List;

public record TrefleSearchResponse(
        List<TreflePlantDto> data
) {
}
