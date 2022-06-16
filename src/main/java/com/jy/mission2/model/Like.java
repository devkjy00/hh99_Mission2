package com.jy.mission2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "like_table")
@AllArgsConstructor
@NoArgsConstructor
public class Like extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LIKE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column(nullable = false)
    private Boolean like_status;

    public Like(User user, Board board) {
        this.user = user;
        this.board = board;
        this.like_status = false;
    }

    public Boolean toggle(){
        like_status = !like_status;
        return like_status;
    }
}
