package umc.spring.service.MissionAssignedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionAssignedHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.MissionAssignedConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MissionAssigned;
import umc.spring.repository.MissionAssignedRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.RestaurantRepository.RestaurantRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.validation.validator.MissionAssignedExistValidator;
import umc.spring.web.dto.MissionAssignedRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
public class MissionAssignedCommandServiceImpl implements MissionAssignedCommandService {

    private final MissionAssignedRepository missionAssignedRepository;
    private final MissionRepository missionRepository;
    private final MissionAssignedConverter missionAssignedConverter;

    @Autowired
    public MissionAssignedCommandServiceImpl(MissionAssignedRepository missionAssignedRepository, MissionRepository missionRepository, MissionAssignedConverter missionAssignedConverter) {
        this.missionAssignedConverter = missionAssignedConverter;
        this.missionRepository = missionRepository;
        this.missionAssignedRepository = missionAssignedRepository;
    }

    @Override
    @Transactional
    public MissionAssigned joinMissionAssigned(MissionAssignedRequestDTO.JoinDto request, Long missionId, Long userId) {
        // 레스토랑 존재 여부 확인
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MissionAssignedExistValidator missionAssignedValidator = new MissionAssignedExistValidator(missionAssignedRepository);
        missionAssignedValidator.initialize(missionId);  // PathVariable missionId 설정
        if (!missionAssignedValidator.isValid(request, null)) {
            throw new MissionAssignedHandler(ErrorStatus.MISSION_ALREADY_ASSIGNED);
        }

        // ReviewRequestDTO.JoinDto와 Restaurant을 넘겨서 Review 객체 생성
        MissionAssigned newMissionAssigned = missionAssignedConverter.toMissionAssigned(request, mission);  // ReviewConverter 인스턴스 메서드 호출

        // 리뷰 저장
        return missionAssignedRepository.save(newMissionAssigned);
    }

}
