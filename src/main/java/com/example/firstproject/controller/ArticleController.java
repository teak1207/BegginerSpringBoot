package com.example.firstproject.controller;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "article/new";
    }

    @RequestMapping(value = "/articles/create", method = RequestMethod.POST)
    public String createArticle(ArticleForm form) {
        log.info("test" + form.toString());
        //dto --> entity
        Article article = form.toEntity();
        log.info(article.toString());

        //repository에게 Entity를 디비안에 저장

        Article saved = articleRepository.save(article);

        System.out.println(saved.toString());

        return "";
    }

}
