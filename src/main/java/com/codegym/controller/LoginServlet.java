package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.Category;
import com.codegym.model.User;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.stone.IStoneService;
import com.codegym.service.stone.StoneService;
import com.codegym.service.user.IUserService;
import com.codegym.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private IUserService userService = new UserService(new UserDao());
    private ICategoryService categoryService = new CategoryService(new CategoryDao());
    private IStoneService stoneService = new StoneService(new StoneDao());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showFormLogin(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                checkLogin(request, response);
                break;
        }
    }


    private void showFormLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/login.jsp");
        dispatcher.forward(request, response);
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int role_id = userService.findRoleId(username, password);
        if (role_id == 1) {
            User user = userService.findByUsername(username);
            session.setAttribute("user", user);
            request.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form-v16/admin.jsp");
            dispatcher.forward(request, response);
        } else if (role_id == 2) {
            User user = userService.findByUsername(username);
            session.setAttribute("user", user);
            request.setAttribute("username", username);
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form-v16/homelogout.jsp");
            dispatcher.forward(request, response);
        }
        request.setAttribute("message", "Username or password is not exactly!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/login.jsp");
        dispatcher.forward(request, response);
    }
}