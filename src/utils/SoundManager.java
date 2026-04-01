package utils;

import javax.sound.sampled.*;
import java.io.File;
import javax.swing.JOptionPane;

public class SoundManager {
    
    public static Clip loadSound(String fileName) {
        try {
            File soundFile = new File("asset/" + fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (Exception e) {
            System.err.println("Lỗi nạp âm thanh: " + fileName + " (Lưu ý: Chỉ hỗ trợ file .wav)");
            // Changed to JOptionPane for user-friendly error message
            JOptionPane.showMessageDialog(null,
                "Lỗi nạp âm thanh: " + fileName + " (Lưu ý: Chỉ hỗ trợ file .wav)\nChi tiết: " + e.getMessage(),
                "Lỗi âm thanh",
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void playSound(Clip clip) {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public static void loopSound(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void stopSound(Clip clip) {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}