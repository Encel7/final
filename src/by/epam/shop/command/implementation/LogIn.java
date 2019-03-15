package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.command.common.JSPPath;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.implementation.OrderServiceImplementation;
import by.epam.shop.service.implementation.UserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LogIn implements Command {

    List<Role> roles;

    public LogIn() {
        roles = Arrays.asList(Role.ANONYMOUS);
    }

    private static final Logger LOG = LogManager.getLogger("name");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImplementation();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        OrderService orderService = new OrderServiceImplementation();

        try {
            if (userService.searchByPersonal(login, password).size() != 0) {
                User user = userService.searchByPersonal(login, password).get(0);
                if (orderService.searchActiveOrder(user.getIdentity()) != null) {
                    Order order = orderService.searchActiveOrder(user.getIdentity());
                    request.getSession().setAttribute("Order", order);
                }
                request.getSession().setAttribute("User", user);
                response.sendRedirect("usermain.html");
            } else {
                request.setAttribute("fail", "Incorrect Login or Password");
                CommandCommon common = new CommandCommon();
                common.visitPage(request, response, JSPPath.INDEX.getUrl());
            }
        } catch (SQLException | IOException | ServletException | ConstantException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
