package cn.edu.ldu.self_study_room.dao;

import cn.edu.ldu.self_study_room.entity.Reservation;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ReservationDao {
    @Select("select * from reservation")
    List<Reservation> findAll() throws Exception;

    //增加记录，供管理员发布新通知
    @Insert("insert into reservation(user_id, room_id, seat_number, reserve_time)" +
            "values (#{user_id}, #{room_id}, #{seat_number}, #{reserve_time})")
    void insert(Reservation notice) throws Exception;

    //删除记录，供管理员删除通知
    @Delete("delete from reservation where user_id=#{user_id}")
    void delete(@Param("notice_id") String notice_id) throws Exception;



}