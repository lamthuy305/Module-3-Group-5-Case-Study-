package com.codegym.service.order;

import com.codegym.model.Image_Stone;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.model.ViewOrderDetail;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IOrderService extends IGeneralService<Order> {
    List<Order> findOrderByOrderID(String q);

}
