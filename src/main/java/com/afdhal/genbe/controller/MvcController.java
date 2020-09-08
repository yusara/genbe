package com.afdhal.genbe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MvcController {
    @GetMapping("form1")
    public String form1(){
        return "form/form1";
    }

    @GetMapping("form2")
    public String form2(){
        return "form/form2";
    }

    @GetMapping("form3")
    public String form3(){
        return "form/form3";
    }

    @GetMapping("formnik")
    public String formnik(){
        return "form/biodatabynik";
    }
}
