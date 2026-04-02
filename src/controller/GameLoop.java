package controller;

import model.Food;
import model.GameData;
import model.Snake;
import model.Wall;
import view.GamePanel;
import view.MainFrame;
import utils.SoundManager;
import javax.sound.sampled.Clip;
import utils.ExceptionHandler;

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
    
    private Clip bgMusic;
    private Clip eatSound;
    private static final String HIGHSCORE_FILE = "highscores.txt";

    public GameLoop(GamePanel gp, Snake s, Food f, GameData gd, MainFrame mf, ArrayList<Integer> scores) {
        this.gamePanel = gp; this.snake = s; this.food = f; 
        this.gameData = gd; this.mainFrame = mf; this.scores = scores;
        
        this.bgMusic = SoundManager.loadSound("bgm.wav");
        this.eatSound = SoundManager.loadSound("eat.wav");
    }

    public void stopGame() { 
        running = false; 
        SoundManager.stopSound(bgMusic);
    }

    @Override
    public void run() {
        SoundManager.loopSound(bgMusic);

        while (running && snake.isAlive()) {
            snake.move();
            checkCollisions();
            gamePanel.repaint();

            try {
                int delay = Math.max(40, 110 - (gameData.getScore() / 10) * 2);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                break;
            }
        }

        if (!snake.isAlive() && running) {
            SoundManager.stopSound(bgMusic);
            scores.add(gameData.getScore());
            ExceptionHandler.writeHighscores(HIGHSCORE_FILE, scores);
            mainFrame.showGameOver(gameData.getScore());
        }
    }

    private void checkCollisions() {
        Point head = snake.getBody().get(0);

        if (head.x < 0 || head.x >= 900 || head.y < 0 || head.y >= 600) {
            snake.setAlive(false);
            return;
        }

        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.equals(snake.getBody().get(i))) {
                snake.setAlive(false);
                return;
            }
        }

        if (gameData.getWalls() != null) {
            for (Wall w : gameData.getWalls()) {
                if (head.x == w.getX() && head.y == w.getY()) {
                    snake.setAlive(false);
                    return;
                }
            }
        }

        if (head.x == food.getX() && head.y == food.getY()) {
            SoundManager.playSound(eatSound);

            int points = food.isSpecial() ? 20 : 10;
            gameData.setScore(gameData.getScore() + points);
            mainFrame.setScore(gameData.getScore());
            
            snake.grow();
            
            gameData.spawnRandomWalls(1, 3, snake, food);
            food.spawn(snake, gameData.getWalls());
        }
    }
}