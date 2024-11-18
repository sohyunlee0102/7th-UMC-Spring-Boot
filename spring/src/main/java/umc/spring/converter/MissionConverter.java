package umc.spring.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.repository.RestaurantRepository.RestaurantRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@Component
public class MissionConverter {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MissionConverter(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Review 생성 시 사용
    public static MissionResponseDTO.JoinResultDTO toJoinResultDTO(Mission mission) {
        return MissionResponseDTO.JoinResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())  // 실제 생성 시간 사용
                .build();
    }

    // ReviewRequestDTO.JoinDto를 Review 객체로 변환
    public Mission toMission(MissionRequestDTO.JoinDto request, Restaurant restaurant) {
        return Mission.builder()
                .content(request.getContent())
                .getPoint(request.getPoint())
                .restaurant(restaurant)
                .build();
    }

}
