package by.epam.shop.servlet.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActionFilter implements Filter {

    private static final Logger LOG = LogManager.getLogger("name");

    private static Map<String, String> actions = new HashMap<>();

    static {
        actions.put("/edit_user", "edit_user");
        actions.put("/add_to_order", "add_to_order");
        actions.put("/replenish_balance", "replenish_balance");
        actions.put("/registration", "registration");
        actions.put("/log_in", "log_in");
        actions.put("/log_out", "log_out");
        actions.put("/visit_log_in", "visit_log_in");
        actions.put("/userlist", "user_list");
        actions.put("/usermain", "main_page");
        actions.put("/profile", "profile");
        actions.put("/index", "main_page");
        actions.put("/orderlist", "order_list");
        actions.put("/checkout", "checkout");
        actions.put("/delete", "delete");
        actions.put("/translate", "translate");
        actions.put("/", "main_page");
        actions.put("/search", "search");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            String actionClass = actions.get(actionName);
            httpRequest.setAttribute("command", actionClass);
            chain.doFilter(request, response);
        } else {
            LOG.error("It is impossible to use HTTP filter");
        }
    }

    @Override
    public void destroy() {

    }
}
