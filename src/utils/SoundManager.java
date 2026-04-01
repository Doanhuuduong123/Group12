package utils;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    
    // Hàm nạp file âm thanh từ thư mục asset
    public static Clip loadSound(String fileName) {
        try {
            File soundFile = new File("asset/" + fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (Exception e) {
            System.err.println("Lỗi nạp âm thanh: " + fileName + " (Lưu ý: Chỉ hỗ trợ file .wav)");
            return null;
        }
    }

   // Hàm phát âm thanh 1 lần (Đã được nâng cấp chống kẹt tiếng)
    public static void playSound(Clip clip) {
        if (clip != null) {
            clip.stop();              // BẮT BUỘC: Dừng âm thanh cũ nếu nó đang phát dở
            clip.setFramePosition(0); // Tua lại từ đầu
            clip.start();             // Phát lại
        }
    }

    // Hàm phát lặp đi lặp lại (Dùng cho nhạc nền)
    public static void loopSound(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Lặp vô hạn
        }
    }

    // Hàm dừng âm thanh (Dùng khi Game Over)
    public static void stopSound(Clip clip) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}