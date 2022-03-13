package com.codegym.dao.order_detail;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.OrderDetail;
import com.codegym.model.ViewOrderDetail;

import java.util.List;

public interface IOrderDetailDao extends IGeneralDao<ViewOrderDetail> {
    List<ViewOrderDetail> showOrderDetailById(int id);

    boolean create(OrderDetail orderDetail);

    OrderDetail findByIdOD(int id);

    boolean updateODById(int id, OrderDetail orderDetail);


}
