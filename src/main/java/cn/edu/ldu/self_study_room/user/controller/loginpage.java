package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.Favorites;
import cn.edu.ldu.self_study_room.entity.Reservation;
import cn.edu.ldu.self_study_room.entity.User;
import cn.edu.ldu.self_study_room.service.UserService;
import cn.edu.ldu.self_study_room.service.impl.FavoritesServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.ReservationServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/self_study_room/")
public class loginpage {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ReservationServiceImpl reservationService;

    @GetMapping("login")
    public ModelAndView showdata(){
        return new ModelAndView("login");
    }

    @GetMapping("loading")
    public ModelAndView loading(@RequestParam("user_id") String user_id, @RequestParam("password") String password, HttpSession session) {
        List<Reservation> search_result;
        try {
            search_result = reservationService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("seatList",search_result);
        Integer page_size = (search_result.size() % 4 == 0) ? search_result.size() / 4 : (search_result.size() / 4) + 1;
        session.setAttribute("page_size",page_size);

        // 在这里可以使用获取到的属性值进行处理
        System.out.println("Username: " + user_id);
        System.out.println("Password: " + password);

        if(user_id.equals("admin") && password.equals("123456")){
            ModelAndView modelAndView = new ModelAndView("admin/admin_index");
            modelAndView.addObject("cur_user", userService.findNameById(user_id));
            return modelAndView;
        }

        try {
            for(User i : userService.findAll()){
                if(user_id.equals(i.getUser_id()) && password.equals(i.getPassword())){


                    session.setAttribute("user_id", user_id);
                    return new ModelAndView("user/user_index");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("login");
    }
}