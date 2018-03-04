package servlet;

import util.VerifyCode;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoginVerifyCodeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //实例化对象
        VerifyCode verifyCode = new VerifyCode();
        //生成图片
        BufferedImage Image = verifyCode.getImage();
        //将图片的文本信息设为login_session_vcode
        request.getSession().setAttribute("login_session_vcode", verifyCode.getText());
        //打印图片
        VerifyCode.output(Image, response.getOutputStream());
    }
}
