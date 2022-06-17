package com.jy.mission2.model;


import com.jy.mission2.dto.request.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "board", orphanRemoval = true)
    private List<Like> likeList;

    @ColumnDefault(value = "0")
    private Integer likeQty;

    @Column(nullable = false)
    private Integer layoutType;

    @Column
    private String imgUrl;

    @Column
    private String content;

    public void updateFields(BoardDto requestDto){
        String content = requestDto.getContent();
        String imgUrl = requestDto.getImgUrl();
        Integer layoutType = requestDto.getLayoutType();

        this.content = Objects.nonNull(content)? content : this.content;
        this.imgUrl = Objects.nonNull(imgUrl)? imgUrl : this.imgUrl;
        this.layoutType = Objects.nonNull(layoutType)? layoutType : this.layoutType;
    }

    public void updateLikeQty(int num){
        likeQty += num;
    }
}

