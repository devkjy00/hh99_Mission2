package com.jy.mission2.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class BoardDto {

    @Min(1)
    @Max(3)
    @NotBlank
    private int layoutType;

    private String imgUrl;

    @NotBlank
    private String content;

}
