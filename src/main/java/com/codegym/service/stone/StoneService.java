package com.codegym.service.stone;

import com.codegym.dao.stone.IStoneDao;
import com.codegym.model.Stone;

import java.util.List;

public class StoneService implements IStoneService {

    private IStoneDao stoneDao;

    public StoneService(IStoneDao stoneDao) {
        this.stoneDao = stoneDao;
    }

    @Override
    public List<Stone> findAll() {
        return stoneDao.findAll();
    }

    @Override
    public Stone findById(int id) {
        return stoneDao.findById(id);
    }

    @Override
    public boolean create(Stone stone) {
        return stoneDao.create(stone);
    }

    @Override
    public boolean updateById(int id, Stone stone) {
        return stoneDao.updateById(id, stone);
    }

    @Override
    public boolean deleteById(int id) {
        return stoneDao.deleteById(id);
    }

    @Override
    public List<Stone> findAllByCategory(int id) {
        return stoneDao.findAllByCategory(id);
    }

    @Override
    public List<Stone> findAllByName(String q) {
        q = "%" + q + "%";
        return stoneDao.findAllByName(q);
    }
}
