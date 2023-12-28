package cn.edu.ldu.self_study_room.dao;


import cn.edu.ldu.self_study_room.entity.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SeatDao {

    //查询所有座位记录，供管理员登陆自习室管理页面时展示
    @Select("select * from seat")
    List<Seat> findAll() throws Exception;

    //查询某自习室的所有座位记录
    @Select("select * from seat where room_id = #{room_id}")
    List<Seat> findAllByRoomId(@Param("room_id") int room_id) throws Exception;

    //修改记录，供管理员修改座位状态
    @Update("update seat set status=#{status} where seat_number=#{seat_number}")
    void update(@Param("seat_number")int seat_number,
                @Param("status") String status) throws Exception;


}
