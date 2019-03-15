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
import java.util.Locale;
import java.util.Objects;

public class Translate implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public Translate() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("select");
        Locale locale1;
        if (Objects.equals(locale, "English")) {
            locale1 = new Locale("en");
        } else {
            locale1 = new Locale("ru");
        }
        request.getSession().setAttribute("locale", locale1);
        try {
            response.sendRedirect("profile.html");
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
