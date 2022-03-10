package com.codegym.dao.order;

import com.codegym.dao.DBConnection;
import com.codegym.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private Connection connection = DBConnection.getConnection();


    public OrderDao() {
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders; ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int stone_id = resultSet.getInt("stone_id");
                int quantity = resultSet.getInt("quantity");
                String date = resultSet.getString("date");
                Order order = new Order(id, user_id, stone_id, quantity, date);
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                int stone_id = resultSet.getInt("stone_id");
                int quantity = resultSet.getInt("quantity");
                String date = resultSet.getString("date");
                order = new Order(id, user_id, stone_id, quantity, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean create(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (user_id,stone_id,quantity, date) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setInt(2, order.getStone_id());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setString(4, order.getDate());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET date =? WHERE id=?");
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
        return false;
    }

}
