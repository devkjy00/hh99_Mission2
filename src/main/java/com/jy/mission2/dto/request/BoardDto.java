package com.jy.mission2.dto.request;

import com.jy.mission2.dto.DtoMessage;
import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BoardDto {

    @Min(value = 1, message = DtoMessage.WRONG_LAYOUTTYPE)
    @Max(value = 3, message = DtoMessage.WRONG_LAYOUTTYPE)
    @NotBlank(message = DtoMessage.WRONG_LAYOUTTYPE)
    private Integer layoutType;

    private String imgUrl;

    @NotBlank(message = DtoMessage.EMPTY_CONTENT)
    private String content;

    public Board getBoard(User user){
        return Board.builder()
                .user(user)
                .content(content)
                .imgUrl(imgUrl)
                .layoutType(layoutType)
                .likeQty(0)
                .build();
    }
}
