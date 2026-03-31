public class AssetManager {
    public static Image loadImage(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                              throw new IOException("Không tìm thấy file ảnh tại: " + path);
            }
            return ImageIO.read(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Lỗi nạp tài nguyên: " + e.getMessage(), 
                "Lỗi hệ thống", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
