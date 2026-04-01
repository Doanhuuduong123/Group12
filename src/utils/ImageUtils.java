package utils;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageUtils {
    public static Image loadImage(String fileName) {
        try {
            return ImageIO.read(new File("asset/" + fileName));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Không tìm thấy ảnh: " + fileName + "\nVui lòng kiểm tra thư mục asset/.", 
                "Lỗi tải tài nguyên", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}