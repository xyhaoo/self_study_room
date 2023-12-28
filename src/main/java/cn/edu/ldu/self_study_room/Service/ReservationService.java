package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll(String user_id) throws Exception;


    void insert(Reservation Reservation);


    void delete(String user_id,int room_id,int seat_number);


}
