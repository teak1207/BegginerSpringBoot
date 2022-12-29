package com.example.firstproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

}
