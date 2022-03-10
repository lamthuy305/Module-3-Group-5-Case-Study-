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
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private IUserService userService;

    public UserServlet() {
        this.userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default: {
                showListUsers(request, response);
                break;
            }
            case "view": {
                showUserDetail(request, response);
                break;
            }
            case "create": {
                showCreateForm(request, response);
                break;
            }
            case "update": {
                showUpdateForm(request, response);
                break;
            }
            case "delete": {
                showDeleteForm(request, response);
                break;
            }
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/update.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showUserDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/view.jsp");
        dispatcher.forward(request, response);
    }

    private void showListUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                createUser(request, response);
                break;
            }
            case "update": {
                updateUser(request, response);
                break;
            }
            case "delete": {
                deleteUser(request, response);
                break;
            }
            case "showGuest":{

            }
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteById(id);
        response.sendRedirect("/users");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        User user = new User(id,name, password, birthday, address, email,role_id);
        userService.updateById(id, user);
        response.sendRedirect("/users");
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        User user = new User(name, password, birthday, address, email, role_id);
        userService.create(user);
        response.sendRedirect("/users");
    }
}