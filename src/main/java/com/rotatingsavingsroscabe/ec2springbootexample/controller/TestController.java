package com.rotatingsavingsroscabe.ec2springbootexample.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5174")  // Allow frontend running here
public class TestController {


    //this is a test endpoint
    @GetMapping
    public String test(){
        return "Space EC2" +
                "This message is from Spring Boot backend running on EC2" +
                "repo url: https://github.com/Dilusha-Ranasingha/EC2-Springboot-Example.git";
    }

    //this is a another test endpoint
    @GetMapping("/test")
    public String test2(){
        return "Hellow Springboot EC2 Example 2";
    }

}
