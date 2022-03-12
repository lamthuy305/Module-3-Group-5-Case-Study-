package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.User;
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
import java.sql.Date;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")

public class UserServlet extends HttpServlet {
    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;

    public UserServlet() {
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
            default: {
                formListUser(request, response);
                break;
            }
            case "view": {
                formViewUser(request, response);
                break;
            }
            case "create": {
                formCreateUser(request, response);
                break;
            }
            case "update": {
                formUpdateUser(request, response);
                break;
            }
            case "delete": {
                showDeleteForm(request, response);
                break;
            }
            case "changepassword": {
                formChangePassword(request, response);
                break;
            }
        }
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
            case "changepassword": {
                changePasswordUser(request, response);
                break;
            }
        }
    }

    private void formChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form-v16/changepassword.jsp");
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void formUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/update.jsp");
        dispatcher.forward(request, response);
    }

    private void formCreateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form-v16/adminDoRegister.jsp");
        dispatcher.forward(request, response);
    }

    private void formViewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/view.jsp");
        dispatcher.forward(request, response);
    }

    private void formListUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("user", user);
        request.setAttribute("user", user);
        List<User> users;
        String q = request.getParameter("q");
        if (q != null) {
            users = userService.findUserByUserName(q);
        } else {
            users = userService.findAll();
        }
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/list.jsp");
        dispatcher.forward(request, response);
    }


    private void changePasswordUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String passwordOld = request.getParameter("passwordOld");
        String passwordNew = request.getParameter("passwordNew");
        String enterPasswordNew = request.getParameter("enterPasswordNew");
        String msg1 = userService.checkPasswordOld(user.getPassword(), passwordOld);
        String msg2 = userService.checkPasswordNew(passwordNew);
        String msg3 = userService.checkEnterPasswordNew(passwordNew, enterPasswordNew);
        String msg4 = userService.checkPasswordOldAndNew(passwordOld, passwordNew);
        if (msg1 != null || msg2 != null || msg3 != null || msg4 != null) {
            request.setAttribute("user", user);
            request.setAttribute("msg1", msg1);
            request.setAttribute("msg2", msg2);
            request.setAttribute("msg3", msg3);
            request.setAttribute("msg4", msg4);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form-v16/changepassword.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
            int id = user.getId();
            String username = user.getUsername();
            Date birthday = user.getBirthday();
            String address = user.getAddress();
            String email = user.getEmail();
            int role_id = user.getRole_id();
            User userNew = new User(id, username, passwordNew, birthday, address, email, role_id);
            userService.updateById(id, userNew);
            String msg = "Change Password success";
            session.setAttribute("user", userNew);
            request.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login-form-v16/changepassword.jsp");
            dispatcher.forward(request, response);
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
        User user = new User(id, name, password, birthday, address, email, role_id);
        userService.updateById(id, user);
        response.sendRedirect("/users");
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        User user = new User(username, password, birthday, address, email, role_id);
        userService.create(user);
        response.sendRedirect("/users");
    }
}