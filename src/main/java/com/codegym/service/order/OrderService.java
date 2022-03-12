package com.codegym.service.order;

import com.codegym.dao.order.IOrderDao;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.model.ViewOrderDetail;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateCreate = java.sql.Date.valueOf(createDate);
        Date dateNow = new Date();
        if (dateNow.before(dateCreate)) {
            return true;
        }
            return false;
    }


}