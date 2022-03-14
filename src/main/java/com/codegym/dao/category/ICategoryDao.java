package com.codegym.dao.category;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Category;

import java.util.List;

public interface ICategoryDao extends IGeneralDao<Category> {
    boolean deleteCategoryUsingProcedure(int id);
    List<Category> findAllByName(String q);


}
