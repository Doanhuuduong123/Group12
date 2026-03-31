package utils;

// 1. Thêm đầy đủ các dòng import này vào đầu file
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AssetManager {
    public static Image loadImage(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                // Sửa lỗi chính tả IOException
                throw new IOException("Không tìm thấy file: " + path);
            }
            return ImageIO.read(file);
        } catch (IOException e) {
            // JOptionPane giờ đã có import nên sẽ hết lỗi
            JOptionPane.showMessageDialog(null,
                "Lỗi nạp tài nguyên: " + e.getMessage(),
                "Lỗi hệ thống",
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}