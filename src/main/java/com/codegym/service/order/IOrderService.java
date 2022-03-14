package com.codegym.service.order;

import com.codegym.model.Order;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IOrderService extends IGeneralService<Order> {
    List<Order> findOrderByOrderID(String q);

    int maxOrder_idNow();

    boolean checkCreateDateAfterDateNow(String createDate);

    List<Order> findOderByUser(int user_id);

    List<Order> findAllDESC(int count);

}
