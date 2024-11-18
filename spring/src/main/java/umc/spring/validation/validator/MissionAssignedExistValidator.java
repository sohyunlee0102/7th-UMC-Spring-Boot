package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import umc.spring.repository.MissionAssignedRepository;
import umc.spring.validation.annotation.ExistMissionAssigned;
import umc.spring.web.dto.MissionAssignedRequestDTO;

@Component
public class MissionAssignedExistValidator implements ConstraintValidator<ExistMissionAssigned, MissionAssignedRequestDTO.JoinDto> {

    @Autowired
    private final MissionAssignedRepository missionAssignedRepository; // 미션 할당 레포지토리

    private Long missionIdFromPath;  // PathVariable로 받은 missionId

    public MissionAssignedExistValidator(MissionAssignedRepository missionAssignedRepository) {
        this.missionAssignedRepository = missionAssignedRepository;
    }

    // missionIdFromPath를 Validator에서 사용할 수 있도록 설정
  //  @Override
    public void initialize(Long missionId) {
        missionIdFromPath = missionId;
    }

    @Override
    public boolean isValid(MissionAssignedRequestDTO.JoinDto value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Long userId = value.getUserId();  // RequestBody에서 받은 userId
        Long missionId = missionIdFromPath;  // PathVariable로 받은 missionId

        if (userId == null || missionId == null) {
            return true;  // missionId나 userId가 null이면 검증을 수행하지 않음
        }

        // missionId와 userId의 조합이 이미 할당된 미션인지 체크
        boolean isAssigned = missionAssignedRepository.existsByUserIdAndMissionId(userId, missionId);

        // 이미 할당된 경우 false를 반환하여 유효성 검증을 실패시킴
        return !isAssigned;
    }
}
