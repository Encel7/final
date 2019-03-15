package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.command.common.JSPPath;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersList implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public UsersList() {
        roles = Arrays.asList(Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int onePageContent = 8;
        Double startIndex;
        if (request.getParameter("start") == null) {
            startIndex = 0.0;
        } else {
            startIndex = Double.valueOf(request.getParameter("start"));
        }
        Integer index;
        if (request.getParameter("index") == null) {
            index = 0;
        } else {
            index = Integer.valueOf(request.getParameter("index"));
        }
        startIndex+=index;
        UserService userService = new UserServiceImplementation();
        BalanceService balanceService = new BalanceServiceImplementation();
        CommandCommon commandCommon = new CommandCommon();
        try {

            List<User> users = userService.takeAllUsers();
            request.setAttribute("users", users);
            List<Balance> balances = new ArrayList<>();
            for (User user : users) {
               balances.add(balanceService.searchByOwner(user.getIdentity()));
            }

            if ((balances.size() - index*onePageContent) < 8) {
                onePageContent = balances.size() - index*onePageContent;
            }

            request.setAttribute("startIndex", startIndex*8);
            request.setAttribute("content", onePageContent);
            request.setAttribute("size", balances.size());
            request.setAttribute("balances", balances);
            commandCommon.visitPage(request, response, JSPPath.USER_LIST.getUrl());
        } catch (SQLException | ServletException | IOException | ConstantException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
