package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.SeatDao;
import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.Seat;
import cn.edu.ldu.self_study_room.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;

    @Override
    public List<Seat> findAll()   {
        try {
            return seatDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Seat> findAllByRoomId(int room_id)   {
        return seatDao.findAllByRoomId(room_id);
    }

    @Override
    public String update(int seat_number, String status){
        try {
            seatDao.update(seat_number, status);
            return "修改成功～";
        }catch (Exception e){
            return "修改失败了，请再次尝试。如果此问题依然存在请联系开发者！";
        }

    }

    @Override
    public List<Seat> findAllbyid(int room_id,int maxseat_number,int minseat_number) {
        return seatDao.findAllbyid(room_id,maxseat_number,minseat_number);
    }


}
