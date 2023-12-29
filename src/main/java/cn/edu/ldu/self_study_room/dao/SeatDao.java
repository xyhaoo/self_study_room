package cn.edu.ldu.self_study_room.dao;

import cn.edu.ldu.self_study_room.entity.Reservation;
import cn.edu.ldu.self_study_room.entity.Seat;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SeatDao {

    @Select("select * from seat")
    List<Seat> findAll() throws Exception;

    @Insert("insert into seat(room_id, seat_number, status)" +
            "values (#{room_id}, #{seat_number}, #{status})")
    void insert(Seat seat) throws Exception;

//
//    @Delete("delete from reservation where user_id=#{user_id} and room_id=#{room_id} and seat_number=#{seat_number}")
//    void delete(@Param("user_id") String user_id,
//                @Param("room_id") int room_id,
//                @Param("seat_number") int seat_number) throws Exception;
//
    @Update("update seat set status=#{status} where seat_number=#{seat_number}")
    void update(
                @Param("seat_number") int seat_number,
                @Param("status") String status);


    @Select("select * from seat where room_id = #{roomId}")
    List<Seat> findAllByRoomId(int roomId);

    @Select("select * from seat where room_id like #{roomId} and seat_number <= #{maxseat_number} and seat_number >= #{minseat_number}")
    List<Seat> findAllbyid(int roomId,int maxseat_number,int minseat_number);


}
