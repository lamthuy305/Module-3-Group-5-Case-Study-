package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.order_detail.OrderDetailDao;
import com.codegym.dao.stone.StoneDao;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetailServlet", value = "/ordersDetail")
public class OrderDetailServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IODService odService;


    public OrderDetailServlet() {
        this.stoneService = new StoneService(new StoneDao());
        this.categoryService = new CategoryService(new CategoryDao());
        this.imageService = new ImageService(new ImageDao());
        this.orderService = new OrderService(new OrderDao());
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
                int id = Integer.parseInt(request.getParameter("id"));
                OrderDetail orderDetail = odService.findByIdOD(id);
                Stone stone = stoneService.findById(orderDetail.getStone_id());
                List<Stone> stones = stoneService.findAll();
                request.setAttribute("stone", stone);
                request.setAttribute("stones", stones);
                request.setAttribute("orderDetail", orderDetail);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/order/editorderdetail.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "delete": {
                formDeleteOrderDetail(request, response);
                break;
            }
            case "viewDetail": {
                formViewDetail(request, response);
                break;
            }
            case "vieworderdetail": {
                formViewOrderDetail(request, response);
                break;
            }
            case "vieworder": {
                formViewOrder(request, response);
                break;
            }
        }
    }

    private void formDeleteOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        odService.deleteById(id);
        response.sendRedirect("/orders");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit": {
                editOrderDetail(request, response);
                break;
            }
        }
    }

    private void editOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int od = Integer.parseInt(request.getParameter("order"));
        int st = Integer.parseInt(request.getParameter("stone"));
        int qu = Integer.parseInt(request.getParameter("quantity"));
        OrderDetail orderDetail = new OrderDetail(id, od, st, qu);
        odService.updateODById(id, orderDetail);
        response.sendRedirect("/orders");
    }

    private void formViewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int user_id = user.getId();
        List<Order> orders = orderService.findOderByUser(user_id);
        request.setAttribute("orders", orders);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userview/vieworder.jsp");
        dispatcher.forward(request, response);
    }

    private void formViewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        int order_id = Integer.parseInt(request.getParameter("id"));
        List<ViewOrderDetail> viewOrderDetails = odService.showOrderDetailById(order_id);
        request.setAttribute("categories", categories);
        request.setAttribute("viewOrderDetails", viewOrderDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userview/vieworderdetail.jsp");
        dispatcher.forward(request, response);
    }


    private void formViewDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<ViewOrderDetail> viewOrderDetails = odService.showOrderDetailById(id);
        request.setAttribute("viewOrderDetails", viewOrderDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/order/viewDetail.jsp");
        dispatcher.forward(request, response);
    }


}
