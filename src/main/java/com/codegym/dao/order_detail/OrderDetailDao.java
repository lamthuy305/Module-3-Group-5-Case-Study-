package com.codegym.dao.order_detail;

import com.codegym.dao.DBConnection;
import com.codegym.dao.order.IOrderDao;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.model.ViewOrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao implements IOrderDetailDao {
    private Connection connection =DBConnection.getConnection();



    public List<ViewOrderDetail> showOrderDetailById(int id) {
        List<ViewOrderDetail> viewOrderDetails = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select o.id,od.id, s.name,od.quantity,s.price,o.createDate\n" +
                    "    from order_detail  od join orders o on od.order_id = o.id\n" +
                    "join stone_management.stones s on s.id = od.stone_id\n" +
                    "where order_id =?;");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int order_id = resultSet.getInt("o.id");
                int od_id = resultSet.getInt("od.id");
                String name  =  resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double price  = resultSet.getDouble("price");
                String createDate = resultSet.getString("createDate");
                ViewOrderDetail viewOrderDetail = new ViewOrderDetail(order_id,od_id,name,quantity,price,createDate);
                viewOrderDetails.add(viewOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viewOrderDetails;
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
}
