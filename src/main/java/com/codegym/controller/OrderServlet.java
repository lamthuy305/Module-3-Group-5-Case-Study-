package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.order_detail.IOrderDetailDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.model.*;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.image.IImageService;
import com.codegym.service.image.ImageService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.order.OrderService;
import com.codegym.service.stone.IStoneService;
import com.codegym.service.stone.StoneService;

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


    public OrderServlet() {
        this.stoneService = new StoneService(new StoneDao());
        this.categoryService = new CategoryService(new CategoryDao());
        this.imageService = new ImageService(new ImageDao());
        this.orderService = new OrderService(new OrderDao());

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
                request.setAttribute("stones", stones);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/order/create.jsp");
                dispatcher.forward(request, response);
                break;
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
                List<Order> orders = orderService.findAll();
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
                Order order = new Order(user_id, date);
                orderService.updateById(id, order);
                response.sendRedirect("/orders");
                break;
            }
        }
    }
}
