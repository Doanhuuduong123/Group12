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

import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JLabel scoreLabel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private ShowHighscorePanel highscorePanel;
    private JPanel scorePanel;

    public MainFrame() {
        setTitle("🐍 Game Rắn Săn Mồi 🐍");
        setLayout(new BorderLayout());
        
        // Tạo panel hiển thị điểm số ở phía trên (ẩn khi vào menu)
        scorePanel = new JPanel();
        scoreLabel = new JLabel("Điểm: 0");
        scoreLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        scorePanel.add(scoreLabel);
        scorePanel.setBackground(new java.awt.Color(34, 139, 34));
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
        JDialog gameOverDialog = new JDialog(this, "🎮 GAME OVER 🎮", true);
        gameOverDialog.setSize(400, 250);
        gameOverDialog.setLocationRelativeTo(this);
        gameOverDialog.setLayout(new BorderLayout(20, 20));
        gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Tiêu đề Game Over
        JLabel titleLabel = new JLabel("🎮 GAME OVER! 🎮");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(220, 20, 60));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        
        mainPanel.add(Box.createVerticalStrut(20));
        
        // Điểm số
        JLabel scoreLabelText = new JLabel("Điểm số: " + score + " điểm");
        scoreLabelText.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabelText.setForeground(new Color(0, 100, 0));
        scoreLabelText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(scoreLabelText);
        
        gameOverDialog.add(mainPanel, BorderLayout.CENTER);
        
        // Nút OK
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 14));
        okButton.setPreferredSize(new Dimension(100, 40));
        okButton.addActionListener(e -> gameOverDialog.dispose());
        buttonPanel.add(okButton);
        gameOverDialog.add(buttonPanel, BorderLayout.SOUTH);
        
        gameOverDialog.setVisible(true);
    }
    
    // Cập nhật điểm số
    public void setScore(int score) {
        scoreLabel.setText("💰 Điểm: " + score);
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