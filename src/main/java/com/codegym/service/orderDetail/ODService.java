package com.codegym.service.orderDetail;

import com.codegym.dao.order_detail.IOrderDetailDao;
import com.codegym.dao.order_detail.OrderDetailDao;
import com.codegym.model.Order;
import com.codegym.model.ViewOrderDetail;
import com.codegym.service.order.IOrderService;

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
        return false;
    }

    @Override
    public List<ViewOrderDetail> showOrderDetailById(int id) {
        return orderDetailDao.showOrderDetailById(id);
    }
}

