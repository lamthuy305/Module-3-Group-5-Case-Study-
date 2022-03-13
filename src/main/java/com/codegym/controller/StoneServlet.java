package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.Category;
import com.codegym.model.Image;
import com.codegym.model.Stone;
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
import java.util.List;

@WebServlet(name = "StoneServlet", value = "/stones")
public class StoneServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;

    public StoneServlet() {
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
                formUpdateStone(request, response);
                break;
            }
            case "delete": {
                formDeleteStone(request, response);
                break;
            }
            case "create": {
                formCreateStone(request, response);
                break;
            }
            case "view": {
                formViewStone(request, response);
                break;
            }
            default: {
                formListStone(request, response);
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
                createStone(request, response);
                break;
            }
            case "delete": {
                deleteStone(request, response);
                break;
            }
            case "edit": {
                updateStone(request, response);
                break;
            }
        }
    }

    private void formListStone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        List<Stone> stones = stoneService.findAll();
        String q = request.getParameter("q");
        if (q != null) {
            stones = stoneService.findAllByName(q);
        }
        request.setAttribute("stones", stones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/list.jsp");
        dispatcher.forward(request, response);
    }

    private void formViewStone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Image> images = imageService.findAllByStone_ID(id);
        Stone stone = stoneService.findById(id);
        request.setAttribute("images", images);
        request.setAttribute("stone", stone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/view.jsp");
        dispatcher.forward(request, response);
    }

    private void formCreateStone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/create.jsp");
        dispatcher.forward(request, response);
    }

    private void formDeleteStone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Stone stone = stoneService.findById(id);
        request.setAttribute("stone", stone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void formUpdateStone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Stone stone = stoneService.findById(id);
        int category_id = stone.getCategory_id();
        Category category = categoryService.findById(category_id);
        List<Category> categories = categoryService.findAll();
        request.setAttribute("stone", stone);
        request.setAttribute("category", category);
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/edit.jsp");
        dispatcher.forward(request, response);
    }


    private void updateStone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        Stone stone = new Stone(name, price, description, image, category_id);
        stoneService.updateById(id, stone);
        response.sendRedirect("/stones");
    }

    private void deleteStone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        stoneService.deleteById(id);
        response.sendRedirect("/stones");
    }

    private void createStone(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        if (category_id != 0) {
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            Stone stone = new Stone(name, price, description, image, category_id);
            stoneService.create(stone);
            response.sendRedirect("/stones");
            return;
        } else {
            List<Category> categories = categoryService.findAll();
            String msg = "Please pick a Category";
            request.setAttribute("categories", categories);
            request.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/stone/create.jsp");
            dispatcher.forward(request, response);
        }
    }
}
