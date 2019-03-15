package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.entity.Balance;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.BalanceService;
import by.epam.shop.service.implementation.BalanceServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ReplenishBalance implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public ReplenishBalance() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Double sum = Double.valueOf(request.getParameter("sum"));
        BalanceService balanceService = new BalanceServiceImplementation();
        try {
            Balance balance = balanceService.
                    searchByOwner(((User)request.getSession().getAttribute("User")).
                            getIdentity());
            balance.setCurrentBalance(balance.getCurrentBalance() + sum);
            balanceService.setData(balance);
            request.getSession().setAttribute("success", "Balance was replenished");
            response.sendRedirect("profile.html");
        } catch (SQLException | IOException | ConstantException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
