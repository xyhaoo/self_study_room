package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll() throws Exception;

    //增加记录，供管理员发布新通知
    String insert(User user);

    //删除记录，供管理员删除通知
    String delete(String user_id);

    // 修改记录，供管理员修改用户信息
    String update(String user_id, String user_name, String password, String phone_number, String sex);
}
