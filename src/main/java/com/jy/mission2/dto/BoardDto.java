package com.jy.mission2.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class BoardDto {

    @Min(value = 1, message = DtoMessage.WRONG_LAYOUTTYPE)
    @Max(value = 3, message = DtoMessage.WRONG_LAYOUTTYPE)
    @NotBlank(message = DtoMessage.WRONG_LAYOUTTYPE)
    private Integer layoutType;

    private String imgUrl;

    @NotBlank(message = DtoMessage.EMPTY_CONTENT)
    private String content;

}
