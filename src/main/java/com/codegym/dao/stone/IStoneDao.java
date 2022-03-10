package com.codegym.dao.stone;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Stone;

import java.util.List;

public interface IStoneDao extends IGeneralDao<Stone> {
    List<Stone> findAllByCategory(int id);
    List<Stone> findAllByName(String q);
}
