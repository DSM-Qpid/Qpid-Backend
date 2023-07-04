package com.example.qpid.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {
    @NotBlank(message = "accountId은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 8, message = "accountId는 8자 이상이어야 합니다")
    private String accountId;

    @NotBlank(message = "password는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Pattern(
            regexp =
                    "(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;<=>?@＼^_`{|}~]{8,32}$",
            message = "password는 영문, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "name는 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 2,max = 5,message = "name은 2자 ~ 5자 사이여야 합니다.")
    private String name;
}
