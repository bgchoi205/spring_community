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

    @Column(name="member_id", nullable = false)
    private Long memberId;

    @Column(name="board_id", nullable = false)
    private Long boardId;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="article_html", columnDefinition = "LONGTEXT", nullable = false)
    private String articleHtml;

    @Column(name="article_md", columnDefinition = "LONGTEXT", nullable = false)
    private String articleMD;

//    @Column(name="reg_date", nullable = false)
//    @NonNull
//    private LocalDateTime regDate;
//
//    @Column(name="update_date", nullable = false)
//    @NonNull
//    private LocalDateTime updateDate;



    @Builder
    public Article(Long memberId, Long boardId, String title, String articleHtml, String articleMD){

        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.articleHtml = articleHtml;
        this.articleMD = articleMD;
    }
}
