package com.codegym.dao.user;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.User;

import java.util.List;

public interface IUserDao extends IGeneralDao<User> {
    List<User> getAllGuestUser();
//    boolean checkLogin(String username, String password);
    int findRoleId(String username,String password);
    boolean checkUserNameExist(String username);
}
