package com.codegym.dao.category;

import com.codegym.dao.DBConnection;
import com.codegym.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao {

    public static final String SQL_SELECT_ALL_CATEGORY = "SELECT * FROM category;";
    public static final String SQL_SELECT_CATEGORY_BY_ID = "SELECT * FROM category Where id = ?;";
    public static final String SQL_DELETE_CATEGORY = "call delete_category(?);";
    public static final String INSERT_INTO_CATEGORY_NAME_VALUES = "INSERT INTO category (name) VALUES (?)";
    public static final String UPDATE_CATEGORY_SET_NAME_WHERE_ID = "UPDATE category SET name=? WHERE id =?;";
    public static final String SELECT_FROM_CATEGORY_WHERE_NAME_LIKE = "SELECT * FROM category WHERE name like ?";
    private Connection connection = DBConnection.getConnection();


    public CategoryDao() {
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        Category category = new Category();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean create(Category category) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CATEGORY_NAME_VALUES);
            preparedStatement.setString(1, category.getName());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Category category) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SET_NAME_WHERE_ID);
            preparedStatement.setString(1, category.getName());
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

    @Override
    public boolean deleteCategoryUsingProcedure(int id) {
        try {
            CallableStatement callableStatement = connection.prepareCall(SQL_DELETE_CATEGORY);
            callableStatement.setInt(1, id);
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> findAllByName(String q) {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CATEGORY_WHERE_NAME_LIKE);
            preparedStatement.setString(1, q);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

}
