package com.jy.mission2.dto;


import com.jy.mission2.model.Board;
import com.jy.mission2.model.Like;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardResponseDto {
    private String nickname;
    private String content;
    private String imgUrl;
    private Integer layoutType;
    private List<LikeResponseDto> likes;

    public BoardResponseDto(Board board) {
        this.content = board.getContent();
        this.imgUrl = board.getImgUrl();
        this.layoutType = board.getLayoutType();
        this.nickname = board.getUser().getNickname();
        this.likes = LikeResponseDto.getLikeTrueList(board.getLikeList());
    }

    public static List<BoardResponseDto> getDtoList(List<Board> boardList){

        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }

        return responseDtoList;
    }
}
