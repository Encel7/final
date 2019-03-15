package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.SouvenirService;
import by.epam.shop.service.implementation.SouvenirServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Search implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public Search() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR, Role.ANONYMOUS);
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String color = request.getParameter("color");
        SouvenirService souvenirService = new SouvenirServiceImplementation();
        CommandCommon common = new CommandCommon();
        try {
            List<Souvenir> search = souvenirService.searchByColor(color);
            if(search.size() > 0) {
                request.setAttribute("search", search);
            } else {
                request.setAttribute("search", null);
                request.setAttribute("fail", "Not found");
            }
            request.setAttribute("start", 0);
            request.setAttribute("index", null);
            request.setAttribute("command", "main_page");
            common.visitPage(request, response, "usermain.html");
        } catch (SQLException | IOException | ServletException | ConstantException e) {
            LOG.error(e);
        }
    }
}
