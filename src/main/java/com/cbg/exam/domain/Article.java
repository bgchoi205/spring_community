package com.cbg.exam.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="article")
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name="board_id", nullable = false)
    private Long boardId;

    @Column(name="reg_date", nullable = false)
    private String regDate;

    @Column(name="update_date", nullable = false)
    private String updateDate;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="body", nullable = false)
    private String body;

    @Builder
    public Article(Long memberId, Long boardId, String regDate, String updateDate, String title, String body){
        this.memberId = memberId;
        this.boardId = boardId;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.title = title;
        this.body = body;
    }
}
