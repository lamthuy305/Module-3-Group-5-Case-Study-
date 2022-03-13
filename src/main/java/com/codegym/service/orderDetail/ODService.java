package com.codegym.service.orderDetail;

import com.codegym.dao.order_detail.IOrderDetailDao;

import com.codegym.model.OrderDetail;
import com.codegym.model.ViewOrderDetail;
import java.util.List;

public class ODService implements IODService {
    private IOrderDetailDao orderDetailDao;

    public ODService(IOrderDetailDao orderDetailDao) {
        this.orderDetailDao = orderDetailDao;
    }


    @Override
    public List<ViewOrderDetail> findAll() {
        return null;
    }

    @Override
    public ViewOrderDetail findById(int id) {
        return null;
    }

    @Override
    public boolean create(ViewOrderDetail viewOrderDetail) {
        return false;
    }

    @Override
    public boolean updateById(int id, ViewOrderDetail viewOrderDetail) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return orderDetailDao.deleteById(id);
    }

    @Override
    public List<ViewOrderDetail> showOrderDetailById(int id) {
        return orderDetailDao.showOrderDetailById(id);
    }

    @Override
    public boolean create(OrderDetail orderDetail) {
        return orderDetailDao.create(orderDetail);
    }

    @Override
    public OrderDetail findByIdOD(int id) {
        return orderDetailDao.findByIdOD(id);
    }

    @Override
    public boolean updateODById(int id, OrderDetail orderDetail) {
        return orderDetailDao.updateODById(id, orderDetail);
    }
}

