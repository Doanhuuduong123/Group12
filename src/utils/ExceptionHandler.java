package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class ExceptionHandler {

    public static void handleAssetLoad(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println(" [ERROR] Khong tim thay asset: " + filePath);
        }
    }

    public static void handleWriteFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println(" [ERROR] Loi ghi file: " + fileName);
        }
    }

    public static int handleReadScore(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            System.out.println(" [WARN] Khong doc duoc file, tra ve 0");
            return 0;
        }
    }

    public static ArrayList<Integer> readHighscores(String fileName) {
        ArrayList<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    scores.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println(" [WARN] Bo qua dong khong hop le trong file highscore: " + line);
                }
            }
            Collections.sort(scores, Collections.reverseOrder());
        } catch (FileNotFoundException e) {
            System.out.println(" [INFO] File highscore chua ton tai: " + fileName);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Lỗi đọc file kỷ lục: " + e.getMessage(),
                "Lỗi hệ thống",
                JOptionPane.ERROR_MESSAGE);
            logError(e);
        }
        return scores;
    }

    public static void writeHighscores(String fileName, ArrayList<Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            ArrayList<Integer> sortedScores = new ArrayList<>(scores);
            Collections.sort(sortedScores, Collections.reverseOrder());
            for (Integer score : sortedScores) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Lỗi ghi file kỷ lục: " + e.getMessage(),
                "Lỗi hệ thống",
                JOptionPane.ERROR_MESSAGE);
            logError(e);
        }
    }

    public static void validateScore(int score) throws GameException {
        if (score < 0) {
            throw new GameException("Diem khong hop le!");
        }
    }

    public static void logError(Exception e) {
        System.out.println(" [ERROR]: " + e.getMessage());
    }

    public static void handleException(Exception e, String message) {
        System.err.println(" [EXCEPTION]: " + message);
        System.err.println(" Chi tiet: " + e.getMessage());
        e.printStackTrace();
    }
}