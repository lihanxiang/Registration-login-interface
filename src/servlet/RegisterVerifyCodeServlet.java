package servlet;

import util.VerifyCode;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RegisterVerifyCodeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException{
        //实例化对象
        VerifyCode verifyCode = new VerifyCode();
        //生成图片
        BufferedImage Image = verifyCode.getImage();
        //将图片中的文本信息设置为register_session_vcode
        request.getSession().setAttribute("register_session_vcode", verifyCode.getText());
        //打印图片
        VerifyCode.output(Image, response.getOutputStream());
    }
}
