package cn.edu.ldu.self_study_room.dao;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Favorites;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface favoriteDao {

    //查询所有记录，供用户点击通知页面时全部展示

    @Select("select * from user_favorites")
    List<Favorites> findAll() throws Exception;

    @Select("SELECT seat.status FROM seat WHERE seat.seat_number = #{seatNumber} AND seat.room_id = #{roomId}")
    String findStatus(@Param("roomId") int roomId,  @Param("seatNumber") int seatNumber) throws Exception;
    //增加记录，供管理员发布新通知
    @Insert("insert into user_favorites(user_id, seat_number, room_id)" +
            "values (#{user_id}, #{seat_number}, #{room_id})")
    void insert(Favorites favorites) throws Exception;

    //删除记录，供管理员删除通知
    @Delete("delete from user_favorites where user_id=#{user_id} and room_id = #{room_id} and seat_number = #{seat_number}")
    void delete(@Param("user_id") String user_id,@Param("room_id") int room_id,@Param("seat_number") int seat_number) throws Exception;


}
