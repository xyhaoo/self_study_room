package cn.edu.ldu.self_study_room.admin.controller;

import cn.edu.ldu.self_study_room.entity.*;
import cn.edu.ldu.self_study_room.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//这是管理员登陆后的一级页面，由tab栏分为四个管理项目页面：自习室管理、通知管理、用户管理和论坛管理
//所有的方法都是直接点击
@RestController
@RequestMapping(value = "/self_study_room/admin")
public class AdminController {

    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private StudyRoomServiceImpl studyRoomService;
    @Autowired
    private SeatServiceImpl seatService;

    //通知界面
    //管理员到达该url，立即展示所有通知
    @GetMapping(value = "/notice")
    public ModelAndView notice(){
        ModelAndView modelAndView = new ModelAndView("admin/admin_index");
        List<Notice> notices;
        try {
            notices = noticeService.findAll();
            if (notices.isEmpty()){
                modelAndView.addObject("search_failed", "暂时没有通知～");
            }else {
                modelAndView.addObject("search_result", notices);
            }
        }catch (Exception e){
            modelAndView.addObject("search_failed", "查找异常，请再次尝试。如果此问题依然存在请联系开发者！");
        }
        return modelAndView;
    }

    // 删除通知
    @GetMapping(value = "/notice/delete")
    public ModelAndView deleteNotice(@RequestParam("notice_id") String notice_id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/self_study_room/admin/notice");
        modelAndView.addObject("delete_notice_result", noticeService.delete(notice_id));
        return modelAndView;
    }

    //发布通知
    //管理员在通知界面点击按钮，跳转到通知发布页面，进入该页面后，显示发布相关操作
    @GetMapping("/notice_publish")
    public ModelAndView notice_publish(){
        return new ModelAndView("admin/notice_publish");
    }


    //自习室管理界面
    //管理员到达该url，立即展示所有自习室的座位信息
    @GetMapping("/room_list")
    public ModelAndView room_list(){
        ModelAndView modelAndView = new ModelAndView("admin/room_list");
        List<StudyRoom> studyRooms;
        try {
            studyRooms = studyRoomService.findAll();
            if (studyRooms.isEmpty()){
                modelAndView.addObject("search_failed", "暂时没有自习室～");
            }else {
                modelAndView.addObject("study_rooms", studyRooms);//所有自习室
                //再找到所有座位
                try {
                    List<Seat> seats;
                    seats = seatService.findAll();
                    modelAndView.addObject("seats",seats); //所有位置
                }catch (Exception e){
                    modelAndView.addObject("search_failed", "查找异常，请再次尝试。如果此问题依然存在请联系开发者！");
                }
            }
        }catch (Exception e){
            modelAndView.addObject("search_failed", "查找异常，请再次尝试。如果此问题依然存在请联系开发者！");
        }
        return modelAndView;
    }

    //修改自习室位置状态
    @PostMapping("/room_list")
    public ModelAndView seatManage(@RequestParam("seat_number")int seat_number,
                                   @RequestParam("status")String status){
        ModelAndView modelAndView = new ModelAndView("redirect:/self_study_room/admin/room_list");

        System.out.println(seat_number);
        System.out.println(status);

        String result = seatService.update(seat_number, status);
        modelAndView.addObject("result", result);
        return modelAndView;
    }






    //用户列表界面
    //管理员到达该url，立即展示所有用户
    @GetMapping("/user_list")
    public ModelAndView user_list(){
        ModelAndView modelAndView = new ModelAndView("admin/user_list");
        List<User> users;
        try {
            users = userService.findAll();
            if (users.isEmpty()){
                modelAndView.addObject("search_failed", "暂时没有用户信息～");
            }else {
                modelAndView.addObject("search_result", users);
            }
        }catch (Exception e){
            modelAndView.addObject("search_failed", "查找异常，请再次尝试。如果此问题依然存在请联系开发者！");
        }

        return modelAndView;
    }

    // 删除或修改用户信息
    @PostMapping(value = "/user_list")
    public ModelAndView userManage(@RequestParam("choice") String choice,
                                   @RequestParam("user_id") String user_id,
                                   @RequestParam("password") String password,
                                   @RequestParam("user_name") String user_name,
                                   @RequestParam("phone_number") String phone_number,
                                   @RequestParam("sex") String sex)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/self_study_room/admin/user_list");

        if ("delete".equals(choice)){
            modelAndView.addObject("delete_user_result", userService.delete(user_id));
        }else {
            modelAndView.addObject("modify_user_result", userService.update(user_id,user_name,password,phone_number,sex));
        }
        return modelAndView;
    }


    //论坛中心界面
    //管理员到达该url，立即展示所有帖子的信息
    @GetMapping("/forum")
    public ModelAndView forum(){
        ModelAndView modelAndView = new ModelAndView("admin/forum");

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
    public ModelAndView forum_detail(@PathVariable String post_id){
        ModelAndView modelAndView = new ModelAndView("admin/post_detail");
        Post post = postService.searchByPostId(post_id);
        List<Comment> comments = commentService.findCommentByPostId(post_id);
        modelAndView.addObject("post",post);    //当前帖子的详细信息
        modelAndView.addObject("comments", comments);   //当前帖子的所有评论


        //这里需要获取当前用户id
        //Admin可以直接获得（id为003），user没写，user可以在a标签传递th：value，之后在url中展现，最后用@Path那个注解接收
        //具体url格式为：/forum/detail/{user_id}/{post_id}
        String user_id = "003";
        //判断被查看详细信息打开的帖子是不是当前用户的,方便对帖子发起者有筛选优质评论的权限
        if (user_id.equals(post.getUser_id())){
            modelAndView.addObject("is_your_post", "true");
        }else {
            modelAndView.addObject("is_your_post", "false");
        }



        return modelAndView;
    }



    //发帖界面
    //管理员点击按钮，跳转到帖子发布页面，进入该页面后，显示发布相关操作
    @GetMapping("/post_publish/{user_id}")
    public ModelAndView post_publish(@PathVariable("user_id") String user_id){
        ModelAndView modelAndView = new ModelAndView("admin/post_publish");
        userService.findNameById(user_id);
        modelAndView.addObject("cur_user", userService.findNameById(user_id));
        return modelAndView;
    }





}
