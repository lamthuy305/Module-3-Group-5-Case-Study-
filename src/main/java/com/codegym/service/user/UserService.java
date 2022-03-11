package com.codegym.service.user;

import com.codegym.dao.user.IUserDao;
import com.codegym.model.User;

import java.util.List;
import java.util.regex.Pattern;

public class UserService implements IUserService{
    public static final String REGEX_FOR_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
    IUserDao userDao;

    public UserService(IUserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public boolean create(User user) {
        return userDao.create(user);
    }

    @Override
    public boolean updateById(int id, User user) {
        return userDao.updateById(id, user);
    }

    @Override
    public boolean deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<User> getAllGuestUser() {
        return userDao.getAllGuestUser();
    }
//
//    @Override
//    public boolean checkLogin(String username, String password) {
//        return userDao.checkLogin(username,password);
//    }

    @Override
    public int findRoleId(String username, String password) {
        return userDao.findRoleId(username,password);
    }

    @Override
    public boolean checkUserNameExist(String username) {
        return userDao.checkUserNameExist(username);
    }

    @Override
    public boolean isValidPassword(String password) {
        Pattern pattern=Pattern.compile(REGEX_FOR_PASSWORD);
        return pattern.matcher(password).matches();
    }
}