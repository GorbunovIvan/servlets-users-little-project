package com.example;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;

public class SessionUtil {

    private final HttpSession session;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private final UserDAO userDAO = new UserDAO();

    public SessionUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        session = request.getSession();
    }

    public HttpSession getSession() {
        return session;
    }

    public <V> V getSessionAttribute(String key) {
        return (V)session.getAttribute(key);
    }

    public <V> void setSessionAttribute(String key, V value) {
        session.setAttribute(key, value);
    }

    public String getCookie(String name) {

        Cookie[] cookies = request.getCookies();

        if (cookies == null)
            return null;

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .findFirst()
                .map(Cookie::getName)
                .orElse(null);

    }

    public User getCurrentUser() {

        User user = getSessionAttribute("user");

        if (user == null) {

            String login = getCookie("user");

            if (login != null) {

                user = userDAO.read(login);

                if (user != null)
                    createSession(user);

            }
        }

        return user;

    }

    public void createSession(@NotNull User user) {

        session.setAttribute("user", user);

        Cookie cookie = new Cookie("user", user.getLogin());
        cookie.setMaxAge(3600); // in seconds

        response.addCookie(cookie);

    }

    public void deleteSession() {

        User user = getSessionAttribute("user");

        if (user != null) {

            session.setAttribute("user", null);

            Cookie cookie = new Cookie("user", "");
            cookie.setMaxAge(0); // deleting

            response.addCookie(cookie);

        }
    }

}
