package com.jy.mission2.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Like extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long boardId;

    @Column(nullable = false)
    private Long userId;
}
