package com.codegym.dao.stone;

import com.codegym.dao.DBConnection;
import com.codegym.model.Stone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoneDao implements IStoneDao {
    public static final String SQL_SELECT_ALL_STONE = "SELECT * FROM stones;";
    public static final String SQL_SELECT_STONE_BY_ID = "SELECT * FROM stones WHERE id = ?;";
    public static final String SQL_SELECT_ALL_STONE_BY_CATEGORY = "SELECT * FROM stones WHERE category_id =?;";
    public static final String SQL_INSERT_STONE = "INSERT INTO stones (name,price,description,image,category_id) VALUES (?,?,?,?,?)";
    public static final String SQL_DELETE_STONE = "call delete_stone(?);";
    public static final String SQL_UPDATE_STONE = "UPDATE stones SET name=?, price=?, description=?,image=?,category_id=? WHERE id =?;";
    private Connection connection = DBConnection.getConnection();


    public StoneDao() {
    }

    @Override
    public List<Stone> findAll() {
        List<Stone> stones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_STONE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                int category_id = resultSet.getInt("category_id");
                Stone stone = new Stone(id, name, price, description, image, category_id);
                stones.add(stone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stones;
    }

    @Override
    public Stone findById(int id) {
        Stone stone = new Stone();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_STONE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_Id = resultSet.getInt("category_id");
                String image = resultSet.getString("image");
                stone = new Stone(id, name, price, description, image, category_Id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stone;
    }

    @Override
    public boolean create(Stone stone) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_STONE);
            preparedStatement.setString(1, stone.getName());
            preparedStatement.setDouble(2, stone.getPrice());
            preparedStatement.setString(3, stone.getDescription());
            preparedStatement.setString(4, stone.getImage());
            preparedStatement.setInt(5, stone.getCategory_id());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Stone stone) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STONE);
            preparedStatement.setString(1, stone.getName());
            preparedStatement.setDouble(2, stone.getPrice());
            preparedStatement.setString(3, stone.getDescription());
            preparedStatement.setString(4, stone.getImage());
            preparedStatement.setInt(5, stone.getCategory_id());
            preparedStatement.setInt(6, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_STONE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Stone> findAllByCategory(int id) {
        List<Stone> stones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_STONE_BY_CATEGORY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idStone = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                int category_id = resultSet.getInt("category_id");
                Stone stone = new Stone(idStone, name, price, description, image, category_id);
                stones.add(stone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stones;
    }

    @Override
    public List<Stone> findAllByName(String q) {
        List<Stone> stones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stones WHERE name like ?");
            preparedStatement.setString(1,q);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                int category_id = resultSet.getInt("category_id");
                Stone stone = new Stone(id, name, price, description, image, category_id);
                stones.add(stone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stones;
    }
}
