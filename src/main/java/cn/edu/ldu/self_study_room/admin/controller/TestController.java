package cn.edu.ldu.self_study_room.admin.controller;

import cn.edu.ldu.self_study_room.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping()
    public ModelAndView notice_publish(){
        return new ModelAndView("admin/admin_index");
    }

}
