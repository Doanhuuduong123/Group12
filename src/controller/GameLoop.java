package controller;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Snake;
import model.Food;
import model.GameData;
import view.MainFrame;
import java.util.ArrayList;

public class GameLoop extends Thread {
    private boolean running;
    private boolean gameOverTriggered = false;
    private final int DELAY = 150; // Tốc độ ban đầu
    private int currentDelay;
    private JPanel gamePanel;
    private CollisionController collisionController;
    private Snake snake;
    private Food food;
    private GameData gameData;
    private MainFrame mainFrame;
    private ArrayList<Integer> scores;

    public GameLoop(JPanel panel, Snake snake, Food food, GameData gameData, MainFrame mainFrame, ArrayList<Integer> scores) {
        this.gamePanel = panel;
        this.snake = snake;
        this.food = food;
        this.gameData = gameData;
        this.mainFrame = mainFrame;
        this.running = true;
        this.currentDelay = DELAY;
        this.collisionController = new CollisionController();
        this.scores = scores;
    }

    @Override
    public void run() {
        try {
            while (running) {
                update();
                checkCollisions();
                gamePanel.repaint();
                Thread.sleep(currentDelay);
            }

            // Chỉ xử lý Game Over nếu luồng dừng do va chạm (không phải bị stop thủ công)
            if (gameOverTriggered && !running) {
                if (gameData != null && scores != null) {
                    scores.add(gameData.getScore());
                }
                
                Thread.sleep(1000); // Chờ 1 giây để người chơi nhìn bảng điểm
                
                if (mainFrame != null) {
                    SwingUtilities.invokeLater(() -> mainFrame.showMenu());
                }
            }
        } catch (InterruptedException e) {
            // Luồng bị ngắt (interrupt) bởi startNewGame, thoát ngay lập tức
        }
    }

    private void update() {
        // Cập nhật vị trí rắn
        if (snake != null) {
            snake.move();
        }
    }

    private void checkCollisions() {
        // Kiểm tra rắn ăn food
        if (collisionController.checkFoodCollision(snake, food)) {
            System.out.println("✓ Ăn được food! Score tăng 10 điểm");
            if (snake != null) {
                snake.grow(); // Rắn phát triển
            }
            if (gameData != null) {
                gameData.increaseScore(10); // Tăng score 10 điểm
                System.out.println("✓ Score hiện tại: " + gameData.getScore());
                
                // Cập nhật score lên MainFrame
                if (mainFrame != null) {
                    SwingUtilities.invokeLater(() -> mainFrame.setScore(gameData.getScore()));
                }
                
                // Tăng độ khó: Mỗi 50 điểm (5 lần ăn) thì giảm delay (tăng tốc)
                if (gameData.getScore() > 0 && gameData.getScore() % 50 == 0) {
                    currentDelay = Math.max(60, currentDelay - 10);
                }
            }
            if (food != null) {
                food.spawn(); // Tạo mồi mới
            }
        }
        
        // Kiểm tra rắn chạm vào chính mình (game over)
        if (collisionController.checkSelfCollision(snake)) {
            System.out.println("✗ Game Over! Rắn chạm vào thân mình");
            running = false; // Kết thúc game 
            gameOverTriggered = true;
            if (gameData != null) {
                gameData.setGameOver(true);
                // Hiển thị Game Over dialog
                if (mainFrame != null) {
                    SwingUtilities.invokeLater(() -> mainFrame.showGameOver(gameData.getScore()));
                }
            }
        }
        
        // Kiểm tra rắn chạm vật cản
        if (collisionController.checkObstacleCollision(snake, gameData)) {
            System.out.println("✗ Game Over! Chạm vật cản");
            running = false; // Kết thúc game 
            gameOverTriggered = true;
            if (gameData != null) {
                gameData.setGameOver(true);
                // Hiển thị Game Over dialog
                if (mainFrame != null) {
                    SwingUtilities.invokeLater(() -> mainFrame.showGameOver(gameData.getScore()));
                }
            }
        }
    }
    
    public void stopGame() {
        this.running = false;
    }
}