package com.jy.mission2.dto;

import com.jy.mission2.model.Like;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LikeResponseDto {

    private Long userId;
    private Long boardId;

    public LikeResponseDto(Like like){
        this.userId = like.getUser().getId();
        this.boardId = like.getBoard().getId();
    }

    public static List<LikeResponseDto> getLikeTrueList(List<Like> likeList){
        List<LikeResponseDto> likeResponseDtoList = new ArrayList<>();
        for(Like like: likeList){
            if(like.getLike_status()){
                likeResponseDtoList.add(new LikeResponseDto(like));
            }
        }

        return likeResponseDtoList;
    }
}
