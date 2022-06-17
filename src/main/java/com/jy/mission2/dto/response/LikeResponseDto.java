package com.jy.mission2.dto.response;

import com.jy.mission2.model.Like;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LikeResponseDto {

    private Long userId;
    private Long boardId;

    public LikeResponseDto(Like like){
        this.userId = like.getUser().getId();
        this.boardId = like.getBoard().getId();
    }

    public static List<LikeResponseDto> getLikeTrueList(List<Like> likeList){
        return likeList.stream()
                    .map(LikeResponseDto::new)
                    .collect(Collectors.toList());
    }
}
