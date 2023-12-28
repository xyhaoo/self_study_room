package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.study_roomDao;
import cn.edu.ldu.self_study_room.entity.Seat;
import cn.edu.ldu.self_study_room.entity.StudyRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudyRoomServiceImpl implements study_roomDao {
    @Autowired
    private study_roomDao study_roomDao;
    @Override
    public List<StudyRoom> findAll() throws Exception {
        for (StudyRoom i : study_roomDao.findAll())
            System.out.println(i);
        return study_roomDao.findAll();
    }

    @Override
    public List<Seat> findstautsbyid(int room_id) throws Exception {
        return study_roomDao.findstautsbyid(room_id);
    }

    @Override
    public void insert(StudyRoom self_study_room) throws Exception {

    }

    @Override
    public void delete(String room_id) throws Exception {

    }
}
