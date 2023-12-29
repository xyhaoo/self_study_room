package cn.edu.ldu.self_study_room.controller;

import cn.edu.ldu.self_study_room.entity.Comment;
import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Post;
import cn.edu.ldu.self_study_room.entity.User;
import cn.edu.ldu.self_study_room.service.CommentService;
import cn.edu.ldu.self_study_room.service.impl.CommentServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.PostServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

//这是帖子发布页面，用于用户或管理员发帖
@RestController
@RequestMapping(value = "/self_study_room")
public class PostController {
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserServiceImpl userService;


    //发布帖子
    //获取用户或管理员提交的帖子信息，插入post表，返回发布状态
    @PostMapping(value = {"/admin/post_publish/{user_id}", "/user/post_publish/{user_id}"})
    public ModelAndView postPublish(
                                    @PathVariable("user_id") String user_id,
                                    @RequestParam String post_title,
                                    @RequestParam String post_content)
    {
        System.out.println("发帖提交");
        System.out.println("用户名"+user_id);



        String user_name = userService.findById(user_id).getUser_name();
        String post_id;
        //插入的通知记录，其id是现有通知中id最大值+1，如果当前没有通知，其值为1
        try {
            List<Post> posts = postService.findAll();
            int maxId = 0;
            for (Post post : posts) {
                int currentId = Integer.parseInt(post.getPost_id());
                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
            if (posts.isEmpty()) {
                post_id = "1";
            } else {
                post_id = String.valueOf(maxId + 1);
            }
        }catch (Exception e){
            //findAll方法异常，出现此类型的通知id时，应该修正findAll
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 32; i++) {
                int digit = random.nextInt(10); // 生成 0 到 9 之间的随机数字
                sb.append(digit);
            }
            post_id = sb.toString();
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(currentDateTime);
        String status = postService.insert(new Post(post_id, user_id, post_title, post_content, now, "unresolved"));
        ModelAndView modelAndView;
        if ("admin".equals(user_name)){
            modelAndView = new ModelAndView("admin/post_publish");
        }else {
            modelAndView = new ModelAndView("user/post_publish");
        }
        modelAndView.addObject("status", status);
        return modelAndView;
    }


    //写评论、帖子所有者将评论设置为最优、帖子所有者或管理员删除评论、帖子所有者或管理员删除帖子
    @PostMapping(value = {"/admin/{user_id}/forum/detail/{post_id}", "/user/{user_id}/forum/detail/{post_id}"})
    public ModelAndView postDetail(@RequestParam("choice")String choice,
                                   @RequestParam("choice")String user_name,
                                   @RequestParam("user_id")String user_id,
                                   @RequestParam(required = false)int comment_id,
                                   @PathVariable("post_id")String post_id,
                                   @RequestParam(required = false) String comment_content){

        //这里需要获取当前用户id
        //Admin可以直接获得（id为003），user没写，user可以在a标签传递th：value，之后在url中展现，最后用@Path那个注解接收
        //具体url格式为：/forum/detail/{user_id}/{post_id}
//        String user_id = "003";
        ModelAndView modelAndView = null;
        String status = null;
//        String user_name = userService.findById(user_id).getUser_name();
        switch (choice) {
            case "publish_comment" -> { //写评论

                if ("admin".equals(user_name)){
                    modelAndView = new ModelAndView("redirect:http://localhost:8881/self_study_room/admin/"+user_id+"/forum/detail/"+post_id);
                }else {
                    modelAndView = new ModelAndView("redirect:http://localhost:8881/self_study_room/user/"+user_id+"/forum/detail/"+post_id);
                }


                int next_comment_id = 0;
                //插入的评论记录，其id是现有通知中id最大值+1，如果当前没有评论，其值为1
                try {
                    List<Comment> comments = commentService.findAll();
                    for (Comment comment : comments) {
                        int cur_id = comment.getComment_id();
                        if (cur_id > next_comment_id) {
                            next_comment_id = cur_id;
                        }
                    }
                    next_comment_id += 1;
                } catch (Exception e) {
                    //findAll方法异常，出现此类型的通知id时，应该修正findAll
                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 32; i++) {
                        int digit = random.nextInt(10); // 生成 0 到 9 之间的随机数字
                        sb.append(digit);
                    }
                    post_id = sb.toString();
                }
                LocalDateTime currentDateTime = LocalDateTime.now();
                Timestamp now = Timestamp.valueOf(currentDateTime);
                status = commentService.insert(next_comment_id, post_id, user_id, now, comment_content, false);
            }
            case "set_best_answer" -> { //设为优质回答
                if ("admin".equals(user_name)){
                    modelAndView = new ModelAndView("redirect:http://localhost:8881/self_study_room/admin/"+user_id+"/forum/detail/"+post_id);
                }else {
                    modelAndView = new ModelAndView("redirect:http://localhost:8881/self_study_room/user/"+user_id+"/forum/detail/"+post_id);
                }

                System.out.println("2");
                status = commentService.update(comment_id, 1);
            }
            case "delete_comment" -> { //帖子所有者或管理员删除评论
                if ("admin".equals(user_name)){
                    modelAndView = new ModelAndView("redirect:http://localhost:8881/self_study_room/admin/"+user_id+"/forum/detail/"+post_id);
                }else {
                    modelAndView = new ModelAndView("redirect:http://localhost:8881/self_study_room/user/"+user_id+"/forum/detail/"+post_id);
                }

                System.out.println("3");
                status = commentService.delete(comment_id);
            }
            case "delete_post" -> { //帖子所有者或管理员删除帖子
                if ("admin".equals(user_name)){
                    modelAndView = new ModelAndView("redirect:/self_study_room/admin/forum");
                }else {
                    modelAndView = new ModelAndView("redirect:/self_study_room/user/forum");
                }

//                modelAndView = new ModelAndView("redirect:/self_study_room/admin/forum");
                System.out.println("4");
                status = postService.delete(post_id);
            }
        }
        if (modelAndView != null){
            modelAndView.addObject("status", status);
        }
        modelAndView.addObject("user_name",user_name);
        return modelAndView;

        //设置为最优 发完贴的跳转 评论删除

    }



}
