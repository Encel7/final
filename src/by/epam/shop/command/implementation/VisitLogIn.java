package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.command.common.JSPPath;
import by.epam.shop.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class VisitLogIn implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public VisitLogIn() {
        roles = Arrays.asList(Role.ANONYMOUS);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("fail") != null) {
            request.setAttribute("fail", request.getSession().getAttribute("fail"));
            request.getSession().setAttribute("fail", null);
        }
        try {
            CommandCommon common = new CommandCommon();
            common.visitPage(request, response, JSPPath.INDEX.getUrl());
        } catch (ServletException | IOException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
