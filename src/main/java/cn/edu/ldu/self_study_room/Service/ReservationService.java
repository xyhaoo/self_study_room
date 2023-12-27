package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll() throws Exception;

    //增加记录，供管理员发布新通知
    void insert(Reservation Reservation);

    //删除记录，供管理员删除通知
    void delete(String user_id);


}
