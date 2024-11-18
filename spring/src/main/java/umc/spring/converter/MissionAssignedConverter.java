package umc.spring.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MissionAssigned;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.MissionAssignedRequestDTO;
import umc.spring.web.dto.MissionAssignedResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@Component
public class MissionAssignedConverter {

    private final UserRepository userRepository;

    @Autowired
    public MissionAssignedConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Review 생성 시 사용
    public static MissionAssignedResponseDTO.JoinResultDTO toJoinResultDTO(MissionAssigned missionAssigned) {
        return MissionAssignedResponseDTO.JoinResultDTO.builder()
                .missionAssignedId(missionAssigned.getId())
                .createdAt(missionAssigned.getCreatedAt())  // 실제 생성 시간 사용
                .build();
    }

    // ReviewRequestDTO.JoinDto를 Review 객체로 변환
    public MissionAssigned toMissionAssigned(MissionAssignedRequestDTO.JoinDto request, Mission mission) {
        // Long 타입의 userId로 User 객체를 찾음
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        return MissionAssigned.builder()
                .isCompleted(false)
                .mission(mission)
                .user(user)  // User 객체 설정
                .build();
    }

}
