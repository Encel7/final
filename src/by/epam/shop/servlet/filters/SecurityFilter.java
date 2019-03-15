package by.epam.shop.servlet.filters;

import by.epam.shop.command.Command;
import by.epam.shop.command.factory.CommandFactory;
import by.epam.shop.entity.Role;
import by.epam.shop.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String cmd = (String) httpRequest.getAttribute("command");
        User user = (User) httpRequest.getSession().getAttribute("User");
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.createCommand(cmd);
        if (user != null) {
            if (command.getRoles().contains(user.getRole())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpRequest.getSession().setAttribute("fail", "You have no enough rights to visit this page");
                httpResponse.sendRedirect("usermain.html");
            }
        } else {
            if (command.getRoles().contains(Role.ANONYMOUS)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpRequest.getSession().setAttribute("fail", "Log in before visiting this page");
                httpResponse.sendRedirect("visit_log_in.html");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
