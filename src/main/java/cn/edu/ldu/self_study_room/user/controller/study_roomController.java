package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.StudyRoom;
import cn.edu.ldu.self_study_room.service.impl.StudyRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class study_roomController {
    @Autowired
    StudyRoomServiceImpl study_roomService;

    @GetMapping("user/self_study_room")
    public ModelAndView showdata(){
        ModelAndView m = new ModelAndView("user/study_room");
        try {
            List<StudyRoom> alls = study_roomService.findAll();
            m.addObject("alls",alls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return m;
    }


}
