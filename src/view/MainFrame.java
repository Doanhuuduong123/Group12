package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Game Rắn Săn Mồi");
        setLayout(new BorderLayout());

        GamePanel gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        JPanel scorePanel = new JPanel();
        scorePanel.add(new JLabel("Điểm: 0"));
        add(scorePanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
