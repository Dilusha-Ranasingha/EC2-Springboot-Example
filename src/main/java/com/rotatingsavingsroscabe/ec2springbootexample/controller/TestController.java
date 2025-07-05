package com.rotatingsavingsroscabe.ec2springbootexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    //this is a test endpoint
    @GetMapping
    public String test(){
        return "Hellow Springboot EC2 Example";
    }

    //this is a another test endpoint
    @GetMapping("/test")
    public String test2(){
        return "Hellow Springboot EC2 Example 2";
    }

}
