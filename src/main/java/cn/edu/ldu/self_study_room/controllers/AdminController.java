package cn.edu.ldu.self_study_room.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello,world";
    }
}
