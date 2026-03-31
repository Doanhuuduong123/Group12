package utils;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static Image loadImage(String fileName) {
        try {
            // Thử từ thư mục gốc dự án
            String[] paths = {
                "asset/" + fileName,
                "../asset/" + fileName
            };
            
            for (String filePath : paths) {
                File imageFile = new File(filePath);
                if (imageFile.exists()) {
                    BufferedImage img = ImageIO.read(imageFile);
                    if (img != null) {
                        System.out.println("✓ Load từ: " + filePath);
                        return img;
                    }
                }
            }
            
            System.err.println("File không tìm thấy: " + fileName);
        } catch (Exception e) {
            System.err.println("Lỗi load: " + e.getMessage());
        }
        return null;
    }
}