package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
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
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddToOrder implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public AddToOrder() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        CommandCommon common = new CommandCommon();
        OrderService orderService = new OrderServiceImplementation();
        Integer tattooID = Integer.valueOf(request.getParameter("id"));
        BalanceService balanceService = new BalanceServiceImplementation();
        try {
            Balance balance = balanceService.searchByOwner(((User)request.getSession().
                    getAttribute("User")).getIdentity());
            if (balance.getCurrentBalance() >= 0) {
                if (orderService.searchActiveOrder(((User) request.
                        getSession().getAttribute("User")).getIdentity()) == null) {
                    orderService.addOrder(new Order(false, 0,
                            new Date(Calendar.getInstance().getTime().getTime()),
                            (User) request.
                                    getSession().getAttribute("User")));
                }
                Order order = orderService.searchActiveOrder(((User) request.
                        getSession().getAttribute("User")).getIdentity());
                request.getSession().setAttribute("Order", order);
                orderService.addSouvenirOrder(order.getIdentity(), tattooID);
                request.setAttribute("start", request.getParameter("start"));
                request.setAttribute("index", null);
                request.setAttribute("command", "main_page");
                request.setAttribute("paid", "Souvenir added to your cart");
                common.visitPage(request, response, "usermain.html");
            } else {
                request.setAttribute("fail", "pay the loan before buying");
                request.setAttribute("command", "main_page");
                common.visitPage(request, response, "usermain.html");
            }
        } catch (SQLException | IOException | ServletException | ConstantException e) {
            LOG.error(e);
        }
    }
}
