package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.NoticeDao;
import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    //查询所有记录，供用户点击通知页面时全部展示
    @Override
    public List<Notice> findAll() throws Exception {
        return noticeDao.findAll();
    }

    @Override
    public List<Notice> findbutitile(String notice_titile) throws Exception {
        return null;
    }

    //增加记录，供管理员发布新通知
    @Override
    public String insert(Notice notice){
        try {
            //只要插入成功，肯定返回1,否则抛出异常。不需要接收返回值再判断了。直接把dao层接口返回值设置成了void
            noticeDao.insert(notice);
            return "发布成功～";
        }catch (Exception e){
            return "发布失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }

    //删除记录，供管理员删除通知
    @Override
    public String delete(String notice_id) {
        try {
            noticeDao.delete(notice_id);
            return "删除成功～";
        }catch (Exception e){
            return "删除失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }

    //修改记录，供管理员修改通知
    @Override
    public String update(String notice_id, String notice_title, String notice_content) {
        try {
            noticeDao.update(notice_id, notice_title, notice_content);
            return "修改成功～";
        }catch (Exception e){
            return "修改失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }
    }
}
