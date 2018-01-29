package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class VerifyCode {
    private int width = 50;
    private int height = 35;
    private Random random = new Random();
    private String[] font = {"微软雅黑","宋体","黑体"};
    private Color color = new Color(255,255,255);
    private String code = "123456789zxcvbnmasdfghjklqwertyuiop";
    private String text;

    public BufferedImage getImage(){
        BufferedImage image = createImage();
        Graphics graphics = image.getGraphics();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            String string = randomChar() + "";
            stringBuilder.append(string);
            graphics.setFont(randomFont());
            graphics.setColor(randomColor());
            graphics.drawString(string, i*width/5, height-5);
        }
        this.text = stringBuilder.toString();
        drawLine(image);
        return image;
    }

    public String getText(){
        return this.text;
    }

    public static void output(BufferedImage image, OutputStream out) throws IOException{
        ImageIO.write(image, "JPEG", out);
    }

    private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.getColor();
        graphics.setColor(color);
        graphics.fillRect(0,0,width,height);
        return image;
    }

    public char randomChar(){
        int index = random.nextInt(code.length());
        char c = code.charAt(index);
        return c;
    }

    public Font randomFont(){
        int index = random.nextInt(font.length);
        int style = random.nextInt(4);
        int size = random.nextInt(4) + 20;
        return new Font(font[index],style,size);
    }

    public Color randomColor(){
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    public void drawLine(BufferedImage image){
        Graphics graphics = image.getGraphics();
        for (int i = 0; i < 3; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            graphics.setColor(Color.BLUE);
            graphics.drawLine(x1, y1, x2, y2);
        }
    }
}
