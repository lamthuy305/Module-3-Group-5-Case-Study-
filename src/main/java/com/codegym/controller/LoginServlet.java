package com.codegym.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("login-form-v16/Login_v16/login.jsp");
                dispatcher.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                if(username.equals("admin")&&password.equals("admin"))
                {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(request,response);
                }
                else {
                    request.setAttribute("username",username);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("homelogout.jsp");
                    dispatcher.forward(request, response);
                }
                break;
        }
    }
}