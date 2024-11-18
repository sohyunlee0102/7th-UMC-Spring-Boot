package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.RestaurantCategory;
import umc.spring.validation.annotation.ExistRestaurantCategory;

public class RestaurantCategoryValidator implements ConstraintValidator<ExistRestaurantCategory, Integer> {

    @Override
    public void initialize(ExistRestaurantCategory constraintAnnotation) {
        // 특별한 초기화가 필요 없다면 비워둘 수 있습니다.
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // value가 null이거나 RestaurantCategory 범위에 포함되지 않으면 false
        if (value == null || value < 0 || value >= RestaurantCategory.values().length) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.RESTAURANT_CATEGORY_NOT_FOUND.toString())
                    .addConstraintViolation();
            return false;
        }

        // 유효한 값이면 true
        return true;
    }
}
