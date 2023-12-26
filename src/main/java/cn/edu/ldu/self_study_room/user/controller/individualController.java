package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.Favorites;
import cn.edu.ldu.self_study_room.service.impl.FavoritesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class individualController {
    @Autowired
    FavoritesServiceImpl favoritesService;

    @GetMapping("user/favorite")
    public ModelAndView showdata(){
        ModelAndView m = new ModelAndView("user/favorite");
        try {
            List<Favorites> alls = favoritesService.findAll();
            m.addObject("alls",alls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return m;
    }
}
