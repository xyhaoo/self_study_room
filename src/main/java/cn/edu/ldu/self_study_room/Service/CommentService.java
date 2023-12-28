package cn.edu.ldu.self_study_room.service;


import cn.edu.ldu.self_study_room.entity.Comment;
import org.apache.ibatis.annotations.Select;


import java.sql.Timestamp;
import java.util.List;

public interface CommentService {
    //查询所有帖子
    List<Comment> findAll();



    //查询某个帖子的所有评论
    List<Comment> findCommentByPostId(String post_id);

    //增加记录，供用户和管理员评论
    String insert(int comment_id,
                String post_id,
                String user_id,
                Timestamp comment_time,
                String comment_content,
                boolean is_best) throws Exception;


}
