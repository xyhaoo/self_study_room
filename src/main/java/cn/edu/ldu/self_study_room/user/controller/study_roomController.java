package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.StudyRoom;
import cn.edu.ldu.self_study_room.service.impl.StudyRoomServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/self_study_room/user")
public class study_roomController {
    @Autowired
    StudyRoomServiceImpl study_roomService;

    @GetMapping("self_study_roompage")
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
