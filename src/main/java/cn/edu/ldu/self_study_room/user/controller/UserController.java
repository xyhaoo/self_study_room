package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.service.NoticeService;
import cn.edu.ldu.self_study_room.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/self_study_room/user")
public class UserController {

    @Autowired
    NoticeService NoticeService;
    @Autowired
    UserServiceImpl userService;


    @GetMapping("/notice")
    public ModelAndView shownotice(){
        ModelAndView m = new ModelAndView("user/user_index");
        try {
            List<Notice> alls = NoticeService.findAll();
            m.addObject("alls",alls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return m;
    }
    @GetMapping("/changepassword")
    public ModelAndView showfavorite(){
        return new ModelAndView("user/changepassword");
    }
    @GetMapping("/changepassworded")
    public ModelAndView showfavorite(@RequestParam("username") String username,
                                     @RequestParam("phoneNumber") String phoneNumber,
                                     @RequestParam("password") String password,
                                     @RequestParam("confirmPassword") String confirmPassword,
                                     @RequestParam("gender") String gender, HttpSession session){
        System.out.println(session.getAttribute("user_id"));
        String user_id= (String) session.getAttribute("user_id");
        System.out.println("用户名：" + username);
        System.out.println("手机号：" + phoneNumber);
        System.out.println("密码：" + password);
        System.out.println("确认密码：" + confirmPassword);
        System.out.println("性别：" + gender);

        userService.update(user_id,username,password,phoneNumber,gender);
        return new ModelAndView("user/changepassword");
    }
    @GetMapping("/reseration")
    public ModelAndView reseration(){
        return new ModelAndView("user/reseration");
    }
    @GetMapping("/forum")
    public ModelAndView fourm(){
        return new ModelAndView("user/forum");
    }
    @GetMapping("/selfforum")
    public ModelAndView selffourm(){
        return new ModelAndView("user/selfforum");
    }




}
