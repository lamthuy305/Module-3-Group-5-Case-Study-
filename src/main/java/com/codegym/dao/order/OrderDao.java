package com.codegym.dao.order;

import com.codegym.dao.DBConnection;
import com.codegym.model.Order;
import com.codegym.model.OrderDetail;
import com.codegym.model.Stone;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    public static final String DELETE_ORDER = "delete from orders where id = ?";
    public static final String UPDATE_ORDER = "UPDATE orders SET user_id = ?, date =? WHERE id=?";
    public static final String INSERT_ORDER = "INSERT INTO orders (user_id, date) VALUES (?,?);";
    public static final String SELECT_ONE_ORDER = "SELECT * FROM orders WHERE id = ?;";
    public static final String SELECT_ALL_ORDERS = "SELECT * FROM orders; ";
    private Connection connection = DBConnection.getConnection();


    public OrderDao() {
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String date = resultSet.getString("date");
                Order order = new Order(id, user_id, date);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findById(int id) {
        Order order = new Order();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_ORDER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String date = resultSet.getString("date");
                order = new Order(id, user_id, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean create(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setString(2, order.getDate());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setString(1, order.getDate());
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
@Override
    public List<Order> showOrderDetailById(int id) {
        List<Order> orderDetails = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("call getOrderPrice(?)");
            callableStatement.setInt(1,id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
               int quantity = resultSet.getInt("quantity");
               double orderPrice = resultSet.getDouble(("(od.quantity*s.price"));// ???
               OrderDetail orderDetail = new OrderDetail(quantity,orderPrice);
               orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
}
