package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.CommentDao;
import cn.edu.ldu.self_study_room.entity.Comment;
import cn.edu.ldu.self_study_room.entity.Post;
import cn.edu.ldu.self_study_room.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;
    @Autowired
    PostServiceImpl postService;

    //查询所有评论，供插入新记录时使得其id每次增加1
    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    //根据评论id找到这条评论
    @Override
    public Comment searchByCommentId(int comment_id) {
        Comment comment = null;
        try {
            comment = commentDao.searchByCommentId(comment_id);
            return comment;
        }catch (Exception e){
            return null;
        }
    }

    //查询某个帖子的所有评论
    @Override
    public List<Comment> findCommentByPostId(String post_id){
        return commentDao.findCommentByPostId(post_id);
    }


    //增加记录，供用户和管理员评论
    @Override
    public String insert(int comment_id, String post_id, String user_id, Timestamp comment_time, String comment_content, boolean is_best) {
        try {
            commentDao.insert(comment_id,post_id,user_id,comment_time,comment_content,is_best);
            return "评论成功～";
        }catch (Exception e){
            System.out.println(e);
            return "评论失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }


    //更改评论状态
    //还要修改评论对应的帖子的状态
    @Override
    public String update(int comment_id, int is_best){
        try {
            commentDao.update(comment_id,is_best);

            Post post = postService.searchByPostId(searchByCommentId(comment_id).getPost_id());
            if (is_best == 1){
                postService.update_status(post.getPost_id(), "resolved");
            }else {
                postService.update_status(post.getPost_id(), "unresolved");
            }
            return "更新成功～";
        }catch (Exception e){
            return "更改评论状态失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }


    //删除评论
    //如果最优回答被删除，需要将帖子设置为unresolved状态
    @Override
    public String delete(int comment_id){
        try {
            Comment comment = searchByCommentId(comment_id);
            if (comment.is_best()){
                Post post = postService.searchByPostId(comment.getPost_id());
                postService.update_status(post.getPost_id(), "unresolved");
            }
            commentDao.delete(comment_id);
            return "删除成功～";
        }catch (Exception e){
            return "删除评论失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }

}
