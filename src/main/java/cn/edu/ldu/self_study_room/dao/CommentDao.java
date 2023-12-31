package cn.edu.ldu.self_study_room.dao;


import cn.edu.ldu.self_study_room.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CommentDao {
    //查询所有帖子
    @Select("select * from comment")
    List<Comment> findAll();

    //根据帖子编号查询帖子
    @Select("select * from comment where comment_id=#{comment_id}")
    Comment searchByCommentId(@Param("comment_id")int comment_id) throws Exception;

    //查询某个帖子的所有评论
    @Select("select * from comment where post_id=#{post_id}")
    List<Comment> findCommentByPostId(@Param("post_id") String post_id);

    //增加记录，供用户和管理员评论
    @Insert("insert into comment(comment_id, post_id, user_id, comment_time, comment_content, is_best) " +
            "values(#{comment_id},#{post_id},#{user_id},#{comment_time},#{comment_content},#{is_best}) ")
    void insert(@Param("comment_id")int comment_id,
                @Param("post_id")String post_id,
                @Param("user_id")String user_id,
                @Param("comment_time") Timestamp comment_time,
                @Param("comment_content")String comment_content,
                @Param("is_best")boolean is_best) throws Exception;

    @Update("update comment set is_best=#{is_best} where comment_id = #{comment_id}")
    void update(@Param("comment_id")int comment_id,
                @Param("is_best")int is_best) throws Exception;

    //删除评论
    @Delete("delete from comment where comment_id=#{comment_id}")
    void delete(@Param("comment_id")int comment_id) throws Exception;


}
