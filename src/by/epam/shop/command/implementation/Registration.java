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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Registration implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public Registration() {
        roles = Arrays.asList(Role.ANONYMOUS);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserServiceImplementation();
        BalanceService balanceService = new BalanceServiceImplementation();
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        try {
            if (Objects.equals(password, repeatPassword)) {
                int result = userService.addUser(new User(Role.USER,
                        firstName,
                        lastName,
                        login,
                        password,
                        0));
                if (result != -1) {
                    User user = userService.searchByPersonal(login, password).get(0);
                    balanceService.addBalance(new Balance(user));
                    response.sendRedirect(JSPPath.INDEX.getUrl());
                } else {
                    request.setAttribute("fname", firstName);
                    request.setAttribute("lname", lastName);
                    request.setAttribute("fail", "This Login already exists");
                    CommandCommon common = new CommandCommon();
                    common.visitPage(request, response, JSPPath.REGISTRATION.getUrl());
                }
            } else {
                request.setAttribute("fname", firstName);
                request.setAttribute("lname", lastName);
                request.setAttribute("login", login);
                request.setAttribute("fail", "Incorrect Repeated Password and Password");
                CommandCommon common = new CommandCommon();
                common.visitPage(request, response, JSPPath.REGISTRATION.getUrl());
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
