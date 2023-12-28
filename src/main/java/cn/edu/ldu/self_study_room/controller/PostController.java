package cn.edu.ldu.self_study_room.controller;

import cn.edu.ldu.self_study_room.entity.Comment;
import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Post;
import cn.edu.ldu.self_study_room.entity.User;
import cn.edu.ldu.self_study_room.service.CommentService;
import cn.edu.ldu.self_study_room.service.impl.CommentServiceImpl;
import cn.edu.ldu.self_study_room.service.impl.PostServiceImpl;
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


    //发布帖子
    //获取用户或管理员提交的帖子信息，插入post表，返回发布状态
    @PostMapping(value = {"/admin/post_publish", "/user/post_publish"})
    public ModelAndView postPublish(@RequestParam String user_id,
                                    @RequestParam String post_title,
                                    @RequestParam String post_content)
    {
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
        ModelAndView modelAndView = new ModelAndView("admin/post_publish");
        modelAndView.addObject("status", status);
        return modelAndView;
    }


    //写评论
    @PostMapping("/admin/forum/detail/{post_id}")
    public ModelAndView commentPublish(@PathVariable("post_id")String post_id,
                                       @RequestParam String comment_content){

        //这里需要获取当前用户id
        //Admin可以直接获得（id为003），user没写，user可以在a标签传递th：value，之后在url中展现，最后用@Path那个注解接收
        //具体url格式为：/forum/detail/{user_id}/{post_id}
        String user_id = "003";

        int comment_id = 0;
        //插入的评论记录，其id是现有通知中id最大值+1，如果当前没有评论，其值为1
        try {
            List<Comment> comments = commentService.findAll();
            int maxId = 0;
            for (Comment comment : comments) {
                int cur_id = comment.getComment_id();
                if (cur_id > comment_id) {
                    comment_id = cur_id;
                }
            }
            comment_id+=1;
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

        System.out.println(post_id);
        System.out.println(comment_content);


        String status = commentService.insert(comment_id, post_id, user_id, now, comment_content, false);
        ModelAndView modelAndView = new ModelAndView("redirect:/self_study_room/admin/forum/detail/"+post_id);
        modelAndView.addObject("status", status);
        return modelAndView;

        //点击按钮提交评论 设置为最优 最优展示不了 发完贴的跳转 评论删除

    }

}
