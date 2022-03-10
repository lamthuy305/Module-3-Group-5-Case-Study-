package com.codegym.service.user;

import com.codegym.dao.user.IUserDao;
import com.codegym.model.User;

import java.util.List;

public class UserService implements IUserService{
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
}