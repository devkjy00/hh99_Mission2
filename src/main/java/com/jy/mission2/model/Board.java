package com.jy.mission2.model;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Like like;

    @Column(nullable = false)
    private int layoutType;

    @Column
    private String imgUrl;






}
