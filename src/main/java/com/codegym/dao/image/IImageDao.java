package com.codegym.dao.image;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Image;
import com.codegym.model.Image_Stone;

import java.util.List;

public interface IImageDao extends IGeneralDao<Image> {
    List<Image> findAllById(int id);
    List<Image_Stone> findAllByStoneName();
    List<Image_Stone> findByStoneName(String q);
}
