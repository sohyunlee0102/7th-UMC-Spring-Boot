package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.Restaurant;
import umc.spring.service.RestaurantService.RestaurantCommandService;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
@Validated
public class RegionController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/{regionId}/restaurants")
    public ApiResponse<RestaurantResponseDTO.JoinResultDTO> join(@RequestBody @Valid RestaurantRequestDTO.JoinDto request,
                                                                 @PathVariable @ExistRegion Long regionId) {
        // 디버깅: 전달된 regionId 값 출력
        System.out.println("RegionController: Received regionId = " + regionId);

        // 레스토랑을 해당 지역에 추가하는 로직
        Restaurant restaurant = restaurantCommandService.joinRestaurant(request, regionId);

        // 성공적으로 레스토랑을 추가한 후 응답을 반환
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }
}
