package com.cbg.exam.domain.entity;

import com.cbg.exam.domain.BaseTime;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Table(name="article")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @Column(name="title")
    private String title;

    @Column(name="article_md", columnDefinition = "LONGTEXT")
    private String articleMD;


    @Builder
    public Article(Member member, Board board, String title, String articleMD){

        this.member = member;
        this.board = board;
        this.title = title;
        this.articleMD = articleMD;
    }

    public void modifyArticle(String title, String articleMD, Board board){
        this.title = title;
        this.articleMD = articleMD;
        this.board = board;
    }
}
