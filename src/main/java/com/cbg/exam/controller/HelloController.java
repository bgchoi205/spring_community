package com.cbg.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name, @RequestParam("age") int age){
        Hello hello = new Hello();
        hello.setName(name);
        hello.setAge(age);
        return hello;
    }

    static class Hello{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
