package cn.edu.ldu.self_study_room.service.impl;

import cn.edu.ldu.self_study_room.dao.favoriteDao;
import cn.edu.ldu.self_study_room.entity.Favorites;
import cn.edu.ldu.self_study_room.service.favoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavoritesServiceImpl implements favoritesService {
    @Autowired
    private favoriteDao favoriteDao;
    @Override
    public List<Favorites> findAll() throws Exception {
        return favoriteDao.findAll();
    }
}
