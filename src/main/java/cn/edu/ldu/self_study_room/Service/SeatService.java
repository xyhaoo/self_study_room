package cn.edu.ldu.self_study_room.service;

import org.apache.ibatis.annotations.Param;

public interface SeatService {
    void update( int room_id,
                 int seat_id,
                String status);
    void insert( int room_id,
                 int seat_id,
                 String status);
}
