package com.codegym.service.user;

import com.codegym.dao.user.IUserDao;
import com.codegym.model.User;

import java.util.List;
import java.util.regex.Pattern;

public class UserService implements IUserService {
    public static final String REGEX_FOR_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
    public static final String PASSWORD_ERORR = "Password Old sai";
    public static final String PASSWORD_NEW_PHAI_KHAC_PASSWORD_OLD = "Password New phai khac Password Old \n";
    public static final String PASSWORD_NEW_ERORR = "Password new sai";
    public static final String PASSWORD_NEW_KHAC_NHAU = "Password new khac nhau";
    IUserDao userDao;

    public UserService(IUserDao userDao) {
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

    @Override
    public int findRoleId(String username, String password) {
        return userDao.findRoleId(username, password);
    }

    @Override
    public boolean checkUserNameExist(String username) {
        return userDao.checkUserNameExist(username);
    }

    @Override
    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(REGEX_FOR_PASSWORD);
        return pattern.matcher(password).matches();
    }

    @Override
    public List<User> findUserByUserName(String q) {
        q = "%" + q + "%";
        return userDao.findUserByUserName(q);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public String checkPasswordOld(String password, String passwordOld) {
        String msg = null;
        if (!password.equals(passwordOld)) {
            msg = PASSWORD_ERORR;
        }
        return msg;
    }

    @Override
    public String checkPasswordOldAndNew(String passwordOld, String passwordNew) {
        String msg = null;
        if (passwordNew.equals(passwordOld)) {
            msg = PASSWORD_NEW_PHAI_KHAC_PASSWORD_OLD;
        }
        return msg;
    }

    @Override
    public String checkPasswordNew(String passwordNew) {
        String msg = null;
        Pattern pattern = Pattern.compile(REGEX_FOR_PASSWORD);
        if (!pattern.matcher(passwordNew).matches()) {
            msg = PASSWORD_NEW_ERORR;
        }
        return msg;
    }

    @Override
    public String checkEnterPasswordNew(String passwordNew, String enterPasswordNew) {
        String msg = null;
        if (!passwordNew.equals(enterPasswordNew)) {
            msg = PASSWORD_NEW_KHAC_NHAU;
        }

        return msg;
    }
}