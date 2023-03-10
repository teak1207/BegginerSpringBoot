package com.example.firstproject.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 전략  // 자동 생성 전략
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
}
