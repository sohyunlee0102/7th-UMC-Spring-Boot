package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.UserExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserExistValidator.class)  // 수정: UserExistValidator 클래스 사용
@Target({ElementType.FIELD, ElementType.PARAMETER})  // 필드나 파라미터에 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUser {

    String message() default "존재하지 않는 사용자입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
