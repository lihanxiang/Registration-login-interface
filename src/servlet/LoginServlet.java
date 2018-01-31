package servlet;

import cn.itcast.commons.CommonUtils;
import domain.User;
import service.UserException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        UserService userService = new UserService();
        try{
            User user = userService.login(form);
            request.getSession().setAttribute("sessionUser", user);
            response.sendRedirect(request.getContextPath() + "");
        }catch(UserException e){

        }
    }
}
