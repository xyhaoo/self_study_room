package cn.edu.ldu.self_study_room.dao;


import cn.edu.ldu.self_study_room.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostDao {
    //查询所有记录，供用户或管理员点击论坛页面时展示所有帖子
    @Select("select * from post")
    List<Post> findAll() throws Exception;

    //增加记录，供用户或管理员发布新帖子
    @Insert("insert into post(post_id, user_id, post_title, post_content, post_time, post_status)" +
            "values (#{post_id}, #{user_id}, #{post_title}, #{post_content}, #{post_time}, #{post_status})")
    void insert(Post post) throws Exception;

    //删除记录，供用户或管理员删除帖子
    @Delete("delete from post where post_id=#{post_id}")
    void delete(@Param("post_id") String post_id) throws Exception;

    //修改记录，供用户或管理员修改帖子内容
    @Update("update post set post_title=#{post_title}, post_content=#{post_content} where post_id=#{post_id}")
    void update_content(@Param("post_id")String post_id,
                @Param("post_title") String post_title,
                @Param("post_content") String post_content);

    @Update("update post set post_status=#{post_status} where post_id=#{post_id}")
    void update_status(@Param("post_id")String post_id,
                @Param("post_status") String post_status);


    //根据帖子号查询，用于点击某个帖子链接跳转到详细界面时，利用该帖子号渲染内容
    @Select("select * from post where post_id = #{post_id}")
    Post searchByPostId(@Param("post_id") String post_id) throws Exception;



    //模糊查询，供用户或管理员根据帖子标题查询帖子
    @Select("select * from post where post_title like #{post_title}")
    List<Post> fuzzySearch(@Param("post_title") String post_title) throws Exception;

}
