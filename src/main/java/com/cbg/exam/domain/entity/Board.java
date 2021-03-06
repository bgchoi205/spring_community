package com.cbg.exam.domain.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="board")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Article> articles;

    @Builder
    public Board(String name){
        this.name = name;
    }

    public void changeName(String newBoardName){
        this.name = newBoardName;
    }
}
