package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.*;
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

@WebServlet(name = "OrderServlet", value = "/orders")
public class OrderServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;


    public OrderServlet() {
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
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                Order order = orderService.findById(id);
                request.setAttribute("order", order);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/order/edit.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                Order order = orderService.findById(id);
                request.setAttribute("order", order);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/order/delete.jsp");
                dispatcher.forward(request, response);
            }
            case "create": {
                List<Stone> stones = stoneService.findAll();
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (user != null) {
                    session.setAttribute("user", user);
                    request.setAttribute("user", user);
                    request.setAttribute("stones", stones);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/order/create.jsp");
                    dispatcher.forward(request, response);
                    break;
                } else {
                    String msg = "Please sign in before shopping";
                    request.setAttribute("msg", msg);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/login.jsp");
                    dispatcher.forward(request, response);
                    break;
                }

            }
            case "view": {
                int id = Integer.parseInt(request.getParameter("id"));
                Order order = orderService.findById(id);
                request.setAttribute("order", order);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/order/view.jsp");
                dispatcher.forward(request, response);
                break;
            }

            default: {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                session.setAttribute("user", user);
                request.setAttribute("user", user);
                List<Order> orders;
                String q = request.getParameter("q");
                if (q != null) {
                    orders = orderService.findOrderByOrderID(q);
                } else {
                    orders = orderService.findAll();
                }
                request.setAttribute("orders", orders);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/order/list.jsp");
                dispatcher.forward(request, response);
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
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                String date = request.getParameter("date");
                Order order = new Order(user_id, date);
                orderService.create(order);
                response.sendRedirect("/orders");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                orderService.deleteById(id);
                response.sendRedirect("/orders");
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                String date = request.getParameter("date");
                Order order = new Order(id, user_id, date);
                orderService.updateById(id, order);
                response.sendRedirect("/orders");
                break;
            }
        }
    }
}
