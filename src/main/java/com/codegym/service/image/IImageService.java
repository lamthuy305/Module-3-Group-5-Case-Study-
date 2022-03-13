package com.codegym.service.image;

import com.codegym.model.Image;
import com.codegym.model.Image_Stone;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IImageService extends IGeneralService<Image> {
    List<Image> findAllByStone_ID(int id);
    List<Image_Stone> findAllByStoneName();
    List<Image_Stone> findByStoneName(String q);

}
