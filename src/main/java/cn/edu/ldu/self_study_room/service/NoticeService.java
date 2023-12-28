package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Notice;

import java.util.List;

public interface NoticeService {
    //查询所有记录，供用户点击通知页面时全部展示
    List<Notice> findAll() throws Exception;
    List<Notice> findbutitile(String notice_titile) throws Exception;
    //增加记录，供管理员发布新通知
    String insert(Notice notice);

    //删除记录，供管理员删除通知
    String delete(String notice_id);

    //修改记录，供管理员修改通知
    String update(String notice_id, String notice_title, String notice_content);

}
