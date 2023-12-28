package cn.edu.ldu.self_study_room.user.controller;

import cn.edu.ldu.self_study_room.entity.*;
import cn.edu.ldu.self_study_room.service.NoticeService;
import cn.edu.ldu.self_study_room.service.impl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private PostServiceImpl postService;
    @Autowired
    NoticeService NoticeService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ReservationServiceImpl reservationService;
    @Autowired
    StudyRoomServiceImpl studyRoomService;
    @Autowired
    SeatServiceImpl seatService;

    @Autowired
    private CommentServiceImpl commentService;
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
//        //修改状态
      //  seatService.insert(roomId,seatNumber,"2");
        seatService.update(seatNumber,"2");
       reservationService.insert(new Reservation(user_id,roomId,seatNumber,datetimes));


        List<Reservation> search_result;
        try {
            search_result = reservationService.findAll(user_id);
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
        Date currentDate = new Date();
        long oneDayInMillis = 24 * 60 * 60 * 1000; // 一天的毫秒数
        List<Integer> overtime = new ArrayList<Integer>();
        for (Reservation reservation : four_seat) {
            System.out.println("---------");
            System.out.println(reservation.getReserve_time());
            if (currentDate.getTime() - reservation.getReserve_time().getTime() > oneDayInMillis) {
                System.out.println("超过一天");
                System.out.println(reservation.getSeat_number());
                System.out.println(reservation.getReserve_time());
                overtime.add(reservation.getSeat_number());
            }
        }

        modelAndView.addObject("overtime",overtime);
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
        Date currentDate = new Date();
        long oneDayInMillis = 24 * 60 * 60 * 1000; // 一天的毫秒数
        List<Integer> overtime = new ArrayList<Integer>();
        for (Reservation reservation : four_seat) {
            System.out.println("---------");
            System.out.println(reservation.getReserve_time());
            if (currentDate.getTime() - reservation.getReserve_time().getTime() > oneDayInMillis) {
                System.out.println("超过一天");
                System.out.println(reservation.getSeat_number());
                System.out.println(reservation.getReserve_time());
                overtime.add(reservation.getSeat_number());
            }
        }

        modelAndView.addObject("overtime",overtime);
        modelAndView.addObject("search_result",four_seat);

        return modelAndView;
    }




//    @GetMapping("/forum")
//    public ModelAndView fourm(){
//        return new ModelAndView("user/forum");
//    }
//    @GetMapping("/selfforum")
//    public ModelAndView selffourm(){
//        return new ModelAndView("user/selfforum");
//    }


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


    @GetMapping("/seachNotice")
    public ModelAndView seachNotice(HttpSession session,@RequestParam String notice_titile) {
        // 处理不带参数的逻辑
        // ...
        ModelAndView modelAndView = new ModelAndView("user/user_index");
        try {
            List<Notice> findbutitile = NoticeService.findbutitile(notice_titile);
            modelAndView.addObject("alls",findbutitile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return modelAndView;
    }



    @GetMapping("/forum")
    public ModelAndView forum(){
        ModelAndView modelAndView = new ModelAndView("user/forum");

        List<Post> posts;
        try {
            posts = postService.findAll();
            if (posts.isEmpty()){
                modelAndView.addObject("search_failed", "暂时没有用户发布帖子～");
            }else {
                modelAndView.addObject("search_result", posts);
                modelAndView.addObject("post_num", posts.size());
                int resolved_num = 0;
                for (Post p : posts){
                    if("resolved".equals(p.getPost_status())){
                        resolved_num++;
                    }
                }
                modelAndView.addObject("resolved_num", resolved_num);
            }
        }catch (Exception e){
            modelAndView.addObject("search_failed", "查找异常，请再次尝试。如果此问题依然存在请联系开发者！");
        }

        return modelAndView;
    }


    //帖子详细信息界面
    //在论坛点击某个帖子进行跳转
    @GetMapping("/forum/detail/{post_id}")
    public ModelAndView forum_detail(@PathVariable String post_id,HttpSession session){
        ModelAndView modelAndView = new ModelAndView("user/post_detail");
        Post post = postService.searchByPostId(post_id);
        List<Comment> comments = commentService.findCommentByPostId(post_id);
        modelAndView.addObject("post",post);    //当前帖子的详细信息
        modelAndView.addObject("comments", comments);   //当前帖子的所有评论
        //这里需要获取当前用户id
        //Admin可以直接获得（id为003），user没写，user可以在a标签传递th：value，之后在url中展现，最后用@Path那个注解接收
        //具体url格式为：/forum/detail/{user_id}/{post_id}
        String user_id = "003";
        String user_od = (String) session.getAttribute("user_id");
        //判断被查看详细信息打开的帖子是不是当前用户的,方便对帖子发起者有筛选优质评论的权限
        if (user_id.equals(post.getUser_id())){
            modelAndView.addObject("is_your_post", "true");
        }else {
            modelAndView.addObject("is_your_post", "false");
        }

        return modelAndView;
    }


}
