package com.codegym.dao.role;

import com.codegym.dao.DBConnection;
import com.codegym.model.Image;
import com.codegym.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDao implements IRoleDao {
    private Connection connection = DBConnection.getConnection();

    public RoleDao() {
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findById(int id) {
        Role role = new Role();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM role WHERE id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String roleName = resultSet.getString("roleName");
                role = new Role(id, roleName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean create(Role role) {
        return false;
    }

    @Override
    public boolean updateById(int id, Role role) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
