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

public class RegisterServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        //设置编码格式，支持中文
        request.setCharacterEncoding("utf-8");
        //设置相应页面的格式
        response.setContentType("text/html;charset=utf-8");
        //实例化，若抛出异常，留至最后处理
        UserService userService = new UserService();

        //将请求参数以Map形式返回，将Map转换为Bean，封装
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        //创建Map对象存放异常类型
        Map<String, String> registerErrors = new HashMap<>();

        //判断注册用户名是否异常
        String username = form.getUsername();
        if(username == null || username.trim().isEmpty()){
            registerErrors.put("username", "用户名不能为空");
        }else if(username.length() < 5 || username.length() > 15){
            registerErrors.put("username", "用户名必须为5-15个字符");
        }

        //判断注册密码是否异常
        String password = form.getPassword();
        if(password == null || password.trim().isEmpty()){
            registerErrors.put("password", "密码不能为空");
        }else if(password.length() < 5 || password.length() > 15){
            registerErrors.put("password", "密码必须为5-15个字符");
        }

        //判断注册电话号码是否异常
        String phone = form.getPhone();
        if(phone == null || phone.trim().isEmpty()){
            registerErrors.put("phone", "电话号码不能为空");
        }

        //判断注册邮箱是否异常
        String email = form.getEmail();
        if(email == null || email.trim().isEmpty()){
            registerErrors.put("email", "邮箱不能为空");
        }

        /*
         * 判断验证码是否异常，
         * registerErrors.put()表示
         * 将错误提示放置在Map中中
         */
        String registerVerifyCode = form.getVerifyCode();
        //得到当前页面自动生成的验证码
        String sessionVerifyCode = (String)request.getSession().getAttribute("register_session_vcode");
        if (registerVerifyCode==null||registerVerifyCode.trim().isEmpty())
        {
            registerErrors.put("verifyCode","验证码不能为空");
        }else if (registerVerifyCode.length()!=4)
        {
            registerErrors.put("verifyCode","验证码长度为4位");
        }else if (!sessionVerifyCode.equalsIgnoreCase(registerVerifyCode))
        {
            registerErrors.put("verifyCode","验证码错误");
        }

        //如果抛出异常，就提示用户
        if(registerErrors.size() > 0){
            //设定属性
            request.setAttribute("errors", registerErrors);
            request.setAttribute("user", form);
            /*
             * 保留当前信息，将请求转发至register.jsp，
             * 进行重新注册
             */
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            //在捕获错误之后，需要return语句来防止继续执行注册操作
            return;
        }

        try{
            //注册
            userService.register(form);
            /*
             * 重定向至
             * registerSuccessful.jsp
             */
            response.sendRedirect("/registerSuccessful.jsp");

        }catch (UserException e){
            //将已填写的信息保留
            request.setAttribute("user", form);
            //设定UserService中抛出的异常信息
            request.setAttribute("message", e.getMessage());
            /*
             * 保留当前信息，将请求转发至register.jsp，
             * 重新注册
             */
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
