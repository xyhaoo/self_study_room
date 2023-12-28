package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.StudyRoomDao;
import cn.edu.ldu.self_study_room.entity.Seat;
import cn.edu.ldu.self_study_room.entity.StudyRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class StudyRoomServiceImpl implements StudyRoomDao {
    @Autowired
    private StudyRoomDao studyRoomDao;
    @Override
    public List<StudyRoom> findAll() throws Exception {
        for (StudyRoom i : studyRoomDao.findAll())
            System.out.println(i);
        return studyRoomDao.findAll();
    }

    @Override
    public List<Seat> findstautsbyid(int room_id) throws Exception {
        return studyRoomDao.findstautsbyid(room_id);
    }

    @Override
    public void insert(StudyRoom self_study_room) throws Exception {

    }

    @Override
    public void delete(String room_id) throws Exception {

    }
}
