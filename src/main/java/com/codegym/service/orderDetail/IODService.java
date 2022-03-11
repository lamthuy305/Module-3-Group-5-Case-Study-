package com.codegym.service.orderDetail;

import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.model.ViewOrderDetail;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IODService extends IGeneralService<ViewOrderDetail> {
    List<ViewOrderDetail> showOrderDetailById(int id);
}
