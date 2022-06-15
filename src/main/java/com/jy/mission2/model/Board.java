package com.jy.mission2.model;


import com.jy.mission2.dto.BoardDto;
import com.jy.mission2.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer layoutType;

    @Column
    private String imgUrl;

    @Column
    private String content;

    public Board(BoardDto requestDto, User user){
         this.user = user;
         this.content = requestDto.getContent();
         this.imgUrl = requestDto.getImgUrl();
         this.layoutType = requestDto.getLayoutType();
    }

    public void update(BoardDto requestDto){
        String content = requestDto.getContent();
        String imgUrl = requestDto.getImgUrl();
        Integer layoutType = requestDto.getLayoutType();


        this.content = Objects.nonNull(content)? content : this.content;
        this.imgUrl = Objects.nonNull(imgUrl)? imgUrl : this.imgUrl;
        this.layoutType = Objects.nonNull(layoutType)? layoutType : this.layoutType;
    }
}

