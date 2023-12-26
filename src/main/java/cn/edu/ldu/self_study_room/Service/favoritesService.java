package cn.edu.ldu.self_study_room.service;

import cn.edu.ldu.self_study_room.entity.Favorites;

import java.util.List;

public interface favoritesService {
    List<Favorites> findAll() throws Exception;

}
