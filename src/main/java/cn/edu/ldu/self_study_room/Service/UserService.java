package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.User;

import java.util.List;

public interface UserService {
    //查询所有记录，管理员进入用户管理界面时展示所有用户信息
    List<User> findAll() throws Exception;

    //增加记录，用户注册时插入记录
    String insert(User user);

    //删除记录，供管理员注销用户
    String delete(String user_id);

    //修改记录，供用户或管理员修改
    String update(String user_id, String user_name, String password, String phone_number, String sex);
}
