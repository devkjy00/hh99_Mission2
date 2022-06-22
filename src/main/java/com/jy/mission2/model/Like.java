package com.jy.mission2.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "like_table")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Boolean likeStatus;

    public Like(User user, Board board) {
        this.user = user;
        this.board = board;
        this.likeStatus = false;
    }

    public Boolean toggle(){
        like_status = !likeStatus;
        return likeStatus;
    }
}
