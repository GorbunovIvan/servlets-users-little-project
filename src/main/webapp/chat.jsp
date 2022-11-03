<%@ page import="com.example.User" %>
<%@ page import="com.example.UserDAO" %>
<%@ page import="com.example.SessionUtil" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%
    User user = new SessionUtil(request, response).getCurrentUser();

    if (user == null) {
        response.sendRedirect("/index.jsp");
        return;
    }

    UserDAO userDAO = new UserDAO();
    User partner = userDAO.read(request.getParameter("user"));
%>

<head>
    <title>Chat with - <%= partner.getLogin() %></title>
</head>
<body>

  <p><%= partner.getLogin() %></p>

  <p> - bla bla bla</p>
  <p> - bla bla bla</p>

</body>
</html>
