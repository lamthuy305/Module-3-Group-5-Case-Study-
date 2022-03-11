package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.Category;
import com.codegym.model.Image;
import com.codegym.model.Stone;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserViewServlet", value = "/home")
public class UserViewServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;

    public UserViewServlet() {
        this.stoneService = new StoneService(new StoneDao());
        this.categoryService = new CategoryService(new CategoryDao());
        this.imageService = new ImageService(new ImageDao());
        this.orderService = new OrderService(new OrderDao());
        this.userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "viewcategory": {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Stone> stones = stoneService.findAllByCategory(id);
                request.setAttribute("stones", stones);
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userview/viewcategory.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "viewstone": {
                int id = Integer.parseInt(request.getParameter("id"));
                Stone stone = stoneService.findById(id);
                List<Image> images = imageService.findAllByStone_ID(id);
                request.setAttribute("stone", stone);
                request.setAttribute("images", images);
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userview/viewstone.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "seachstone": {
                String q = request.getParameter("q");
                List<Stone> stones = stoneService.findAllByName(q);
                request.setAttribute("stones", stones);
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userview/viewallstonebyseach.jsp");
                dispatcher.forward(request, response);
                break;
            }

            default: {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", user);
//                request.setAttribute("user", user);
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
