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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOARD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "board", orphanRemoval = true)
    private Like like;

    @Column(nullable = false)
    private Integer layoutType;

    @Column
    private String imgUrl;

    @Column
    private String content;

    public void update(BoardDto requestDto){
        String content = requestDto.getContent();
        String imgUrl = requestDto.getImgUrl();
        Integer layoutType = requestDto.getLayoutType();

        this.content = Objects.nonNull(content)? content : this.content;
        this.imgUrl = Objects.nonNull(imgUrl)? imgUrl : this.imgUrl;
        this.layoutType = Objects.nonNull(layoutType)? layoutType : this.layoutType;
    }
}

