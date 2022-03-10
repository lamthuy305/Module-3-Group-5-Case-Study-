package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.model.Category;
import com.codegym.model.Image;
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

@WebServlet(name = "ImageServlet", value = "/image")
public class ImageServlet extends HttpServlet {

    private ICategoryService categoryService;
    private IStoneService stoneService;
    private IImageService imageService;

    public ImageServlet() {
        this.categoryService = new CategoryService(new CategoryDao());
        this.stoneService = new StoneService((new StoneDao()));
        this.imageService = new ImageService(new ImageDao());
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
                Image image = imageService.findById(id);
                int ston_id = image.getStone_id();
                Stone stone = stoneService.findById(ston_id);
                List<Stone> stones = stoneService.findAll();
                request.setAttribute("image", image);
                request.setAttribute("stone", stone);
                request.setAttribute("stones", stones);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/image/edit.jsp");
                dispatcher.forward(request, response);
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                Image image = imageService.findById(id);
                request.setAttribute("image", image);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/image/delete.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "create": {
                List<Stone> stones = stoneService.findAll();
                request.setAttribute("stones", stones);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/image/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "view": {
                int id = Integer.parseInt(request.getParameter("id"));
                Image image = imageService.findById(id);
                request.setAttribute("image", image);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/image/view.jsp");
                dispatcher.forward(request, response);
                break;
            }
            default: {
                List<Image> images = imageService.findAll();
                request.setAttribute("images", images);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/image/list.jsp");
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
                String link = request.getParameter("link");
                int stone_Id = Integer.parseInt(request.getParameter("stone_id"));
                Image image = new Image(link, stone_Id);
                imageService.create(image);
                response.sendRedirect("/image");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                imageService.deleteById(id);
                response.sendRedirect("/image");
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String link = request.getParameter("link");
                int stone_id = Integer.parseInt(request.getParameter("stone_id"));
                Image image = new Image(id, link, stone_id);
                imageService.updateById(id, image);
                response.sendRedirect("/image");
                break;
            }
        }
    }

}
