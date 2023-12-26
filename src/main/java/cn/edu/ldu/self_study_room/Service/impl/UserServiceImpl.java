package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.study_roomDao;
import cn.edu.ldu.self_study_room.dao.userDao;
import cn.edu.ldu.self_study_room.entity.User;
import cn.edu.ldu.self_study_room.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    userDao userDao;


    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public String insert(User user) {
        try {
            userDao.insert(user);
            return "添加用户成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加用户失败";
        }
    }

    @Override
    public String delete(String user_id) {
        try {
            userDao.delete(user_id);
            return "删除用户成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除用户失败";
        }
    }

    @Override
    public String update(String user_id, String user_name, String password, String phone_number, String sex) {
        try {
            userDao.update(user_id, user_name, password, phone_number, sex);
            return "修改用户信息成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改用户信息失败";
        }
    }
}
