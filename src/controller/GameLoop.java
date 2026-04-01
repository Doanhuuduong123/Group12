package controller;

import model.Food;
import model.GameData;
import model.Snake;
import model.Wall;
import view.GamePanel;
import view.MainFrame;
import utils.SoundManager; // Import bộ quản lý âm thanh
import javax.sound.sampled.Clip; // Import thư viện âm thanh của Java

import java.awt.Point;
import java.util.ArrayList;

public class GameLoop extends Thread {
    private GamePanel gamePanel;
    private Snake snake;
    private Food food;
    private GameData gameData;
    private MainFrame mainFrame;
    private ArrayList<Integer> scores;
    private volatile boolean running = true;
    
    // Biến lưu trữ âm thanh
    private Clip bgMusic;
    private Clip eatSound;

    public GameLoop(GamePanel gp, Snake s, Food f, GameData gd, MainFrame mf, ArrayList<Integer> scores) {
        this.gamePanel = gp; this.snake = s; this.food = f; 
        this.gameData = gd; this.mainFrame = mf; this.scores = scores;
        
        // Nạp file âm thanh từ thư mục asset
        this.bgMusic = SoundManager.loadSound("bgm.wav");
        this.eatSound = SoundManager.loadSound("eat.wav");
    }

    public void stopGame() { 
        running = false; 
        SoundManager.stopSound(bgMusic); // Tắt nhạc nền khi thoát game
    }

    @Override
    public void run() {
        // Bật nhạc nền lặp vô hạn khi bắt đầu vòng lặp game
        SoundManager.loopSound(bgMusic);

        while (running && snake.isAlive()) {
            snake.move();
            checkCollisions();
            gamePanel.repaint();

            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                break;
            }
        }

        if (!snake.isAlive() && running) {
            SoundManager.stopSound(bgMusic); // Rắn chết -> Tắt nhạc nền ngay lập tức
            scores.add(gameData.getScore());
            mainFrame.showGameOver(gameData.getScore());
        }
    }

    private void checkCollisions() {
        Point head = snake.getBody().get(0);

        // 1. Đụng biên giới
        if (head.x < 0 || head.x >= 900 || head.y < 0 || head.y >= 600) {
            snake.setAlive(false);
            return;
        }

        // 2. Cắn vào thân
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.equals(snake.getBody().get(i))) {
                snake.setAlive(false);
                return;
            }
        }

        // 3. Đụng vật cản (Khối Block)
        if (gameData.getWalls() != null) {
            for (Wall w : gameData.getWalls()) {
                if (head.x == w.getX() && head.y == w.getY()) {
                    snake.setAlive(false);
                    return;
                }
            }
        }

        // 4. Ăn mồi
        if (head.x == food.getX() && head.y == food.getY()) {
            // Phát tiếng cắn táo "Rộp rộp"
            SoundManager.playSound(eatSound);

            int points = food.isSpecial() ? 20 : 10;
            gameData.setScore(gameData.getScore() + points);
            mainFrame.setScore(gameData.getScore());
            
            snake.grow();
            
            // LOGIC CHUẨN: Tường mọc trước, Táo mọc sau!
            gameData.spawnRandomWalls(1, 3, snake, food); // Tường mới mọc lên
            food.spawn(snake, gameData.getWalls());       // Táo lấy danh sách tường mới để né
        }
    }
}