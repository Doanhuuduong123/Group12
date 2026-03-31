

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.Snake;
import model.Food;
import model.GameData;
import view.MainFrame;
import view.GamePanel;
import view.ShowHighscorePanel;
import controller.GameLoop;
import controller.InputHandler;
import java.util.ArrayList;
import utils.ExceptionHandler;

public class Main {
    private static GameLoop gameLoop;
    private static InputHandler input;
    private static ArrayList<Integer> scores = new ArrayList<>();
    private static MainFrame mainFrame;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                ExceptionHandler.handleException(e, "Lỗi cài đặt giao diện");
            }

            try {
                // 1. Khởi tạo Model & View
                Snake snake = new Snake(450, 300, 25);
                Food food = new Food(600, 300, 25);
                GameData gameData = new GameData();
                mainFrame = new MainFrame();
                GamePanel gamePanel = new GamePanel(snake, food, gameData);
                
                // 2. Cấu hình Input
                input = new InputHandler(snake);
                gamePanel.addKeyListener(input);
                gamePanel.setFocusable(true);
                
                // 3. Xử lý sự kiện Menu
                mainFrame.getMenuPanel().addPlayListener(e -> {
                    startNewGame(mainFrame, snake, food, gameData, gamePanel);
                });
                
                mainFrame.getMenuPanel().addHighscoreListener(e -> showHighscore());
                mainFrame.getMenuPanel().addExitListener(e -> {
                    if (gameLoop != null) {
                        gameLoop.stopGame();
                    }
                    System.exit(0);
                });

                mainFrame.setVisible(true);
                
            } catch (Exception e) {
                ExceptionHandler.handleException(e, "Lỗi khởi tạo trò chơi");
                System.exit(1);
            }
        });
    }
    
    private static void startNewGame(MainFrame mf, Snake snake, Food food, 
                                      GameData gameData, GamePanel gamePanel) {
        // Reset game data
        snake.reset();
        food.spawn();
        gameData.setScore(0);
        mf.setScore(0); // Cập nhật nhãn điểm ngay lập tức
        gameData.setGameOver(false);
        
        // Hiển thị game
        mf.showGame(gamePanel);
        
        gamePanel.requestFocusInWindow();
        
        // Dừng game loop cũ nếu có
        if (gameLoop != null) {
            gameLoop.stopGame();
            gameLoop.interrupt(); // Đánh thức luồng khỏi sleep để dừng ngay lập tức
        }
        
        // Bắt đầu game loop mới
        gameLoop = new GameLoop(gamePanel, snake, food, gameData, mf, scores);
        gameLoop.start();
    }
    
    private static void showHighscore() {
        try {
            // Hiển thị bảng xếp hạng
            mainFrame.showHighscore(scores);

            // Xử lý nút quay lại
            ShowHighscorePanel panel = mainFrame.getHighscorePanel();
            if (panel != null) {
                // Xóa listener cũ để tránh lặp lại
                for (var listener : panel.getBtnBack().getActionListeners()) {
                    panel.getBtnBack().removeActionListener(listener);
                }
                // Thêm listener mới
                panel.getBtnBack().addActionListener(e -> {
                    mainFrame.showMenu();
                });
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e, "Lỗi hiển thị bảng xếp hạng");
        }
    }
}