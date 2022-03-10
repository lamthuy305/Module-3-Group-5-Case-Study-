package com.codegym.service.stone;

import com.codegym.model.Stone;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IStoneService extends IGeneralService<Stone> {
    List<Stone> findAllByCategory(int id);
    List<Stone> findAllByName(String q);
}
