package com.jy.mission2.dto.request;

import com.jy.mission2.dto.DtoMessage;
import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import com.jy.mission2.service.AwsS3Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    @Min(value = 1, message = DtoMessage.WRONG_LAYOUTTYPE)
    @Max(value = 3, message = DtoMessage.WRONG_LAYOUTTYPE)
    @NotBlank(message = DtoMessage.WRONG_LAYOUTTYPE)
    private Integer layoutType;

    @NotBlank(message = DtoMessage.EMPTY_IMG)
    private MultipartFile imgUrl;

    @NotBlank(message = DtoMessage.EMPTY_CONTENT)
    private String content;



    public Board getBoard(User user, AwsS3Service awsS3Service){
        return Board.builder()
                .user(user)
                .content(content)
                .layoutType(layoutType)
                .imgUrl(awsS3Service.uploadFile(imgUrl))
                .likeQty(0)
                .build();
    }

}
