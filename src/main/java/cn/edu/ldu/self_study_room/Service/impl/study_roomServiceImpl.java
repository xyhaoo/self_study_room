package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.NoticeDao;
import cn.edu.ldu.self_study_room.dao.study_roomDao;
import cn.edu.ldu.self_study_room.entity.study_room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class study_roomServiceImpl implements study_roomDao {
    @Autowired
    private study_roomDao study_roomDao;
    @Override
    public List<study_room> findAll() throws Exception {
        return study_roomDao.findAll();
    }

    @Override
    public void insert(study_room self_study_room) throws Exception {

    }

    @Override
    public void delete(String room_id) throws Exception {

    }
}
