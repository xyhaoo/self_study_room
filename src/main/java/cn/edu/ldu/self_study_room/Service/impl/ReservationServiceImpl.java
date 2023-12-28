package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.ReservationDao;
import cn.edu.ldu.self_study_room.entity.Reservation;
import cn.edu.ldu.self_study_room.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationDao reservationDao;
    @Override
    public List<Reservation> findAll(String user_id) throws Exception {
        return reservationDao.findAll(user_id);
    }

    @Override
    public void insert(Reservation Reservation) {

        try {
            reservationDao.insert(Reservation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void delete(String user_id,int room_id,int seat_number) {
        try {
            reservationDao.delete(user_id,room_id,seat_number);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
