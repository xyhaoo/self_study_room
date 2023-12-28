package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.Favorites;
import cn.edu.ldu.self_study_room.entity.Reservation;
import cn.edu.ldu.self_study_room.service.impl.FavoritesServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.ReservationServiceImpl;
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


    @Autowired
    ReservationServiceImpl reservationService;
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

    @GetMapping("deletes")
    public ModelAndView delete(@RequestParam("room_idDe") int roomId,
                             @RequestParam("seat_numberDe") int seatNumber,
                           HttpSession session) {
        System.out.println("jinru");
        ModelAndView m = new ModelAndView("user/reseration");

        try {

            System.out.println(session.getAttribute("user_id"));
            String userId = (String) session.getAttribute("user_id");
            System.out.println(userId+" "+roomId);
            reservationService.delete(userId,roomId,seatNumber);
            System.out.println("删除完成");

            List<Reservation> search_result = reservationService.findAll();
            session.setAttribute("seatList",search_result);
            Integer page_size = (search_result.size() % 4 == 0) ? search_result.size() / 4 : (search_result.size() / 4) + 1;
            session.setAttribute("page_size",page_size);

            m.addObject("page_size",page_size);
            List<Reservation> four_seat=new ArrayList<>();
            if(1!=page_size){
                four_seat=search_result.subList(1*4-4,1*4);
            }else{
                four_seat=search_result.subList(1*4-4,search_result.size());

            }


            m.addObject("search_result",four_seat);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return m;
    }

    @GetMapping("deletefavorite")
    public ModelAndView deletefavorite(@RequestParam("room_id") int room_id,
                               @RequestParam("seat_number") int seat_number,
                               HttpSession session) {
        ModelAndView m = new ModelAndView("user/favorite");
        try {

            System.out.println(session.getAttribute("user_id"));
            String userId = (String) session.getAttribute("user_id");
            System.out.println(userId+" "+ room_id);
             favoritesService.delete(userId, room_id, seat_number);
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
