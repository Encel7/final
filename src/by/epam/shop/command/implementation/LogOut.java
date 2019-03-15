package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LogOut implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public LogOut() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("User", null);
            request.getSession().setAttribute("Order", null);
            request.setAttribute("command", "visit_log_in");
            response.sendRedirect("visit_log_in.html");
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
