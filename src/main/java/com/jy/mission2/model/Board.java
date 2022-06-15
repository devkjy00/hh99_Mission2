package com.jy.mission2.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
    private int layoutType;

    @Column
    private String imgUrl;

    @Column
    private String content;

    @Builder
    public Board(int layoutType, String imgUrl, String content) {
        this.layoutType = layoutType;
        this.imgUrl = imgUrl;
        this.content = content;
    }
}
