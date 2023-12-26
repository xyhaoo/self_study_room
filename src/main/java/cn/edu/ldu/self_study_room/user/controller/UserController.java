package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.dao.NoticeDao;
import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.User;
import cn.edu.ldu.self_study_room.entity.study_room;
import cn.edu.ldu.self_study_room.service.NoticeService;
import cn.edu.ldu.self_study_room.service.impl.study_roomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    NoticeService NoticeService;


    @GetMapping("user/notice")
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
    @GetMapping("user/changepassword")
    public ModelAndView showfavorite(){
        return new ModelAndView("user/changepassword");
    }
    @GetMapping("user/changepassworded")
    public ModelAndView showfavorite(  @RequestParam("username") String username,
                                       @RequestParam("phoneNumber") String phoneNumber,
                                       @RequestParam("password") String password,
                                       @RequestParam("confirmPassword") String confirmPassword,
                                       @RequestParam("gender") String gender){

        System.out.println("用户名：" + username);
        System.out.println("手机号：" + phoneNumber);
        System.out.println("密码：" + password);
        System.out.println("确认密码：" + confirmPassword);
        System.out.println("性别：" + gender);
//        User user = new User(username,password,confirmPassword,phoneNumber,gender);
//



        return new ModelAndView("user/changepassword");
    }
    @GetMapping("user/reseration")
    public ModelAndView reseration(){

        return new ModelAndView("user/reseration");
    }
    @GetMapping("user/forum")
    public ModelAndView fourm(){

        return new ModelAndView("user/forum");
    }
    @GetMapping("user/selfforum")
    public ModelAndView selffourm(){

        return new ModelAndView("user/selfforum");
    }




}
