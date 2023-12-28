package cn.edu.ldu.self_study_room.admin.controller;


import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.service.NoticeService;
import cn.edu.ldu.self_study_room.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

//这是通知发布页面，用于管理员发布通知
@RestController
@RequestMapping(value = "/self_study_room/admin/notice_publish")
public class NoticeController {
    @Autowired
    NoticeServiceImpl noticeService;

    //发布通知
    //获取管理员提交的通知信息，插入notice表，返回发布状态
    @PostMapping
    public ModelAndView noticePublish(@RequestParam String notice_title,
                                      @RequestParam String notice_content)
    {
        String notice_id;
        //插入的通知记录，其id是现有通知中id最大值+1，如果当前没有通知，其值为1
        try {
            List<Notice> notices = noticeService.findAll();
            int maxId = 0;
            for (Notice notice : notices) {
                int currentId = Integer.parseInt(notice.getNotice_id());
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
            if (notices.isEmpty()) {
                notice_id = "1";
            } else {
                notice_id = String.valueOf(maxId + 1);
            }
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

        System.out.println(notice_id);
        System.out.println(notice_title);
        System.out.println(notice_content);

        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(currentDateTime);
        String status = noticeService.insert(new Notice(notice_id, notice_title, notice_content, now));
        ModelAndView modelAndView = new ModelAndView("admin/notice_publish");
        modelAndView.addObject("status", status);
        return modelAndView;
    }
}
