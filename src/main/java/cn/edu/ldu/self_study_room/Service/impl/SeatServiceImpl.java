package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.ReservationDao;
import cn.edu.ldu.self_study_room.dao.SeatDao;
import cn.edu.ldu.self_study_room.entity.Seat;
import cn.edu.ldu.self_study_room.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatDao seatDao;
    @Override
    public void update(int room_id, int seat_id, String status) {
        seatDao.update(room_id,seat_id,status);
    }

    @Override
    public void insert(int room_id, int seat_id, String status) {
        try {
            seatDao.insert(new Seat(room_id,seat_id,status));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
