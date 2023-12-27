package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.Favorites;
import cn.edu.ldu.self_study_room.service.impl.FavoritesServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
                    String status = favoritesService.findStatus(i.getRoom_id(), i.getSeat_number());
                    i.setStatus(status);
                    if(status==null)
                        i.setStatus("available");
                    results.add(i);
                }
            }
            System.out.println(alls.size());
            System.out.println(results.size());
            m.addObject("alls",results);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return m;
    }

    @GetMapping("save")
    public ModelAndView save(@RequestParam("room_id") int roomId,
                             @RequestParam("seat_number") int seatNumber,
                             @RequestParam("reserve_time") String reserveTime,HttpSession session) {
        ModelAndView m = new ModelAndView("user/favorite");
        try {

            System.out.println(session.getAttribute("user_id"));
            String userId = (String) session.getAttribute("user_id");
            System.out.println(userId+" "+roomId);
            favoritesService.insert(new Favorites(userId,seatNumber,roomId,"1"));
            System.out.println("插入完成");

            List<Favorites> alls = favoritesService.findAll();
            System.out.println(alls.size());
            List<Favorites> results = new ArrayList<>();
            for(Favorites i : alls){
                if(i.getUser_id().equals(userId)){
                    String status = favoritesService.findStatus(i.getRoom_id(), i.getSeat_number());
                    System.out.println(status);
                    i.setStatus(status);
                    if(status==null)
                        i.setStatus("available");

                    results.add(i);
                }
            }
            for(Favorites i : results){
                System.out.println(i);
            }
            m.addObject("alls",results);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return m;
    }
}
