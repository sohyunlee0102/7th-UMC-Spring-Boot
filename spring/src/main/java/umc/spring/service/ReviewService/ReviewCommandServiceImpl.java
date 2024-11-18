package umc.spring.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Restaurant;
import umc.spring.repository.RestaurantRepository.RestaurantRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewConverter reviewConverter;  // ReviewConverter를 주입받음

    @Autowired
    public ReviewCommandServiceImpl(ReviewRepository reviewRepository,
                                    RestaurantRepository restaurantRepository,
                                    ReviewConverter reviewConverter) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.reviewConverter = reviewConverter;  // 생성자를 통해 ReviewConverter 주입
    }

    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO.JoinDto request, Long restaurantId) {
        // 레스토랑 존재 여부 확인
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        // ReviewRequestDTO.JoinDto와 Restaurant을 넘겨서 Review 객체 생성
        Review newReview = reviewConverter.toReview(request, restaurant);  // ReviewConverter 인스턴스 메서드 호출

        // 리뷰 저장
        return reviewRepository.save(newReview);
    }
}
