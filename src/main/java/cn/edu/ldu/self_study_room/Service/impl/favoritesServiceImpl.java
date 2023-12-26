package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.NoticeDao;
import cn.edu.ldu.self_study_room.dao.favoriteDao;
import cn.edu.ldu.self_study_room.entity.favorites;
import cn.edu.ldu.self_study_room.service.favoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class favoritesServiceImpl implements favoritesService {
    @Autowired
    private favoriteDao favoriteDao;
    @Override
    public List<favorites> findAll() throws Exception {
        return favoriteDao.findAll();
    }
}
