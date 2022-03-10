package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.model.Category;
import com.codegym.model.Stone;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.image.IImageService;
import com.codegym.service.image.ImageService;
import com.codegym.service.stone.IStoneService;
import com.codegym.service.stone.StoneService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {

    private ICategoryService categoryService;
    private IStoneService stoneService;
    private IImageService imageService;

    public CategoryServlet() {
        this.categoryService = new CategoryService(new CategoryDao());
        this.stoneService = new StoneService((new StoneDao()));
        this.imageService = new ImageService((new ImageDao()));
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
                Category category = categoryService.findById(id);
                request.setAttribute("category", category);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/edit.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = categoryService.findById(id);
                request.setAttribute("category", category);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/delete.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "create": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "view": {
                int category_Id = Integer.parseInt(request.getParameter("id"));
                List<Stone> stones = stoneService.findAllByCategory(category_Id);
                request.setAttribute("stones", stones);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/view.jsp");
                dispatcher.forward(request, response);
                break;
            }
            default: {
                List<Category> categories = categoryService.findAll();
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/list.jsp");
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
                String name = request.getParameter("name");
                Category category = new Category(name);
                categoryService.create(category);
                response.sendRedirect("/category");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                categoryService.deleteById(id);
                response.sendRedirect("/category");
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                Category category = new Category(name);
                categoryService.updateById(id, category);
                response.sendRedirect("/category");
                break;
            }
        }
    }

}
