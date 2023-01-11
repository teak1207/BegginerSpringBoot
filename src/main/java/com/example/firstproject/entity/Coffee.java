package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 전략  // 자동 생성 전략
    private Long id;

    @Column
    private String name;

    @Column
    private int price;
}
