package com.codegym.dao.order;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.Order;

import java.util.List;

public interface IOrderDao extends IGeneralDao<Order> {
    List<Order> findOrderByOrderID(String q);

    int maxOrder_idNow();

    List<Order> findOderByUser(int user_id);

    List<Order> findAllDESC(int count);

}
