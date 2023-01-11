package com.example.firstproject.controller;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "article/new";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {

        log.info("show id : {}", id);
        // 1. id로 데이터를 가져옴

        // 1_1
        Article articleEntity1 = articleRepository.findById(id).orElse(null);
        // 1_2
        Optional<Article> articleEntity2 = articleRepository.findById(id);

        // 2. 가져온 데이터를  모델에 등록
        model.addAttribute("article", articleEntity1);

        // 3. 보여줄 페이지를 설정
//        return "article/index";
        return "article/show";
    }


    @RequestMapping(value = "/articles/create", method = RequestMethod.GET)
    public String createArticle(ArticleForm form) {
        log.info("title : {}", form.getTitle());
        log.info("content : {}", form.getContent());

        //dto --> entity
        Article article = form.toEntity();
        log.info("article : {}", article.toString());

        //repository에게 Entity를 디비안에 저장
        Article saved = articleRepository.save(article);

        log.info("saved : {}", saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @RequestMapping(value = "/articles/", method = RequestMethod.GET)
    public String index(Model model) {

        //1. 모든 아티클을 가져온다
        // 방법1
        List<Article> articleList1 = (List<Article>) articleRepository.findAll();
        // 방법2
        List<Article> articleList2 = articleRepository.findAll();
        // 방법3
        Iterable<Article> articleList3 = (List<Article>) articleRepository.findAll();

        //2. 가져온 아티클 묶음을 뷰로 전달

        model.addAttribute("articleList", articleList2);


        //3. 뷰페이지를 설정한다

        return "article/index";
    }


    @RequestMapping(value = "/articles/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {

        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //뷰페이지에서 설정
        model.addAttribute("article", articleEntity);
        return "article/edit";
    }

    @RequestMapping(value = "/articles/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {

        //1. 삭제 대상을 가져온다.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info("articleEntity DEL - {}", articleEntity);
        //2. 대상을 삭제한다.


        if(articleEntity != null) {
            articleRepository.delete(articleEntity);

            rttr.addFlashAttribute("msg","삭제가 완료되었습니다");
        }

        //3. 결과 페이지로 리다이렉트.




        return "redirect:/articles/";
    }


    @RequestMapping(value = "/articles/update", method = RequestMethod.POST)
    public String update(ArticleForm form) {

        log.info(form.toString());

        //1. DTO를 엔티티로 변환한다.
        Article article = form.toEntity();
        //2. 엔티티를 DB로 저장한다
        //2.1  DB에서 기존 데이터를 가져온다.
        Optional<Article> target1 = articleRepository.findById(article.getId());
        Article target2 = articleRepository.findById(article.getId()).orElse(null);

        //2.2 기존 데이터에 값을 갱신한다
        if (target2 != null) {
            articleRepository.save(article);
        }

        //3. 수정결과 페이지로 리다이렉트한다.

        return "redirect:/articles/" + article.getId();
    }


}
