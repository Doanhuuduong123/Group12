package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private ShowHighscorePanel highscorePanel;
    
    private JPanel scorePanel;
    private JLabel scoreLabel;

    public MainFrame() {
        setTitle("SNAKE GAME - LEGENDARY");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 750);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        setupScorePanel();

        menuPanel = new MenuPanel();
        mainContainer.add(menuPanel, "MENU");

        add(mainContainer, BorderLayout.CENTER);
    }

    private void setupScorePanel() {
        scorePanel = new JPanel();
        scorePanel.setBackground(new Color(45, 45, 45)); 
        scoreLabel = new JLabel("SCORE: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scorePanel.add(scoreLabel);
    }

    public void showMenu() {
        getContentPane().remove(scorePanel); 
        cardLayout.show(mainContainer, "MENU");
        revalidate();
        repaint();
    }

    public void showGame(GamePanel game) {
        this.gamePanel = game;
        mainContainer.add(gamePanel, "GAME");
        
        add(scorePanel, BorderLayout.NORTH); 
        cardLayout.show(mainContainer, "GAME");
        
        gamePanel.requestFocusInWindow(); 
        revalidate();
        repaint();
    }

    public void showHighscore(ArrayList<Integer> scores) {
        getContentPane().remove(scorePanel);
        highscorePanel = new ShowHighscorePanel(scores);
        mainContainer.add(highscorePanel, "HIGHSCORE");
        cardLayout.show(mainContainer, "HIGHSCORE");
        revalidate();
        repaint();
    }

    public void setScore(int score) {
        scoreLabel.setText("SCORE: " + score);
    }

    public void showGameOver(int score) {
        GameOverDialog dialog = new GameOverDialog(this, score);
        dialog.setVisible(true); 
    }

    public MenuPanel getMenuPanel() { return menuPanel; }
    public ShowHighscorePanel getHighscorePanel() { return highscorePanel; }

    private class GameOverDialog extends JDialog {
        public GameOverDialog(Frame owner, int score) {
            super(owner, "GAME OVER", true);
            setSize(400, 300);
            setLocationRelativeTo(owner);
            setLayout(new BorderLayout());

            JPanel content = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(new GradientPaint(0, 0, new Color(80, 20, 20), 0, getHeight(), new Color(40, 0, 0)));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel lblOver = new JLabel("GAME OVER");
            lblOver.setFont(new Font("Arial Black", Font.BOLD, 28));
            lblOver.setForeground(Color.WHITE);
            lblOver.setAlignmentX(CENTER_ALIGNMENT);

            JLabel lblScore = new JLabel("SCORE: " + score);
            lblScore.setFont(new Font("Arial", Font.BOLD, 22));
            lblScore.setForeground(Color.YELLOW);
            lblScore.setAlignmentX(CENTER_ALIGNMENT);

            JButton btnBack = new JButton("BACK TO MENU");
            btnBack.setAlignmentX(CENTER_ALIGNMENT);
            btnBack.addActionListener(e -> {
                dispose();
                showMenu();
            });

            content.add(lblOver);
            content.add(Box.createVerticalStrut(20));
            content.add(lblScore);
            content.add(Box.createVerticalStrut(30));
            content.add(btnBack);

            add(content);
        }
    }
}