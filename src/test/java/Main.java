import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class Main {
    public static void main(String[] args) {
        var time = "12:05:15";
        var bimg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        var g2d = bimg.createGraphics();
        var font = new Font("Times New Roman", Font.PLAIN, 20);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(time);
        int height = fm.getHeight();
        g2d.dispose();

        bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = bimg.createGraphics();
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.WHITE);
        g2d.drawString(time, 0, fm.getAscent());
        g2d.dispose();
        try {
            ImageIO.write(bimg, "png", new File("test.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
