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

    @Override
    public void insert(Favorites favorites) {
        try {
            favoriteDao.insert(favorites);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findStatus(int room_id,int seat_number) throws Exception {
        return favoriteDao.findStatus(room_id,seat_number);
    }

    @Override
    public void delete(String user_id, int room_id, int seat_number) {
        try {
            favoriteDao.delete(user_id,room_id,seat_number);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
