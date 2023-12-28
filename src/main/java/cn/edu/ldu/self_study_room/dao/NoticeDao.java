package cn.edu.ldu.self_study_room.dao;

import org.apache.ibatis.annotations.*;
import cn.edu.ldu.self_study_room.entity.Notice;

import java.util.List;

@Mapper
public interface NoticeDao {
    //查询所有记录，供用户点击通知页面时全部展示
    @Select("select * from notice")
    List<Notice> findAll() throws Exception;

    //增加记录，供管理员发布新通知
    @Insert("insert into notice(notice_id, notice_title, notice_content, notice_time)" +
            "values (#{notice_id}, #{notice_title}, #{notice_content}, #{notice_time})")
    void insert(Notice notice) throws Exception;

    //删除记录，供管理员删除通知
    @Delete("delete from notice where notice_id=#{notice_id}")
    void delete(@Param("notice_id") String notice_id) throws Exception;

    //修改记录，供管理员修改通知
    @Update("update notice set notice_title=#{notice_title}, notice_content=#{notice_content} where notice_id=#{notice_id}")
    void update(@Param("notice_id")String notice_id,
               @Param("notice_title") String notice_title,
               @Param("notice_content") String notice_content);


    @Select("select * from notice where notice_title like ${notice_title}")
    List<Notice> findbutitle(@Param("notice_title")String notice_title) throws Exception;
    //理应按照日期排序，最近的帖子在最前面，暂时不考虑供用户查找

}
