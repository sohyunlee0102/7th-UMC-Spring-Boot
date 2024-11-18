package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.RestaurantCategoryValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RestaurantCategoryValidator.class)  // 유효성 검사 로직을 담당할 클래스 설정
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })  // 어노테이션 적용 위치
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRestaurantCategory {
    String message() default "존재하지 않는 식당 카테고리입니다";  // 기본 에러 메시지
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
