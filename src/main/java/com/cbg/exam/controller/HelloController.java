package com.cbg.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hihihi");
        return "hello";
    }

    @GetMapping("haha")
    public String haha(Model model){
        model.addAttribute("introduce", "hi yo");
        return "haha";
    }

    @GetMapping("hello-mvc")
    public String helloParam(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-mvc";
    }
}
