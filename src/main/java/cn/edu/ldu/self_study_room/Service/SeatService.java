package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Seat;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface SeatService {
    //查询所有座位记录，供管理员登陆自习室管理页面时展示
    List<Seat> findAll() throws Exception;


    //查询某自习室的所有座位记录，供管理员登陆自习室管理页面时展示
    List<Seat> findAllByRoomId(@Param("room_id") int room_id) throws Exception;

    //修改记录，供管理员修改座位状态
    String update(@Param("seat_number")int seat_number,
                @Param("status") String status);

}
