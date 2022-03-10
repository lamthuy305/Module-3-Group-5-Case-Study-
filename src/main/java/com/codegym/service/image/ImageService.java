package com.codegym.service.image;


import com.codegym.dao.image.IImageDao;
import com.codegym.model.Image;

import java.util.List;

public class ImageService implements IImageService {
    private IImageDao imageDao;

    public ImageService(IImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public List<Image> findAll() {
        return imageDao.findAll();
    }

    @Override
    public Image findById(int id) {
        return imageDao.findById(id);
    }

    @Override
    public boolean create(Image image) {
        return imageDao.create(image);
    }

    @Override
    public boolean updateById(int id, Image image) {
        return imageDao.updateById(id, image);
    }

    @Override
    public boolean deleteById(int id) {
        return imageDao.deleteById(id);
    }

    @Override
    public List<Image> findAllByStone_ID(int id) {
        return imageDao.findAllById(id);
    }
}

