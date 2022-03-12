package com.codegym.controller;

import com.codegym.dao.category.CategoryDao;
import com.codegym.dao.image.ImageDao;
import com.codegym.dao.order.OrderDao;
import com.codegym.dao.stone.StoneDao;
import com.codegym.dao.user.UserDao;
import com.codegym.model.Image;
import com.codegym.model.Image_Stone;
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

@WebServlet(name = "ImageServlet", value = "/image")
public class ImageServlet extends HttpServlet {

    private IStoneService stoneService;
    private ICategoryService categoryService;
    private IImageService imageService;
    private IOrderService orderService;
    private IUserService userService;

    public ImageServlet() {
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
                formupdateImage(request, response);
                break;
            }
            case "delete": {
                formDeleteImage(request, response);
                break;
            }
            case "create": {
                formCreateImage(request, response);
                break;
            }
            case "view": {
                formViewImage(request, response);
                break;
            }
            default: {
                formListImage(request, response);
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
                createImage(request, response);
                break;

            }
            case "delete": {
                deleteImage(request, response);
                break;
            }
            case "edit": {
                updateImage(request, response);
                break;
            }
        }
    }

    private void formListImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        List<Image_Stone> image_stones = imageService.findAllByStoneName();
        String q = request.getParameter("q");
        if (q != null) {
            image_stones = imageService.findByStoneName(q);
        }
        request.setAttribute("image_stones", image_stones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/image/list.jsp");
        dispatcher.forward(request, response);
    }

    private void formViewImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Image image = imageService.findById(id);
        request.setAttribute("image", image);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/image/view.jsp");
        dispatcher.forward(request, response);
    }

    private void formCreateImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Stone> stones = stoneService.findAll();
        request.setAttribute("stones", stones);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/image/create.jsp");
        dispatcher.forward(request, response);
    }

    private void formDeleteImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Image image = imageService.findById(id);
        request.setAttribute("image", image);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/image/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void formupdateImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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



    private void updateImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String link = request.getParameter("link");
        int stone_id = Integer.parseInt(request.getParameter("stone_id"));
        Image image = new Image(id, link, stone_id);
        imageService.updateById(id, image);
        response.sendRedirect("/image");
    }

    private void deleteImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        imageService.deleteById(id);
        response.sendRedirect("/image");
    }

    private void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int stone_Id = Integer.parseInt(request.getParameter("stone_id"));
        if (stone_Id != 0) {
            String link = request.getParameter("link");
            Image image = new Image(link, stone_Id);
            imageService.create(image);
            response.sendRedirect("/image");
            return;
        } else {
            List<Stone> stones = stoneService.findAll();
            String msg = "Select type of Stone";
            request.setAttribute("msg", msg);
            request.setAttribute("stones", stones);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/image/create.jsp");
            dispatcher.forward(request, response);
        }
    }

}
