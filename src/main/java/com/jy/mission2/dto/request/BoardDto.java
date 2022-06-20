package com.jy.mission2.dto.request;

import com.jy.mission2.dto.DtoMessage;
import com.jy.mission2.dto.validation.ValidationGroup;
import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class BoardDto {

    @Min(value = 1, message = DtoMessage.WRONG_LAYOUTTYPE, groups = {ValidationGroup.Update.class})
    @Max(value = 3, message = DtoMessage.WRONG_LAYOUTTYPE, groups = {ValidationGroup.Update.class})
    @ColumnDefault(value = "1")
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
