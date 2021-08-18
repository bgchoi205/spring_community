package com.cbg.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name="body", nullable = false)
    private String body;

    @Builder
    public Article(Long memberId, Long boardId, String title, String body){
        this.memberId = memberId;
        this.boardId = boardId;
        this.title = title;
        this.body = body;
    }
}
