package com.example.qpid.domain.feed.presentation.dto.request;

import com.example.qpid.domain.feed.domain.type.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateFeedRequest {
    @NotBlank(message = "title은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Size(max = 20, message = "title는 20자 이하이어야 합니다")
    private String title;

    @NotBlank(message = "content은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Size(max = 1000, message = "content는 1000자 이하이어야 합니다")
    private String content;

    @NotBlank
    private Tag tag;
}
