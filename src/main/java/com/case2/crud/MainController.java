package com.case2.crud;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String paginaIncial(){

        System.out.println("Main controller");
        return "index";
    }
}
