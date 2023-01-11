package com.example.firstproject.dto;


import com.example.firstproject.entity.Article;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

/*    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    */

    public Article toEntity() {
        return new Article(id, title, content);
    }
}

