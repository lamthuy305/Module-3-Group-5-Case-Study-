package com.codegym.service.order;

import com.codegym.dao.order.IOrderDao;
import com.codegym.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class OrderService implements IOrderService {
    public static final String DD_MM_YYYY = "dd-MM-yyyy";
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

    @Override
    public List<Order> findOrderByOrderID(String q) {
        q = "%" + q + "%";
        return orderDao.findOrderByOrderID(q);
    }

    @Override
    public int maxOrder_idNow() {
        return orderDao.maxOrder_idNow();
    }

    @Override
    public boolean checkCreateDateAfterDateNow(String createDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
        Date dateCreate = java.sql.Date.valueOf(createDate);
        Date dateNow = new Date();
        if (dateNow.before(dateCreate)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Order> findOderByUser(int user_id) {
        return orderDao.findOderByUser(user_id);
    }

    @Override
    public List<Order> findAllDESC(int count) {
        return orderDao.findAllDESC(count);
    }


}