package com.cbg.exam.domain.entity;

import com.cbg.exam.domain.BaseTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @Column(name="title")
    private String title;

    @Column(name="article_html", columnDefinition = "LONGTEXT")
    private String articleHtml;

    @Column(name="article_md", columnDefinition = "LONGTEXT")
    private String articleMD;

//    @Column(name="reg_date", nullable = false)
//    @NonNull
//    private LocalDateTime regDate;
//
//    @Column(name="update_date", nullable = false)
//    @NonNull
//    private LocalDateTime updateDate;



    @Builder
    public Article(Member member, Board board, String title, String articleHtml, String articleMD){

        this.member = member;
        this.board = board;
        this.title = title;
        this.articleHtml = articleHtml;
        this.articleMD = articleMD;
    }
}
