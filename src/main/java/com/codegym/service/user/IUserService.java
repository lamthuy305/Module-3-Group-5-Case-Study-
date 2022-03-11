package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IUserService extends IGeneralService<User> {
    List<User> getAllGuestUser();
    int findRoleId(String username,String password);
    boolean checkUserNameExist(String username);
    boolean isValidPassword(String password);
    List<User> findUserByUserName(String q);
    User findByUsername(String username);


}
