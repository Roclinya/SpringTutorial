package com.tutorial.SpringTutorial;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {
	//透過 @RequestMapping 指定從/會被對應到此hello()方法
  @RequestMapping("/")
  public String hello(){
      return "向全世界說聲Spring Boot 很高興認識你!";
  }

}