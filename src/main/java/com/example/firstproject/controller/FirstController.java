package com.example.firstproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {


    @GetMapping("/hi")
    public String greetings(Model model) {

        model.addAttribute("username", "사막");

        return "greetings";   // templates/greetings.mustache   -> 브라우저로 전송
    }

    @GetMapping("/bye")
    public String GoodByeGreetings(Model model) {

        model.addAttribute("username", "오아시스");

        return "GoodBye";   // templates/greetings.mustache   -> 브라우저로 전송
    }


}
