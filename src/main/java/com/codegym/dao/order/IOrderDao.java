package com.codegym.dao.order;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Image_Stone;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;

import java.util.List;

public interface IOrderDao extends IGeneralDao<Order> {
    List<Order> findOrderByOrderID(String q);

    int maxOrder_idNow();
}
