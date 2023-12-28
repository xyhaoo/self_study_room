package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.CommentDao;
import cn.edu.ldu.self_study_room.entity.Comment;
import cn.edu.ldu.self_study_room.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    //查询所有评论，供插入新记录时使得其id每次增加1
    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
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
            return "评论失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }
}
