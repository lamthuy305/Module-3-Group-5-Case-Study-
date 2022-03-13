package com.codegym.controller;

import com.codegym.dao.user.UserDao;
import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import com.codegym.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private IUserService userService = new UserService(new UserDao());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("register");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showFormRegister(request, response);
                break;
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("register");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default: {
                checkRegister(request, response);
                break;
            }
        }
    }

    private void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/register.jsp");
        dispatcher.forward(request, response);
    }

    private void checkRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        boolean isUserExist = userService.checkUserNameExist(username);
        boolean isValidPassword = userService.isValidPassword(password);
        if (isUserExist || !isValidPassword) {
            request.setAttribute("message1", "Username or password is invalid");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-form-v16/register.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("message2", "Register success!");
            User user = new User(username, password, Date.valueOf(birthday), address, email, 2);
            userService.create(user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-form-v16/register.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}