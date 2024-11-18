package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.User;
import umc.spring.repository.UserRepository;
import umc.spring.validation.annotation.ExistUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserExistValidator implements ConstraintValidator<ExistUser, Long> {

    private final UserRepository userRepository;

    @Override
    public void initialize(ExistUser constraintAnnotation) {
        // 초기화 로직 (특별히 필요한 경우)
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // userRepository를 사용하여 해당 User ID가 존재하는지 확인
        boolean isValid = userRepository.existsById(value);

        if (!isValid) {
            // 유효하지 않으면 오류 메시지를 설정
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }
}
