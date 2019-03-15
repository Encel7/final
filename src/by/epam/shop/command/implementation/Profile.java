package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.command.common.JSPPath;
import by.epam.shop.entity.Balance;
import by.epam.shop.entity.Order;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.BalanceService;
import by.epam.shop.service.OrderService;
import by.epam.shop.service.implementation.BalanceServiceImplementation;
import by.epam.shop.service.implementation.OrderServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Profile implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public Profile() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("success") != null) {
            request.setAttribute("success", request.getSession().getAttribute("success"));
            request.getSession().setAttribute("success", null);
        }
        BalanceService balanceService = new BalanceServiceImplementation();
        OrderService orderService = new OrderServiceImplementation();
        List<Order> orders = new ArrayList<>();
        try {
            Balance balance = balanceService.searchByOwner(((User)request.
                    getSession().getAttribute("User")).getIdentity());
            orders = orderService.searchInactiveOrder(((User)request.
                    getSession().getAttribute("User")).getIdentity());
            request.setAttribute("Balance", balance);
            request.setAttribute("orders", orders);
            CommandCommon common = new CommandCommon();
            common.visitPage(request, response, JSPPath.PROFILE.getUrl());
        } catch (SQLException | ServletException | IOException | ConstantException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
