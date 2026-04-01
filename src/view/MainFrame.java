package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Cursor;

import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JLabel scoreLabel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private ShowHighscorePanel highscorePanel;
    private JPanel scorePanel;

    public MainFrame() {
        setTitle("🐍 GAME RẮN ĂN MỒI - ĐẢ VÀNG TỪ ĐỊA NGỤC 🐍");
        setLayout(new BorderLayout());
        
        // Tạo panel hiển thị điểm số ở phía trên (ẩn khi vào menu)
        scorePanel = new JPanel();
        scoreLabel = new JLabel("📍 ĐIỂM SỐ: 0");
        scoreLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        scorePanel.add(scoreLabel);
        scorePanel.setBackground(new java.awt.Color(76, 175, 80));
        scoreLabel.setForeground(java.awt.Color.WHITE);
        
        // Tạo MenuPanel
        menuPanel = new MenuPanel();
        
        // Thêm MenuPanel vào frame
        add(menuPanel, BorderLayout.CENTER);
        
        pack();
        setSize(950, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // Hiển thị menu
    public void showMenu() {
        remove(scorePanel);
        if (gamePanel != null) {
            remove(gamePanel);
        }
        if (highscorePanel != null) {
            remove(highscorePanel);
        }
        add(menuPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    // Hiển thị game
    public void showGame(GamePanel game) {
        this.gamePanel = game;
        remove(menuPanel);
        if (highscorePanel != null) {
            remove(highscorePanel);
        }
        add(scorePanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    // Hiển thị bảng xếp hạng
    public void showHighscore(ArrayList<Integer> scores) {
        if (gamePanel != null) {
            remove(gamePanel);
            remove(scorePanel);
        }
        remove(menuPanel);
        
        highscorePanel = new ShowHighscorePanel(scores);
        add(highscorePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    // Hiển thị Game Over dialog
    public void showGameOver(int score) {
        JDialog gameOverDialog = new JDialog(this, "💀 KẾT THÚC TRẬN ĐẤU 💀", true);
        gameOverDialog.setSize(500, 320);
        gameOverDialog.setLocationRelativeTo(this);
        gameOverDialog.setLayout(new BorderLayout(20, 20));
        gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // Panel chính với gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 200, 200),
                        0, getHeight(), new Color(255, 150, 150));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        
        // Tiêu đề Game Over
        JLabel titleLabel = new JLabel("💀 GAME OVER! 💀");
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 36));
        titleLabel.setForeground(new Color(200, 30, 30));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        
        mainPanel.add(Box.createVerticalStrut(20));
        
        // Thông báo
        JLabel messageLabel = new JLabel("Bạn đã chết vì chạm vào bẫy!");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        messageLabel.setForeground(new Color(100, 0, 0));
        messageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(messageLabel);
        
        mainPanel.add(Box.createVerticalStrut(15));
        
        // Điểm số hiển thị
        JLabel scoreLabelText = new JLabel("📊 ĐIỂM SỐ CUỐI CÙNG: " + score);
        scoreLabelText.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabelText.setForeground(new Color(0, 100, 0));
        scoreLabelText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(scoreLabelText);
        
        // Đánh giá
        JLabel ratingLabel = new JLabel();
        String rating;
        if (score >= 100) rating = "⭐⭐⭐⭐⭐ SIÊU SAO!";
        else if (score >= 80) rating = "⭐⭐⭐⭐ VÔ CÙNG TỐT!";
        else if (score >= 60) rating = "⭐⭐⭐ TỐT!";
        else if (score >= 40) rating = "⭐⭐ TRUNG BÌNH";
        else if (score > 0) rating = "⭐ HÃY CỐ GẮNG LẠI!";
        else rating = "Bạn cần luyện tập thêm!";
        
        ratingLabel.setText(rating);
        ratingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ratingLabel.setForeground(new Color(255, 140, 0));
        ratingLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(ratingLabel);
        
        gameOverDialog.add(mainPanel, BorderLayout.CENTER);
        
        // Panel nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(200, 100, 100));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton okButton = new JButton("🔄 QUAY VỀ MENU");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(new Color(200, 50, 50));
        okButton.setPreferredSize(new Dimension(200, 45));
        okButton.setFocusPainted(false);
        okButton.setBorderPainted(false);
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        okButton.addActionListener(e -> gameOverDialog.dispose());
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                okButton.setBackground(new Color(220, 80, 80));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                okButton.setBackground(new Color(200, 50, 50));
            }
        });
        
        buttonPanel.add(okButton);
        gameOverDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        gameOverDialog.setVisible(true);
    }
    
    // Cập nhật điểm số
    public void setScore(int score) {
        scoreLabel.setText("� ĐIỂM SỐ: " + score);
        scoreLabel.setText("🏆 ĐIỂM SỐ: " + score);
    }
    
    // Getter cho MenuPanel
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
    
    // Getter cho HighscorePanel
    public ShowHighscorePanel getHighscorePanel() {
        return highscorePanel;
    }
}