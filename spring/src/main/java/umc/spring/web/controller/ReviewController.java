package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.validation.annotation.ExistRestaurant;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{restaurantId}")
    public ApiResponse<ReviewResponseDTO.JoinResultDTO> join(@RequestBody @Valid ReviewRequestDTO.JoinDto request, @PathVariable @ExistRestaurant Long restaurantId) {
        Review review = reviewCommandService.joinReview(request, restaurantId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }

}
