package com.fastcampus.ch4.controller;
import com.fastcampus.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

//사용하려면 Maven Dependency에서 Jackson Databind 추가 필요

@Controller
//@RestController
public class SimpleRestController {
//    @GetMapping("/ajax")
//    public String ajax() {
//        return "ajax";
//    }
	
  @GetMapping("/test")
  public String ajax() {
      return "test";
  }

    @PostMapping("/send")
//    @ResponseBody
    public Person test(@RequestBody Person p) {
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p;
    }
    
}