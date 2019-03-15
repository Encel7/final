package by.epam.shop.command.common;

import by.epam.shop.entity.Role;
import by.epam.shop.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandCommon {
    public boolean isUserExist(HttpServletRequest request) {
        return (request.getSession().getAttribute("User") != null);
    }

    public Role takeUsersRole(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("User");
        return user.getRole();
    }

    public void visitPage(HttpServletRequest request,
                          HttpServletResponse response,
                          String pageUrl) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageUrl);
        dispatcher.forward(request, response);
    }
}
