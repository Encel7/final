package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Role;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.implementation.OrderServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Delete implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public Delete() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderServiceImplementation();
        Integer tattooID = Integer.valueOf(request.getParameter("id"));
        Integer orderId = ((Order) request.getSession().getAttribute("Order")).getIdentity();
        try {
            orderService.deleteSouvenir(orderId, tattooID);
            if (orderService.calculateTotalPrice(orderId) == 0) {
                orderService.deleteByID(orderId);
                request.getSession().setAttribute("Order", null);
                response.sendRedirect("usermain.html");
            } else {
                request.setAttribute("command", "order_list");
                CommandCommon common = new CommandCommon();
                common.visitPage(request, response, "orderlist.html");
            }
        } catch (SQLException | ServletException | IOException | ConstantException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
