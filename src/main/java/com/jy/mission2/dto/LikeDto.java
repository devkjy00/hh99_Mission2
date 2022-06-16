package com.jy.mission2.dto;

import com.jy.mission2.model.Like;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

// POST 가 없어서 사용 하지 않는 Dto
@Getter
public class LikeDto {

    @NotBlank
    private Long userId;

    @NotBlank
    private Long boardId;

    @NotBlank
    private Boolean like;

}
