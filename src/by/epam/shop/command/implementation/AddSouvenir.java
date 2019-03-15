package by.epam.shop.command.implementation;

import by.epam.shop.command.Command;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.Souvenir;
import by.epam.shop.entity.User;
import by.epam.shop.exceptions.ConstantException;
import by.epam.shop.service.SouvenirService;
import by.epam.shop.service.UserService;
import by.epam.shop.service.implementation.SouvenirServiceImplementation;
import by.epam.shop.service.implementation.UserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AddSouvenir implements Command {

    private static final Logger LOG = LogManager.getLogger("name");

    List<Role> roles;

    public AddSouvenir() {
        roles = Arrays.asList(Role.USER, Role.ADMINISTRATOR);
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        SouvenirService souvenirService = new SouvenirServiceImplementation();

        Integer size = Integer.valueOf(request.getParameter("size"));
        String color = request.getParameter("color");
        Integer price = Integer.valueOf(request.getParameter("price"));
        try {
            Part filePart = request.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();
            byte[] bytes = new byte[fileContent.available()];
            fileContent.read(bytes);
            User user = (User) request.getSession().getAttribute("User");
            File file = new File("" + fileName);
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            souvenirService.addSouvenir(new Souvenir(size,
                    color,
                    "./userimg/" + fileName,
                    price,
                    user));
            if (user.getDiscount() < 0.5) {
                UserService userService = new UserServiceImplementation();
                double newDiscount = new BigDecimal(user.getDiscount() + 0.05).setScale(3, RoundingMode.FLOOR).doubleValue();
                user.setDiscount(newDiscount);
                request.getSession().setAttribute("User", user);
                userService.setData(user);
            }
            request.getSession().setAttribute("success", "Souvenir was added");
            response.sendRedirect("profile.html");
        } catch (IOException | ServletException | SQLException | ConstantException e) {
            LOG.error(e);
        }


    }
}
