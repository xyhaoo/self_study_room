package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PostService {

    //查询所有记录，供用户或管理员点击论坛页面时展示所有帖子
    List<Post> findAll() throws Exception;

    //增加记录，供用户或管理员发布新帖子
    String insert(Post post) throws Exception;

    //删除记录，供用户或管理员删除帖子
    String delete(@Param("post_id") String post_id) throws Exception;

    //修改记录，供用户或管理员修改帖子内容
    String update_content(@Param("post_id")String post_id,
                @Param("post_title") String post_title,
                @Param("post_content") String post_content);

    //修改记录，供用户或管理员修改帖子状态
    String update_status(@Param("post_id")String post_id,
                          @Param("post_status") String post_status);

    //根据帖子号查询，用于点击某个帖子链接跳转到详细界面时，利用该帖子号渲染内容
    Post searchByPostId(@Param("post_id") String post_id);

    //模糊查询，供用户或管理员根据帖子标题查询帖子
    List<Post> fuzzySearch(@Param("post_title") String post_title) throws Exception;

}
