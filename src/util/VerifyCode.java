package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCode {

    private int width=50;                               //图片缓冲区的宽
    private int height=15;                              //图片缓冲区的高
    private Random random=new Random();                 //随机数字
    private Color color=new Color(255,255,255); //白色背景
    private String text;                                //图片上的文本

    /*
     * 创建图片缓冲区
     */
    public BufferedImage getImage()
    {
        BufferedImage image = createImage();                    //创建缓冲区
        Graphics g = image.getGraphics();                       //得到绘制环境
        StringBuilder stringBuilder = new StringBuilder();      //验证码文本

        //验证码长度为4
        for (int i=0;i<4;i++)
        {
            String str=randomChar() + "";           //产生随机字符,将char类型转为String类型
            stringBuilder.append(str);              //将生成的随机字符添加至stringBuilder
            g.setColor(randomColor());              //产生随机颜色
            g.drawString(str, i*width/4,height);  //绘制文本
        }
        drawLine(g);                                //添加干扰线
        text=stringBuilder.toString();              //把生成字符串赋给文本，以便于验证
        return image;
    }

    //放回验证码图片中的文本
    public String getText()
    {
        return text;
    }

    //保存至输出流，打印图片
    public static void output(BufferedImage image, OutputStream out) throws IOException {
        //作为JPEG格式输出图片
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
        //选取随机数字，生成字符
        String numbers = "1234567890";
        int index=random.nextInt(numbers.length());
        return numbers.charAt(index);
    }

    //生成随机颜色
    private Color randomColor()
    {
        //以RGB形式表示颜色
        int red=random.nextInt(150);
        int green=random.nextInt(150);
        int blue=random.nextInt(150);
        return new Color(red,green,blue);
    }

    //画干扰线
    private void drawLine(Graphics g){
        for (int i = 0; i < 2; i++) {
            //设置线的颜色
            g.setColor(randomColor());
            //线的长度从(x1,y1)到(x2,y2)
            g.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }
    }
}