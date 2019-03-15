package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.command.common.JSPPath;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.entity.User;
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

public class OrderList implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public OrderList() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        CommandCommon common = new CommandCommon();
        OrderService orderService = new OrderServiceImplementation();
        try {
            Integer sum = orderService.calculateTotalPrice(((Order) request.
                    getSession().getAttribute("Order")).getIdentity());
            List<Souvenir> order = orderService.takeAllSouvenir(((Order)request.
                    getSession().getAttribute("Order")).getIdentity());
            request.setAttribute("order", order);
            User user = (User) request.getSession().getAttribute("User");
            request.setAttribute("sum", sum*(1 - user.getDiscount()));
            common.visitPage(request, response, JSPPath.ORDER_LIST.getUrl());
        } catch (SQLException | ServletException | IOException | ConstantException e) {
            LOG.error(e);
        }

    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
