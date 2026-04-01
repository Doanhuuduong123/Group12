

import javax.swing.*;
import model.Snake;
import model.Food;
import model.GameData;
import view.MainFrame;
import view.GamePanel;
import controller.GameLoop;
import controller.InputHandler;
import java.util.ArrayList;
import utils.ExceptionHandler; // Import ExceptionHandler

public class Main {
    private static MainFrame mainFrame;
    private static GameLoop gameLoop;
    private static ArrayList<Integer> highscores = new ArrayList<>();
    private static final String HIGHSCORE_FILE = "highscores.txt"; // Define highscore file name

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                highscores = ExceptionHandler.readHighscores(HIGHSCORE_FILE); // Load highscores at startup
                mainFrame = new MainFrame();
                setupMenuActions();
                mainFrame.setVisible(true);
            } catch (Exception e) {
                System.err.println("Lỗi khởi tạo hệ thống: " + e.getMessage());
            }
        });
    }

    private static void setupMenuActions() {
        mainFrame.getMenuPanel().addPlayListener(e -> startNewGame());

        mainFrame.getMenuPanel().addHighscoreListener(e -> {
            mainFrame.showHighscore(highscores);
            mainFrame.getHighscorePanel().getBtnBack().addActionListener(backEvt -> {
                mainFrame.showMenu();
            });
        });

        mainFrame.getMenuPanel().addExitListener(e -> System.exit(0));
    }

    private static void startNewGame() {
        if (gameLoop != null) {
            gameLoop.stopGame();
        }

        Snake snake = new Snake(300, 300, 30); 
        Food food = new Food(450, 300, 30);
        GameData gameData = new GameData();

        GamePanel gamePanel = new GamePanel(snake, food, gameData);

        InputHandler input = new InputHandler(snake);
        gamePanel.addKeyListener(input);
        gamePanel.setFocusable(true);

        mainFrame.showGame(gamePanel);

        gameLoop = new GameLoop(gamePanel, snake, food, gameData, mainFrame, highscores);
        gameLoop.start();
    }
}