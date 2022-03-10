package com.codegym.service.order;

import com.codegym.dao.order.IOrderDao;
import com.codegym.model.Order;

import java.util.List;

public class OrderService implements IOrderService {
    private IOrderDao orderDao;

    public OrderService(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public boolean create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public boolean updateById(int id, Order order) {
        return orderDao.updateById(id, order);
    }

    @Override
    public boolean deleteById(int id) {
        return orderDao.deleteById(id);
    }

}