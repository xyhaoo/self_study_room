package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeatService {
//    void update( int room_id,
//                 int seat_id,
//                String status);
//    void insert( int room_id,
//                 int seat_id,
//                 String status);

    public List<Seat> findAll();
    public List<Seat> findAllByRoomId(int room_id);
    public String update(int seat_number, String status);


}
