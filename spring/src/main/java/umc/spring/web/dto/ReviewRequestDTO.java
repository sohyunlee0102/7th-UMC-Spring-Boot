package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistUser;

public class ReviewRequestDTO {

    @Getter
    public static class JoinDto {
        @Size(min = 5, max = 1000)
        String content;

        @NotNull
        Float rating;

        @ExistUser// 사용자 ID 검증 애노테이션
        Long userId;  // user ID는 Long 타입으로 수정
    }

}
