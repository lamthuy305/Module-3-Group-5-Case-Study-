package com.codegym.dao.order;

import com.codegym.dao.DBConnection;
import com.codegym.model.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {

    public static final String UPDATE_ORDER = "UPDATE orders SET user_id = ?, createDate =? WHERE id=?";
    public static final String INSERT_ORDER = "INSERT INTO orders (user_id, createDate) VALUES (?,?);";
    public static final String SELECT_ONE_ORDER = "SELECT * FROM orders WHERE id = ?;";
    public static final String SELECT_ALL_ORDERS = "SELECT * FROM orders; ";
    public static final String DELETE_ORDER = "call deleteOrder(?) ";
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
                String date = resultSet.getString("createDate");
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
                String date = resultSet.getString("createDate");
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
            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setString(2, order.getDate());
            preparedStatement.setInt(3, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            CallableStatement callableStatement = connection.prepareCall(DELETE_ORDER);
            callableStatement.setInt(1, id);
            return callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> findOrderByOrderID(String q) {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from orders where id like ?;");
            preparedStatement.setString(1, q);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                String createDate = resultSet.getString("createDate");
                Order order = new Order(id, user_id, createDate);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int maxOrder_idNow() {
        int max_Order_Id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE id = (SELECT MAX(id) FROM orders);");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                max_Order_Id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max_Order_Id;
    }
}
