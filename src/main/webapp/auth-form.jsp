<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authentication</title>
</head>
    <body>

        <% String error = request.getParameter("error"); %>
        <% if (error != null) {%>
            <%= error %>
            <% if (error.indexOf("password") == -1) { %>
                <a href="registration-form.jsp"> - register</a><br/>
            <%}%>
            </br>
        <%}%>

        <form action="/authentication-after">
            Login: <input type="text" name="login"><br>
            Password: <input type="text" name="password"><br>
            <input type="submit" value="Enter">
        </form>

    </body>
</html>
