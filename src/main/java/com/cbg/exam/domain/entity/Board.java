package com.cbg.exam.domain.entity;

import lombok.*;

import javax.persistence.*;

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

    @Builder
    public Board(String name){
        this.name = name;
    }
}
