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

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        //设置编码，支持中文
        request.setCharacterEncoding("utf-8");
        //设置相应页面的格式
        response.setContentType("text/html;charset=utf-8");

        UserService userService = new UserService();
        //将请求参数以Map形式返回，将Map转换为Bean，封装
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        try{
            //用户登陆，并记录状态
            User user = userService.login(form);
            request.getSession().setAttribute("sessionUser", user);
            //重定向页面
            response.sendRedirect(request.getContextPath() + "/loginSuccessful.jsp");
        }catch (UserException e){
            //保留当前信息
            request.setAttribute("user", form);
            //显示错误信息
            request.setAttribute("message", e.getMessage());
            //保留信息，转发页面，重新登陆
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
