package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.Favorites;
import cn.edu.ldu.self_study_room.service.impl.FavoritesServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/self_study_room/user")
public class individualController {
    @Autowired
    FavoritesServiceImpl favoritesService;

    @GetMapping("favorite")
    public ModelAndView showdata(HttpSession session){
        ModelAndView m = new ModelAndView("user/favorite");
        try {
            System.out.println(session.getAttribute("user_id"));
            String userId = (String) session.getAttribute("user_id");
            List<Favorites> alls = favoritesService.findAll();
            List<Favorites> results = new ArrayList<>();
            for(Favorites i : alls){
                if(i.getUser_id().equals(userId)){
                    results.add(i);
                }
            }
            m.addObject("alls",results);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return m;
    }
}
