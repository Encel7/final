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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Checkout implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public Checkout() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderServiceImplementation();
        BalanceService balanceService = new BalanceServiceImplementation();
        try {
            Integer sum = orderService.calculateTotalPrice(((Order) request.
                    getSession().getAttribute("Order")).getIdentity());
            User user = (User) request.getSession().getAttribute("User");
            Balance balance = balanceService.searchByOwner(user.getIdentity());
            if ((balance.getCurrentBalance() + balance.getOverdraft()
                    - sum * (1 - user.getDiscount())) >= 0) {
                balance.setCurrentBalance(balance.getCurrentBalance()
                        - sum * (1 - user.getDiscount()));
                Order order = orderService.searchByID(((Order) request.
                        getSession().getAttribute("Order")).getIdentity());
                order.setPaid(true);
                order.setDate(new Date(Calendar.getInstance().getTime().getTime()));
                order.setTotalPrice(sum * (1 - user.getDiscount()));
                orderService.setData(order);
                balanceService.setData(balance);
                if (balance.getCurrentBalance() < 0) {
                    request.getSession().setAttribute("credit", "Purchase is taken on credit");
                } else {
                    request.getSession().setAttribute("paid", "Purchase paid");
                }
                request.getSession().setAttribute("Order", null);
                response.sendRedirect("usermain.html");

            } else {
                request.setAttribute("fail", "Not enough money");
                request.setAttribute("command", "order_list");
                CommandCommon common = new CommandCommon();
                common.visitPage(request, response, "orderlist.html");
            }
        } catch (SQLException | ServletException | IOException | ConstantException e) {
            LOG.error(e);
        }
    }
}