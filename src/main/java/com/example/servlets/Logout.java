package com.example.servlets;

import java.io.*;

import com.example.SessionUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        new SessionUtil(request, response).deleteSession();

        response.sendRedirect("index.jsp");

    }
}