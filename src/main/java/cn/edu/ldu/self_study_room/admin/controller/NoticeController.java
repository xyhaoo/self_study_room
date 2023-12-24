package cn.edu.ldu.self_study_room.admin.controller;


import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

//这是通知发布页面，用于管理员发布通知
@RestController
@RequestMapping(value = "/self_study_room/admin/notice/publish")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    //发布通知
    //获取管理员提交的通知信息，插入notice表，返回发布状态
    @PostMapping
    public ModelAndView noticePublish(@RequestParam String notice_title,
                                      @RequestParam String notice_content)
    {
        String notice_id;
        try {
            notice_id = String.valueOf(noticeService.findAll().size() + 1);
        }catch (Exception e){
            //findAll方法异常，出现此类型的通知id时，应该修正findAll
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 32; i++) {
                int digit = random.nextInt(10); // 生成 0 到 9 之间的随机数字
                sb.append(digit);
            }
            notice_id = sb.toString();
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(currentDateTime);
        String status = noticeService.insert(new Notice(notice_id, notice_title, notice_content, now));
        ModelAndView modelAndView = new ModelAndView("notice_publish");
        modelAndView.addObject("status", status);
        return modelAndView;
    }
}
