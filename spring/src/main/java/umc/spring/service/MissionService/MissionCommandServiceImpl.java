package umc.spring.service.MissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.RestaurantRepository.RestaurantRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;
    private final MissionConverter missionConverter;  // ReviewConverter를 주입받음

    @Autowired
    public MissionCommandServiceImpl(MissionRepository missionRepository,
                                     RestaurantRepository restaurantRepository,
                                     MissionConverter missionConverter) {
        this.missionRepository = missionRepository;
        this.restaurantRepository = restaurantRepository;
        this.missionConverter = missionConverter;
    }

    @Override
    @Transactional
    public Mission joinMission(MissionRequestDTO.JoinDto request, Long restaurantId) {
        // 레스토랑 존재 여부 확인
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        // ReviewRequestDTO.JoinDto와 Restaurant을 넘겨서 Review 객체 생성
        Mission newMission = missionConverter.toMission(request, restaurant);  // ReviewConverter 인스턴스 메서드 호출

        // 리뷰 저장
        return missionRepository.save(newMission);
    }

}
