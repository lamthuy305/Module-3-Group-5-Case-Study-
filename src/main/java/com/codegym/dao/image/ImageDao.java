package com.codegym.dao.image;

import com.codegym.dao.DBConnection;
import com.codegym.model.Image;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDao implements IImageDao {
    public static final String SQL_SELECT_ALL_IMAGE = "SELECT * FROM image;";
    private Connection connection = DBConnection.getConnection();

    public ImageDao() {
    }

    @Override
    public List<Image> findAll() {
        List<Image> images = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_IMAGE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String link = resultSet.getString("link");
                int stone_id = resultSet.getInt("stone_id");
                Image image = new Image(id, link, stone_id);
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    @Override
    public Image findById(int id) {
        Image image = new Image();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM image WHERE id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String link = resultSet.getString("link");
                int stone_id = resultSet.getInt("stone_id");
                image = new Image(id, link, stone_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public boolean create(Image image) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO image (link,stone_id) VALUES (?,?);");
            preparedStatement.setString(1, image.getLink());
            preparedStatement.setInt(2, image.getStone_id());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Image image) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE image SET link=?,stone_id=? WHERE id=?");
            preparedStatement.setString(1, image.getLink());
            preparedStatement.setDouble(2, image.getStone_id());
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
            CallableStatement callableStatement = connection.prepareCall("DELETE FROM image WHERE id=?;");
            callableStatement.setInt(1, id);
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Image> findAllById(int id) {
        List<Image> images = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM image WHERE stone_id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idImage = resultSet.getInt("id");
                String link = resultSet.getString("link");
                int stone_id = resultSet.getInt("stone_id");
                Image image = new Image(idImage, link, stone_id);
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
}
