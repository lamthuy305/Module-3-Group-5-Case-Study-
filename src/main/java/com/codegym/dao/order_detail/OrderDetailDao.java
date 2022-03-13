package com.codegym.dao.order_detail;

import com.codegym.dao.DBConnection;
import com.codegym.model.OrderDetail;
import com.codegym.model.ViewOrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao implements IOrderDetailDao {
    public static final String SQL_SELECT_VIEW_ORDER_DETAIL = "select o.id,od.id, s.name,od.quantity,s.price,o.createDate\n" +
            "    from order_detail  od join orders o on od.order_id = o.id\n" +
            "join stone_management.stones s on s.id = od.stone_id\n" +
            "where order_id =?;";
    public static final String SELECT_FROM_ORDER_DETAIL_WHERE_ID = "SELECT * FROM order_detail WHERE id = ?;";
    public static final String UPDATE_ORDER_DETAIL_SET_ID_ORDER_ID_STONE_ID_QUANTITY_WHERE_ID = "UPDATE order_detail SET id = ?, order_id = ?, stone_id = ?, quantity = ? WHERE id = ?;";
    public static final String SQL_CREATE_ORDERDETAIL = "INSERT INTO order_detail (order_id, stone_id, quantity) VALUES (?,?,?);";
    private Connection connection = DBConnection.getConnection();


    public List<ViewOrderDetail> showOrderDetailById(int id) {
        List<ViewOrderDetail> viewOrderDetails = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_VIEW_ORDER_DETAIL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int order_id = resultSet.getInt("o.id");
                int od_id = resultSet.getInt("od.id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String createDate = resultSet.getString("createDate");
                ViewOrderDetail viewOrderDetail = new ViewOrderDetail(order_id, od_id, name, quantity, price, createDate);
                viewOrderDetails.add(viewOrderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viewOrderDetails;
    }

    @Override
    public boolean create(OrderDetail orderDetail) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDERDETAIL);
            preparedStatement.setInt(1, orderDetail.getOrder_id());
            preparedStatement.setInt(2, orderDetail.getStone_id());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OrderDetail findByIdOD(int id) {
        OrderDetail orderDetail = new OrderDetail();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ORDER_DETAIL_WHERE_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int order_id = resultSet.getInt("order_id");
                int stone_id = resultSet.getInt("stone_id");
                int quantity = resultSet.getInt("quantity");
                orderDetail = new OrderDetail(id, order_id, stone_id, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    @Override
    public boolean updateODById(int id, OrderDetail orderDetail) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_DETAIL_SET_ID_ORDER_ID_STONE_ID_QUANTITY_WHERE_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, orderDetail.getOrder_id());
            preparedStatement.setInt(3, orderDetail.getStone_id());
            preparedStatement.setInt(4, orderDetail.getQuantity());
            preparedStatement.setInt(5, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM order_detail WHERE id = ?; ");
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
