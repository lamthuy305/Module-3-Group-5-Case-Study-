package com.codegym.service.category;

import com.codegym.model.Category;
import com.codegym.model.Stone;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {
    List<Category> findAllByName(String q);

}
