package com.codegym.controller;


import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.Category;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.image.IImageService;
import com.codegym.service.image.ImageService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.order.OrderService;
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

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;

    public LoginServlet() {
        this.stoneService = new StoneService(new StoneDao());
        this.categoryService = new CategoryService(new CategoryDao());
        this.imageService = new ImageService(new ImageDao());
        this.orderService = new OrderService(new OrderDao());
        this.userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/Login_v16/login.jsp");
                dispatcher.forward(request, response);
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
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                int role_id = userService.findRoleId(username, password);
//                boolean isUser = userService.checkLogin(username, password);
                if (role_id == 1) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(request, response);
                } else if (role_id == 2) {
                    List<Category> categories = categoryService.findAll();
                    request.setAttribute("username", username);
                    request.setAttribute("categories", categories);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("homelogout.jsp");
                    dispatcher.forward(request, response);
                }
            {
                request.setAttribute("message", "Username or password is not exactly!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/Login_v16/login.jsp");
                dispatcher.forward(request, response);
            }
            break;
        }
    }
}