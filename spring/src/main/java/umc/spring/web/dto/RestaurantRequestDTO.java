package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistRestaurantCategory;

import java.util.List;

public class RestaurantRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String address;
        @NotNull
        Float rating;
        @ExistRestaurantCategory
        Integer category;
    }

}
