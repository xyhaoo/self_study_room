package cn.edu.ldu.self_study_room.dao;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.favorites;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
public interface favoriteDao {

    //查询所有记录，供用户点击通知页面时全部展示
    @Select("SELECT user_favorites.user_id, seat.seat_number, seat.room_id, seat.status\n" +
            "FROM user_favorites\n" +
            "         JOIN seat ON user_favorites.user_id = seat.room_id AND user_favorites.room_id = seat.room_id and user_favorites.seat_number = seat.seat_number\n")
    List<favorites> findAll() throws Exception;

    //增加记录，供管理员发布新通知
    @Insert("insert into notice(user_id, seat_number, room_id)" +
            "values (#{user_id}, #{seat_number}, #{room_id})")
    void insert(Notice notice) throws Exception;

    //删除记录，供管理员删除通知
    @Delete("delete from notice where notice_id=#{notice_id}")
    void delete(@Param("notice_id") String notice_id) throws Exception;


}
