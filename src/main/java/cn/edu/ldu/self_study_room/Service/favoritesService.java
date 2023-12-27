package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Favorites;
import cn.edu.ldu.self_study_room.entity.Notice;

import java.util.List;

public interface favoritesService {
    List<Favorites> findAll() throws Exception;
    //增加记录，供管理员发布新通知
    void insert(Favorites favorites);

    String findStatus(int room_id,int seat_number) throws Exception;
}
