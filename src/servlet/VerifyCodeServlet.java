package servlet;

import util.VerifyCode;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerifyCodeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage bufferedImage = verifyCode.getImage();
        request.getSession().setAttribute("session_vcode", verifyCode.getText());
        VerifyCode.output(bufferedImage, response.getOutputStream());
    }
}
