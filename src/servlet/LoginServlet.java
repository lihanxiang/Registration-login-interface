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
import java.util.HashMap;
import java.util.Map;

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

        //异常信息用Map存放，以便查找
        Map<String, String> loginErrors = new HashMap<>();

        String loginVerifyCode = form.getVerifyCode();
        //得到当前页面自动生成的验证码
        String sessionVerifyCode = (String)request.getSession().getAttribute("login_session_vcode");
        if(loginVerifyCode == null || loginVerifyCode.trim().isEmpty()){
            loginErrors.put("verifyCode", "验证码不能为空");
        }else if(loginVerifyCode.length() != 4){
            loginErrors.put("verifyCode", "验证码长度为4位");
        }else if(!sessionVerifyCode.equals(loginVerifyCode)){
            loginErrors.put("verifyCode", "验证码错误");
        }

        //若存在异常
        if(loginErrors.size() > 0){
            //设定属性
            request.setAttribute("errors", loginErrors);
            request.setAttribute("user", form);
            /*
             * 保留当前信息，将请求转发至login.jsp，
             * 进行重新注册
             */
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            //需要return语句来防止将信息注册至数据库
            return;
        }

        try{
            //用户登陆，并记录状态
            User user = userService.login(form);
            request.getSession().setAttribute("sessionUser", user);
            //重定向页面
            response.sendRedirect("/loginSuccessful.jsp");
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
