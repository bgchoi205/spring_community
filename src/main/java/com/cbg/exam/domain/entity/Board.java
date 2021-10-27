package com.cbg.exam.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="member")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    List<Article> articles;

    @Builder
    public Board(String name){
        this.name = name;
    }
}
