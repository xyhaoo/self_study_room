package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Notice;
import cn.edu.ldu.self_study_room.entity.favorites;

import java.util.List;

public interface favoritesService {
    List<favorites> findAll() throws Exception;

}
