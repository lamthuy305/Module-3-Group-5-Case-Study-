package com.codegym.dao.image;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Image;

import java.util.List;

public interface IImageDao extends IGeneralDao<Image> {
    List<Image> findAllById(int id);
}
