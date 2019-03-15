package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.entity.Balance;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.BalanceService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.implementation.BalanceServiceImplementation;
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
import java.util.Objects;

public class EditUser implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public EditUser() {
        roles = Arrays.asList(Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        CommandCommon common = new CommandCommon();
        Integer id = Integer.valueOf(request.getParameter("id"));
        String role = request.getParameter("select");
        Double discount = Double.valueOf(request.getParameter("discount"));
        Double overdraft = Double.valueOf(request.getParameter("overdraft"));
        Double newBalance = Double.valueOf(request.getParameter("balance"));
        BalanceService balanceService = new BalanceServiceImplementation();
        UserService userService = new UserServiceImplementation();
        try {
            Balance balance = balanceService.searchByID(id);
            User user = balance.getUser();
            if (Objects.equals(role, "USER")) {
                user.setRole(Role.USER);
            } else {
                user.setRole(Role.ADMINISTRATOR);
            }
            user.setDiscount(discount/100.0);
            balance.setOverdraft(overdraft);
            balance.setCurrentBalance(newBalance);
            userService.setData(user);
            balanceService.setData(balance);
            request.setAttribute("start", request.getParameter("start"));
            request.setAttribute("index", null);
            request.setAttribute("command", "user_list");
            request.setAttribute("success", "User was edited");
            common.visitPage(request, response, "userlist.html");
        } catch (SQLException | IOException | ServletException | ConstantException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
