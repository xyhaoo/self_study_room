package cn.edu.ldu.self_study_room.admin.controller;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.User;
import cn.edu.ldu.self_study_room.service.impl.NoticeServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//这是管理员登陆后的一级页面，由tab栏分为四个管理项目页面：自习室管理、通知管理、用户管理和论坛管理
//所有的方法都是直接点击
@RestController
@RequestMapping(value = "/self_study_room/admin")
public class AdminController {

    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private UserServiceImpl userService;

    //通知界面
    //管理员到达该url，立即展示所有通知
    @GetMapping(value = "/notice")
    public ModelAndView notice(){
        ModelAndView modelAndView = new ModelAndView("admin/admin_index");
        List<Notice> notices;
        try {
            notices = noticeService.findAll();
            if (notices.isEmpty()){
                modelAndView.addObject("search_failed", "暂时没有通知～");
            }else {
                modelAndView.addObject("search_result", notices);
            }
        }catch (Exception e){
            modelAndView.addObject("search_failed", "查找异常，请再次尝试。如果此问题依然存在请联系开发者！");
        }
        return modelAndView;
    }


    // 删除通知
    @GetMapping(value = "/notice/delete")
    public ModelAndView deleteNotice(@RequestParam("notice_id") String notice_id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/self_study_room/admin/notice");
        modelAndView.addObject("delete_notice_result", noticeService.delete(notice_id));
        return modelAndView;
    }

    //发布通知
    //管理员在通知界面点击按钮，跳转到通知发布页面，进入该页面后，显示发布相关操作
    @GetMapping("/notice_publish")
    public ModelAndView notice_publish(){
        return new ModelAndView("admin/notice_publish");
    }



    //用户列表界面
    //管理员到达该url，立即展示所有用户
    @GetMapping("/user_list")
    public ModelAndView user_list(){
        ModelAndView modelAndView = new ModelAndView("admin/user_list");
        List<User> users;
        try {
            users = userService.findAll();
            if (users.isEmpty()){
                modelAndView.addObject("search_failed", "暂时没有用户信息～");
            }else {
                modelAndView.addObject("search_result", users);
            }
        }catch (Exception e){
            modelAndView.addObject("search_failed", "查找异常，请再次尝试。如果此问题依然存在请联系开发者！");
        }

        return modelAndView;

    }

    // 删除或修改用户信息
    @PostMapping(value = "/user_list")
    public ModelAndView userManage(@RequestParam("choice") String choice,
                                   @RequestParam("user_id") String user_id,
                                   @RequestParam("password") String password,
                                   @RequestParam("user_name") String user_name,
                                   @RequestParam("phone_number") String phone_number,
                                   @RequestParam("sex") String sex)
    {
        System.out.println("hello");
        ModelAndView modelAndView = new ModelAndView("redirect:/self_study_room/admin/user_list");

        if ("delete".equals(choice)){
            modelAndView.addObject("delete_user_result", userService.delete(user_id));
        }else {
            modelAndView.addObject("modify_user_result", userService.update(user_id,user_name,password,phone_number,sex));
        }
        return modelAndView;
    }


    //论坛中心界面 包含对帖子对管理
    @GetMapping("/forum")
    public ModelAndView forum(){
        return new ModelAndView("admin/admin_forum");
    }



    //发帖界面
    @GetMapping("/post_publish")
    public ModelAndView post_publish(){
        return new ModelAndView("post_publish");
    }





}
