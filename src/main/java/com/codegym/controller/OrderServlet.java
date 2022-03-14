package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.order_detail.OrderDetailDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.*;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.image.IImageService;
import com.codegym.service.image.ImageService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.order.OrderService;
import com.codegym.service.orderDetail.IODService;
import com.codegym.service.orderDetail.ODService;
import com.codegym.service.stone.IStoneService;
import com.codegym.service.stone.StoneService;
import com.codegym.service.user.IUserService;
import com.codegym.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/orders")
public class OrderServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;
    private IODService odService;


    public OrderServlet() {
        this.stoneService = new StoneService(new StoneDao());
        this.categoryService = new CategoryService(new CategoryDao());
        this.imageService = new ImageService(new ImageDao());
        this.orderService = new OrderService(new OrderDao());
        this.userService = new UserService(new UserDao());
        this.odService = new ODService(new OrderDetailDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit": {
                formUpdateOrder(request, response);
                break;
            }
            case "delete": {
                formDeleteOrder(request, response);
                break;
            }
            case "create": {
                formCreateOrder(request, response);
                break;
            }
            case "view": {
                formViewOrder(request, response);
                break;
            }
            case "reset": {
                formReset(request, response);
                break;
            }
            default: {
                formListOrder(request, response);
                break;
            }
        }
    }

    private void formReset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String stringCount = (String) session.getAttribute("count");
        int count = Integer.parseInt(stringCount);
        stringCount = null;
        session.setAttribute("count", stringCount);
        List<Order> orders;
        orders = orderService.findAllDESC(count);
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order/list.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "createorderdetail": {
                createOrderDetail(request, response);
                break;
            }

            case "create": {
                createOrder(request, response);
                break;
            }
            case "delete": {
                deleteOrder(request, response);
                break;
            }
            case "edit": {
                updateOrder(request, response);
                break;
            }
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int order_id = (int) session.getAttribute("order_Id_Now");
        int user_id = user.getId();
        String createDate = request.getParameter("createDate");
        if (orderService.checkCreateDateAfterDateNow(createDate)) {
            Order order = new Order(order_id, user_id, createDate);
            orderService.create(order);
            int count = 0;
            String stringCount = (String) session.getAttribute("count");
            if (stringCount != null) {
                count = Integer.parseInt(stringCount);
            }
            count = count + 1;
            stringCount = String.valueOf(count);
            session.setAttribute("count", stringCount);
            List<Stone> stones = stoneService.findAll();
            String msg = "Create Order Success";
            request.setAttribute("msg", msg);
            request.setAttribute("user", user);
            request.setAttribute("order", order);
            request.setAttribute("stones", stones);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/order/createorderdetail.jsp");
            dispatcher.forward(request, response);
        } else {
            String msg1 = "Create error";
            List<Stone> stones = stoneService.findAll();
            int max_Order_Id = orderService.maxOrder_idNow();
            int order_Id_Now = max_Order_Id + 1;
            request.setAttribute("order_Id_Now", order_Id_Now);
            request.setAttribute("msg1", msg1);
            request.setAttribute("user", user);
            request.setAttribute("stones", stones);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/order/create.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void createOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Stone> stones = stoneService.findAll();
        int order_id = (int) session.getAttribute("order_Id_Now");
        int stone_id = Integer.parseInt(request.getParameter("stone_id"));
        if (stone_id != 0) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            OrderDetail orderDetail = new OrderDetail(order_id, stone_id, quantity);
            odService.create(orderDetail);
            String msg1 = "Success !!! Mua tiep";
            request.setAttribute("msg1", msg1);
        } else {
            String msg2 = "Please!!! select stone";
            request.setAttribute("msg2", msg2);
        }
        request.setAttribute("user", user);
        request.setAttribute("stones", stones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order/createorderdetail.jsp");
        dispatcher.forward(request, response);
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String date = request.getParameter("date");
        Order order = new Order(id, user_id, date);
        orderService.updateById(id, order);
        response.sendRedirect("/orders");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.deleteById(id);
        response.sendRedirect("/orders");
    }


    private void formListOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    private void formViewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.findById(id);
        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order/view.jsp");
        dispatcher.forward(request, response);
    }

    private void formCreateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Stone> stones = stoneService.findAll();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int max_Order_Id = orderService.maxOrder_idNow();
        int order_Id_Now = max_Order_Id + 1;
        if (user != null) {
            session.setAttribute("order_Id_Now", order_Id_Now);
            request.setAttribute("user", user);
            request.setAttribute("stones", stones);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/order/create.jsp");
            dispatcher.forward(request, response);
        } else {
            String msg = "Please sign in before shopping";
            request.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void formUpdateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.findById(id);
        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void formDeleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.findById(id);
        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order/delete.jsp");
        dispatcher.forward(request, response);
    }
}
