package com.jy.mission2.model;


import com.jy.mission2.dto.BoardDto;
import com.jy.mission2.security.UserDetailsImpl;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Builder
@DynamicInsert
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", orphanRemoval = true)
    private List<Like> likeList;

    @ColumnDefault(value = "0")
    private Integer likeQty;

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

