package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.favorites;
import cn.edu.ldu.self_study_room.service.impl.favoritesServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.study_roomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class individualController {
    @Autowired
    favoritesServiceImpl favoritesService;

    @GetMapping("user/favorite")
    public ModelAndView showdata(){
        ModelAndView m = new ModelAndView("user/favorite");
        try {
            List<favorites> alls = favoritesService.findAll();
            m.addObject("alls",alls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return m;
    }
}
