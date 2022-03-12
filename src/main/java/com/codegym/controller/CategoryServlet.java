package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.Category;
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

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;

    public CategoryServlet() {
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
                formUpdateCategory(request, response);
                break;
            }
            case "delete": {
                formdeleteCategory(request, response);
                break;
            }
            case "create": {
                formCreateCategory(request, response);
                break;
            }
            case "view": {
                formViewCategory(request, response);
                break;
            }
            default: {
                formListCategory(request, response);
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
                createCategory(request, response);
                break;
            }
            case "delete": {
                deleteCategory(request, response);
                break;
            }
            case "edit": {
                updateCategory(request, response);
                break;
            }
        }
    }

    private void formListCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        List<Category> categories = categoryService.findAll();
        String q = request.getParameter("q");
        if (q != null) {
            categories = categoryService.findAllByName(q);
        }
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/list.jsp");
        dispatcher.forward(request, response);
    }

    private void formViewCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category_Id = Integer.parseInt(request.getParameter("id"));
        List<Stone> stones = stoneService.findAllByCategory(category_Id);
        request.setAttribute("stones", stones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/view.jsp");
        dispatcher.forward(request, response);
    }

    private void formCreateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/create.jsp");
        dispatcher.forward(request, response);
    }

    private void formdeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void formUpdateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/edit.jsp");
        dispatcher.forward(request, response);
    }


    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(name);
        categoryService.updateById(id, category);
        response.sendRedirect("/category");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteById(id);
        response.sendRedirect("/category");
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (categoryService.checkCategory(name)) {
            String msg = "Category existed";
            request.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/category/create.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
            Category category = new Category(name);
            categoryService.create(category);
            response.sendRedirect("/category");
        }
    }

}
