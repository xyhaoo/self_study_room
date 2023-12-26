package cn.edu.ldu.self_study_room.dao;

import cn.edu.ldu.self_study_room.entity.study_room;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface study_roomDao {
    //查询所有记录，供用户点击通知页面时全部展示
    @Select("select * from room")
    List<study_room> findAll() throws Exception;

    //增加记录，供管理员发布新通知
    @Insert("insert into room(room_id, room_content, room_picture" +
            "values (#{room_id}, #{room_content}, #{room_picture})")
    void insert(study_room self_study_room) throws Exception;

    //删除记录，供管理员删除通知
    @Delete("delete from room where room_id=#{room_id}")
    void delete(@Param("room_id") String room_id) throws Exception;

    //修改记录，供管理员修改通知
//    @Update("update notice set notice_title=#{notice_title}, notice_content=#{notice_content} where notice_id=#{notice_id}")
//    void update(@Param("notice_id")String notice_id,
//                @Param("notice_title") String notice_title,
//                @Param("notice_content") String notice_content);

    //理应按照日期排序，最近的帖子在最前面，暂时不考虑供用户查找

}
