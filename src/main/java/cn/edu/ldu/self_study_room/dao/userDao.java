package cn.edu.ldu.self_study_room.dao;

import cn.edu.ldu.self_study_room.entity.User;
import cn.edu.ldu.self_study_room.entity.study_room;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface userDao {
    @Select("select * from user")
    List<User> findAll() throws Exception;

    //增加记录，供管理员发布新通知
    @Insert("insert into room(user_id, password, user_name, phone_number,sex" +
            "values (#{user_id}, #{password}, #{user_name},#{phone_number}, #{sex})")
    void insert(User user) throws Exception;

    //删除记录，供管理员删除通知
    @Delete("delete from user where user_id=#{user_id}")
    void delete(@Param("user_id") String user_id) throws Exception;

    //修改记录，供管理员修改通知
    @Update("update user set user_name=#{user_name}, password=#{password}, phone_number=#{phone_number}, sex=#{sex} where user_id=#{user_id}")
    void update(@Param("user_id")String user_id,
                @Param("user_name") String user_name,
                @Param("password") String password,
                @Param("phone_number") String phone_number,
                @Param("sex") String sex) throws Exception;

}
