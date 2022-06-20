package com.jy.mission2.dto.request;

import com.jy.mission2.dto.DtoMessage;
import com.jy.mission2.dto.validation.ValidationGroup;
import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import com.jy.mission2.service.AwsS3Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    @Min(value = 1, message = DtoMessage.WRONG_LAYOUTTYPE, groups = {ValidationGroup.Update.class})
    @Max(value = 3, message = DtoMessage.WRONG_LAYOUTTYPE, groups = {ValidationGroup.Update.class})
    @ColumnDefault(value = "1")
    private Integer layoutType;

    @NotBlank(message = DtoMessage.EMPTY_IMG)
    private MultipartFile img;

    @NotBlank(message = DtoMessage.EMPTY_CONTENT)
    private String content;


    public Board getBoard(User user, AwsS3Service awsS3Service) {
        return Board.builder()
                .user(user)
                .content(content)
                .layoutType(layoutType)
                .imgUrl(Objects.nonNull(img)? awsS3Service.uploadFile(img) : null)
                .likeQty(0)
                .build();
    }

}
