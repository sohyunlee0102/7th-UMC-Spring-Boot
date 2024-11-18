package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMissionAssigned;
import umc.spring.validation.annotation.ExistUser;

@Getter
public class MissionAssignedRequestDTO {

 //   @Getter
    @ExistMissionAssigned
    public static class JoinDto {

        @ExistUser
        @NotNull// 사용자 ID 검증 애노테이션
        Long userId;

         public Long getUserId() {
             return userId;
         }
    }

}
