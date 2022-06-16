package com.jy.mission2.dto;


import com.jy.mission2.model.Board;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardResponseDto {
    private String nickname;
    private String content;
    private String imgUrl;
//    private int likes;
    private Integer layoutType;

    public BoardResponseDto(Board board) {
        this.content = board.getContent();
        this.imgUrl = board.getImgUrl();
        this.layoutType = board.getLayoutType();
        this.nickname = board.getUser().getNickname();
    }

    public static List<BoardResponseDto> getDtoList(List<Board> boardList){

        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }

        return responseDtoList;
    }
}
