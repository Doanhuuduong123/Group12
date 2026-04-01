package utils;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtils {
    public static Image loadImage(String fileName) {
        try {
            return ImageIO.read(new File("asset/" + fileName));
        } catch (Exception e) {
            System.err.println("Lỗi nạp tài nguyên: Không tìm thấy " + fileName);
            return null;
        }
    }
}