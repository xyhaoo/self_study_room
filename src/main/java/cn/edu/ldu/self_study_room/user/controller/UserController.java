package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Reservation;
import cn.edu.ldu.self_study_room.entity.Seat;
import cn.edu.ldu.self_study_room.service.NoticeService;
import cn.edu.ldu.self_study_room.service.impl.ReservationServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.StudyRoomServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/self_study_room/user")
public class UserController {

    @Autowired
    NoticeService NoticeService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ReservationServiceImpl reservationService;
    @Autowired
    StudyRoomServiceImpl studyRoomService;

    @GetMapping("/notice")
    public ModelAndView shownotice(){
        ModelAndView m = new ModelAndView("user/user_index");
        try {
            List<Notice> alls = NoticeService.findAll();
            m.addObject("alls",alls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return m;
    }

    @GetMapping("/changepassword")
    public ModelAndView showfavorite(){
        return new ModelAndView("user/changepassword");
    }
    @GetMapping("/changepassworded")
    public ModelAndView showfavorite(@RequestParam("username") String username,
                                     @RequestParam("phoneNumber") String phoneNumber,
                                     @RequestParam("password") String password,
                                     @RequestParam("confirmPassword") String confirmPassword,
                                     @RequestParam("gender") String gender, HttpSession session){
        System.out.println(session.getAttribute("user_id"));
        String user_id= (String) session.getAttribute("user_id");
        System.out.println("用户名：" + username);
        System.out.println("手机号：" + phoneNumber);
        System.out.println("密码：" + password);
        System.out.println("确认密码：" + confirmPassword);
        System.out.println("性别：" + gender);

        userService.update(user_id,username,password,phoneNumber,gender);
        return new ModelAndView("user/changepassword");
    }
    @GetMapping(value = "/reseration", params = {"datetime", "roomId", "seatNumber"})
    public ModelAndView reseration(@RequestParam("datetime")   String datetime,
                                   @RequestParam("roomId") int roomId,
                                   @RequestParam("seatNumber") int seatNumber,HttpSession session) {
        // 在这里处理接收到的参数
        // 可以将参数存储到数据库或进行其他业务逻辑处理
        // 然后返回相应的ModelAndView

        ModelAndView modelAndView = new ModelAndView("user/reseration");


        // 进行其他操作
        String user_id = (String) session.getAttribute("user_id");
        System.out.println(user_id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date datetimes;
        try {
            datetimes = dateFormat.parse(datetime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
       reservationService.insert(new Reservation(user_id,roomId,seatNumber,datetimes));
        List<Reservation> search_result;
        try {
            search_result = reservationService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        session.setAttribute("seatList",search_result);
        Integer page_size = (search_result.size() % 4 == 0) ? search_result.size() / 4 : (search_result.size() / 4) + 1;
        session.setAttribute("page_size",page_size);

        modelAndView.addObject("page_size",page_size);
        List<Reservation> four_seat=new ArrayList<>();
        if(1!=page_size){
            four_seat=search_result.subList(1*4-4,1*4);
        }else{
            four_seat=search_result.subList(1*4-4,search_result.size());
        }


        modelAndView.addObject("search_result",four_seat);


//        modelAndView.addObject("search_result",search_result);
        return modelAndView;
    }

    @GetMapping("/reseration")
    public ModelAndView reservationWithoutParams(HttpSession session,@RequestParam int page_number) {
        // 处理不带参数的逻辑
        // ...
        ModelAndView modelAndView = new ModelAndView("user/reseration");

        Integer page_size= (Integer) session.getAttribute("page_size");
        modelAndView.addObject("page_size",page_size);
        List<Reservation> seatList= (List<Reservation>) session.getAttribute("seatList");
        List<Reservation> four_seat=new ArrayList<>();
        if(page_number!=page_size){
            four_seat=seatList.subList(page_number*4-4,page_number*4);
        }else{
            four_seat=seatList.subList(page_number*4-4,seatList.size());

        }

        modelAndView.addObject("search_result",four_seat);

        return modelAndView;
    }




    @GetMapping("/forum")
    public ModelAndView fourm(){
        return new ModelAndView("user/forum");
    }
    @GetMapping("/selfforum")
    public ModelAndView selffourm(){
        return new ModelAndView("user/selfforum");
    }


    @GetMapping("/Contreteroom")
    public ModelAndView showroom(@RequestParam("room_id") int room_id,
                                 @RequestParam("roomContent") String roomContent,
                                 @RequestParam("roomPicture") String roomPicture,
                                 @RequestParam("roomVolume") int roomVolume)
    {
        ModelAndView modelAndView = new ModelAndView("user/Contrete_room");
        //希望有一个方法 根据room 查到seat 的状态
        try {

            List<Seat> findstautsbyid = studyRoomService.findstautsbyid(room_id);

            modelAndView.addObject("findstautsbyid",findstautsbyid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return modelAndView;
    }


}
