import com.filkond.megaclock.utils.Translator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.filkond.megaclock.utils.FontUtils.getImage;

public class Main {
    public static void main(String[] args) {
        printTimes();
    }

    private static void createPng() {
        var time = "12:05:15";
        var font = new Font("Times New Roman", Font.PLAIN, 45);
        var bimg = getImage(time, font);
        try {
            ImageIO.write(bimg, "png", new File("test.png"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void printTimes() {
        var zdt = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        System.out.println(Translator.formatTime(zdt));
    }
}
