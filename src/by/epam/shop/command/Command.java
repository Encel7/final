package by.epam.shop.command;

import by.epam.shop.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface Command {

    List<Role> getRoles();

    void execute(HttpServletRequest request, HttpServletResponse response);
}
