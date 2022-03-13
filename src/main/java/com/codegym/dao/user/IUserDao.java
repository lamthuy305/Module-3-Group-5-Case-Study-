package com.codegym.dao.user;

import com.codegym.dao.IGeneralDao;
import com.codegym.model.User;

import java.util.List;

public interface IUserDao extends IGeneralDao<User> {
    List<User> getAllGuestUser();
    int findRoleId(String username,String password);
    boolean checkUserNameExist(String username);
    List<User> findUserByUserName(String q);
    User findByUsername(String username);

}
