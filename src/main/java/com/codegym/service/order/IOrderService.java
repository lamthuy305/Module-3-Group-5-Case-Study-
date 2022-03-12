package com.codegym.service.order;

import com.codegym.model.Order;
import com.codegym.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface IOrderService extends IGeneralService<Order> {
    List<Order> findOrderByOrderID(String q);

    int maxOrder_idNow();

    boolean checkCreateDateAfterDateNow(String createDate);


}
