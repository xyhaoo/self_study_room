package cn.edu.ldu.self_study_room.admin.controller;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//这是管理员登陆后的一级页面，由tab栏分为四个管理项目页面：自习室管理、通知管理、用户管理和论坛
//所有的方法都是直接点击
@RestController
@RequestMapping(value = "/self_study_room/admin")
public class AdminController {

    @Autowired
    private NoticeService noticeService;

    //通知界面
    //管理员到达该url，立即展示所有通知
    @GetMapping(value = "/notice")
    public ModelAndView notice(){
        ModelAndView modelAndView = new ModelAndView("notice");
        List<Notice> notices = new ArrayList<>();
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

    //发布通知
    //管理员在通知界面点击按钮，跳转到通知发布页面，进入该页面后，显示发布相关操作
    @GetMapping("/notice/publish")
    public ModelAndView notice_publish(){
        return new ModelAndView("notice_publish");
    }

}
