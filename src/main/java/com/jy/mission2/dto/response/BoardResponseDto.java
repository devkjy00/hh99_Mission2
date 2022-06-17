package com.jy.mission2.dto.response;


import com.jy.mission2.model.Board;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponseDto {
    private Long userId;
    private String nickname;
    private String content;
    private String imgUrl;
    private Integer layoutType;
    private List<LikeResponseDto> likes;

    public BoardResponseDto(Board board) {
        this.userId = board.getUser().getId();
        this.content = board.getContent();
        this.imgUrl = board.getImgUrl();
        this.layoutType = board.getLayoutType();
        this.nickname = board.getUser().getNickname();
        this.likes = LikeResponseDto.getLikeTrueList(board.getLikeList());
    }

    public static List<BoardResponseDto> getDtoList(List<Board> boardList){
        return  boardList.stream()
                        .map(BoardResponseDto::new)
                        .collect(Collectors.toList());

    }
}
