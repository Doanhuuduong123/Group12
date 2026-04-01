package controller;

import model.Food;
import model.GameData;
import model.Snake;
import model.Wall;
import view.GamePanel;
import view.MainFrame;
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

    public GameLoop(GamePanel gp, Snake s, Food f, GameData gd, MainFrame mf, ArrayList<Integer> scores) {
        this.gamePanel = gp; this.snake = s; this.food = f; 
        this.gameData = gd; this.mainFrame = mf; this.scores = scores;
    }

    public void stopGame() { running = false; }

    @Override
    public void run() {
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
            scores.add(gameData.getScore());
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
            int points = food.isSpecial() ? 20 : 10;
            gameData.setScore(gameData.getScore() + points);
            mainFrame.setScore(gameData.getScore());
            
            snake.grow();
            food.spawn(snake, gameData.getWalls());
            
            gameData.spawnRandomWalls(1, 3, snake, food);
        }
    }
}