package com.example.servlets;

import java.io.*;

import com.example.SessionUtil;
import com.example.User;
import com.example.UserDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/authentication-after")
public class AuthenticationAfter extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String loginParam = request.getParameter("login");
        String passwordParam = request.getParameter("password");

        User user = null;

        try {

            UserDAO userDAO = new UserDAO();
            user = userDAO.read(loginParam);

            if (user == null) {
                String error = "user not found";
                response.sendRedirect("/auth-form.jsp?error=" + error);
                return;
            }

            if (!user.getPassword().equals(passwordParam)) {
                String error = "invalid password";
                response.sendRedirect("/auth-form.jsp?error=" + error);
                return;
            }

        } catch (Exception e) {
            String error = e.getMessage();
            response.sendRedirect("/auth-form.jsp?error=" + error);
            return;
        }

        new SessionUtil(request, response).createSession(user);

        response.sendRedirect("/lobby.jsp");

    }
}