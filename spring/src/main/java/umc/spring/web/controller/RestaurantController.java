package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionCommandServiceImpl;
import umc.spring.service.RestaurantService.RestaurantCommandService;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistRestaurant;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
@Validated
public class RestaurantController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{restaurantId}/missions")
    public ApiResponse<MissionResponseDTO.JoinResultDTO> join(@RequestBody @Valid MissionRequestDTO.JoinDto request, @PathVariable @ExistRestaurant Long restaurantId) {
        // Call the service to add the restaurant to the specified region
        Mission mission = missionCommandService.joinMission(request, restaurantId);
        return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(mission));
    }

}
