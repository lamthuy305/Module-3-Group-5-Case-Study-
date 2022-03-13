
package com.codegym.dao.user;

import com.codegym.dao.DBConnection;
import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    public static final String SELECT_ALL_FROM_USER = "select * from users";
    public static final String SELECT_ONE_FROM_USER = "SELECT * FROM users where id = ?";
    public static final String INSERT_USER = "insert into users (username,password,birthday,address,email,role_id) values (?,?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, birthday = ?, address = ?, email=?,role_id =? where  id = ?";
    public static final String SELECT_ALL_GUEST_FROM_USERS = "select * from users where role_id = 2";
    public static final String FIND_USERNAME = "select username from users where username = ?";
    public static final String CALL_DELETE_USER = "call deleteUser(?);";
    public static final String SELECT_FROM_USERS_WHERE_USERNAME_LIKE = "SELECT * from users where username like ?;";
    public static final String SELECT_FROM_USERS_WHERE_USERNAME = "SELECT * FROM users WHERE username = ?";
    public static final String SELECT_ROLE_ID_FROM_USERS_WHERE_USERNAME_AND_PASSWORD = "select role_id from users where username= ? and password= ?";
    Connection connection = DBConnection.getConnection();

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int role_id = rs.getInt("role_id");
                User user = new User(id, username, password, birthday, address, email, role_id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ONE_FROM_USER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Date birthday = resultSet.getDate("birthday");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int role_id = resultSet.getInt("role_id");
                user = new User(id, username, password, birthday, address, email, role_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getRole_id());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getRole_id());
            preparedStatement.setInt(7, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            CallableStatement callableStatement = connection.prepareCall(CALL_DELETE_USER);
            callableStatement.setInt(1, id);
            return callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllGuestUser() {
        List<User> guest = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GUEST_FROM_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String password = rs.getString("password");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                String email = rs.getString("email");
                User user = new User(id, name, password, birthday, address, email);
                guest.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    public List<User> findUserByUserName(String q) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_USERNAME_LIKE);
            preparedStatement.setString(1, q);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Date birthday = resultSet.getDate("birthday");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int role_id = resultSet.getInt("role_id");
                User user = new User(id, username, password, birthday, address, email, role_id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                Date birthday = resultSet.getDate("birthday");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int role_id = resultSet.getInt("role_id");
                user = new User(id, username, password, birthday, address, email, role_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int findRoleId(String username, String password) {
        int role_id = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_ID_FROM_USERS_WHERE_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role_id = resultSet.getInt("role_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role_id;
    }

    public boolean checkUserNameExist(String username) {
        boolean isUsernameExist = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isUsernameExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUsernameExist;
    }
}

