package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCode {

    private int width=60;//图片缓冲区的宽
    private int height=15;//图片缓冲区的高
    private Random random=new Random();//随机数字
    private Color color=new Color(255,255,255);//白色背景
    private String text;//图片上的文本

    /*
     * 创建图片缓冲区
     */
    public BufferedImage getImage()
    {
        BufferedImage image=createImage();//1.调用创建图片缓冲区方法
        Graphics g=image.getGraphics();//3.得到绘制环境
        StringBuilder sb=new StringBuilder();//用来装载生成的验证码文本
        /*
        循环四次
        每次生成一个字符
         */
        for (int i=0;i<4;i++)
        {
            String str=randomChar()+"";//调用产生随机字符方法，随机生成一个字符
            sb.append(str);//将生成的随机字符加到sb后面
            g.setColor(randomColor());//调用产生随机颜色方法
            g.drawString(str, i*width/5,height);//在图片中绘制文本
        }
        drawLine(g);
        text=sb.toString();//把生成字符串赋给文本
        return image;
    }

    //放回验证码图片中的文本
    public String getText()
    {
        return text;
    }

    //保存至输出流，打印图片
    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image,"JPEG",out);
    }

    //创造图片缓冲区
    private BufferedImage createImage()
    {
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        g.setColor(color);
        g.fillRect(0,0,width,height);
        return image;
    }

    //生成随机字符
    private char randomChar()
    {
        String numbers = "1234567890";
        int index=random.nextInt(numbers.length());
        return numbers.charAt(index);
    }

    //生成随机颜色
    private Color randomColor()
    {
        int red=random.nextInt(150);
        int green=random.nextInt(150);
        int blue=random.nextInt(150);
        return new Color(red,green,blue);
    }

    private void drawLine(Graphics g){
        for (int i = 0; i < 2; i++) {
            g.setColor(randomColor());
            g.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }
    }
}
