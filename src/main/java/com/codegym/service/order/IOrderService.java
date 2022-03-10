package com.codegym.service.order;

import com.codegym.model.Order;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IOrderService extends IGeneralService<Order> {
    public List<Order> showOrderDetailById(int id);
}
