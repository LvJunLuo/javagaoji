package com.example.esdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class Democontroller {


    @GetMapping("/getHelloDemo")
    @ResponseBody
    public String getHello() {

        ArrayList arrayList = new ArrayList();

        return "hello,world! 190805";
    }
}
