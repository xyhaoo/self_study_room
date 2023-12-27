package cn.edu.ldu.self_study_room.dao;

import cn.edu.ldu.self_study_room.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserDao {
    //查询所有记录，管理员进入用户管理界面时展示所有用户信息
    @Select("select * from user")
    List<User> findAll() throws Exception;

    //增加记录，用户注册时插入记录
    @Insert("insert into user(user_id, password, user_name, phone_number,sex) " +
            "values (#{user_id}, #{password}, #{user_name},#{phone_number}, #{sex})")
    void insert(User user) throws Exception;

    //删除记录，供管理员注销用户
    @Delete("delete from user where user_id=#{user_id}")
    void delete(@Param("user_id") String user_id) throws Exception;

    //修改记录，供用户或管理员修改
    @Update("update user set user_name=#{user_name}, password=#{password}, phone_number=#{phone_number}, sex=#{sex} where user_id=#{user_id}")
    void update(@Param("user_id")String user_id,
                @Param("user_name") String user_name,
                @Param("password") String password,
                @Param("phone_number") String phone_number,
                @Param("sex") String sex) throws Exception;

}
