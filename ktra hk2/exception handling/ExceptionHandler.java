package utils;

import java.io.*;

public class ExceptionHandler {

    public static void handleAssetLoad(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println(" Không tìm thấy asset: " + filePath);
        }
    }

    public static void handleWriteFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println(" Lỗi ghi file: " + fileName);
        }
    }

    public static int handleReadScore(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            System.out.println(" Không đọc được file, trả về 0");
            return 0;
        }
    }

    public static void validateScore(int score) throws GameException {
        if (score < 0) {
            throw new GameException("Điểm không hợp lệ!");
        }
    }

    public static void logError(Exception e) {
        System.out.println(" Lỗi: " + e.getMessage());
    }
}