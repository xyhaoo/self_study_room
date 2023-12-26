package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.study_room;
import cn.edu.ldu.self_study_room.service.impl.study_roomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class study_roomController {
    @Autowired
    study_roomServiceImpl study_roomService;

    @GetMapping("user/self_study_room")
    public ModelAndView showdata(){
        ModelAndView m = new ModelAndView("user/study_room");
        try {
            List<study_room> alls = study_roomService.findAll();
            m.addObject("alls",alls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return m;
    }


}
