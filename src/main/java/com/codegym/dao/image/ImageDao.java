package com.codegym.dao.image;

import com.codegym.dao.DBConnection;
import com.codegym.model.Image;
import com.codegym.model.Image_Stone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImageDao implements IImageDao {
    public static final String SQL_SELECT_ALL_IMAGE = "SELECT * FROM image;";
    public static final String SELECT_IMAGA_BY_NAME_STONE = "SELECT i.id,i.link,i.stone_id,s.name FROM image i join stones s on i.stone_id = s.id;";
    public static final String SELECT_A_IMAGE_BY_NAME = "SELECT i.id,i.link,i.stone_id,s.name FROM image i join stones s on i.stone_id = s.id WHERE s.name like ?;";
    public static final String SELECT_IMAGE_BY_ID = "SELECT * FROM image WHERE id=?;";
    public static final String CREATE_IMGAE = "INSERT INTO image (link,stone_id) VALUES (?,?);";
    public static final String UPDATE_BY_ID = "UPDATE image SET link=?,stone_id=? WHERE id=?";
    public static final String DELETE_FROM_IMAGE_WHERE_ID = "DELETE FROM image WHERE id=?;";
    public static final String SELECT_FROM_IMAGE_WHERE_STONE_ID = "SELECT * FROM image WHERE stone_id=?;";
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

    public List<Image_Stone> findAllByStoneName() {
        List<Image_Stone> image_stones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IMAGA_BY_NAME_STONE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String link = resultSet.getString("link");
                int stone_id = resultSet.getInt("stone_id");
                String stone_name = resultSet.getString("name");
                Image image = new Image(id, link, stone_id);
                Image_Stone image_stone = new Image_Stone(image, stone_name);
                image_stones.add(image_stone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image_stones;
    }

    public List<Image_Stone> findByStoneName(String q) {
        List<Image_Stone> image_stones = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_A_IMAGE_BY_NAME);
            preparedStatement.setString(1, q);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String link = resultSet.getString("link");
                int stone_id = resultSet.getInt("stone_id");
                String stone_name = resultSet.getString("name");
                Image image = new Image(id, link, stone_id);
                Image_Stone image_stone = new Image_Stone(image, stone_name);
                image_stones.add(image_stone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image_stones;
    }

    @Override
    public Image findById(int id) {
        Image image = new Image();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IMAGE_BY_ID);
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
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_IMGAE);
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
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
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
            CallableStatement callableStatement = connection.prepareCall(DELETE_FROM_IMAGE_WHERE_ID);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_IMAGE_WHERE_STONE_ID);
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
