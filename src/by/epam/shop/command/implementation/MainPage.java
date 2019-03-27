package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.command.common.CommandCommon;
import by.epam.shop.command.common.JSPPath;
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

public class MainPage implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public MainPage() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR, Role.ANONYMOUS);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("fail") != null) {
            request.setAttribute("fail", request.getSession().getAttribute("fail"));
            request.getSession().setAttribute("fail", null);
        }
        if (request.getSession().getAttribute("credit") != null) {
            request.setAttribute("credit", request.getSession().getAttribute("credit"));
            request.getSession().setAttribute("credit", null);
        }
        if (request.getSession().getAttribute("paid") != null) {
            request.setAttribute("paid", request.getSession().getAttribute("paid"));
            request.getSession().setAttribute("paid", null);
        }
        int onePageContent = 8;
        Double startIndex;
        if (request.getParameter("start") == null) {
            startIndex = 0.0;
        } else {
            startIndex = Double.valueOf(request.getParameter("start"));
        }
        //System.out.println("str ind" + startIndex);

        CommandCommon common = new CommandCommon();
        SouvenirService souvenirService = new SouvenirServiceImplementation();
        Integer index;
        if (request.getParameter("index") == null) {
            index = 0;
        } else {
            index = Integer.valueOf(request.getParameter("index"));
        }
        startIndex+=index;
        List<Souvenir> souvenirList;
        try {
            if (request.getAttribute("search") == null) {
                souvenirList = souvenirService.takeAllSouvenirs();
            } else {
                souvenirList = (List<Souvenir>) request.getAttribute("search");
                startIndex = 0.0;
            }
            if ((souvenirList.size() - index*onePageContent) < 8) {
                onePageContent = souvenirList.size() - index*onePageContent;
            }
            request.setAttribute("startIndex", startIndex*8);
            request.setAttribute("content", onePageContent);
            request.setAttribute("souvenir", souvenirList);
            request.setAttribute("size", souvenirList.size());
            common.visitPage(request, response, JSPPath.MAIN_USER_PAGE.getUrl());
        } catch (SQLException | ServletException | IOException | ConstantException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }
}
