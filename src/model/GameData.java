package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GameData {
    private int score = 0;
    private boolean isGameOver = false;
    private ArrayList<Wall> walls = new ArrayList<>();
    private Random rand = new Random();

    public GameData() {
        walls.clear();
    }

    public void spawnRandomWalls(int min, int max, Snake snake, Food food) {
        int count = rand.nextInt(max - min + 1) + min;
        int tileSize = 30;
        int maxX = 900 / tileSize;
        int maxY = 600 / tileSize;

        for (int i = 0; i < count; i++) {
            int rx, ry;
            boolean validPosition;
            int attempts = 0;

            do {
                validPosition = true;
                rx = rand.nextInt(maxX) * tileSize;
                ry = rand.nextInt(maxY) * tileSize;

                if (food != null && rx == food.getX() && ry == food.getY()) {
                    validPosition = false;
                }

                if (snake != null && snake.getBody() != null) {
                    for (Point p : snake.getBody()) {
                        if (rx == p.x && ry == p.y) {
                            validPosition = false;
                            break;
                        }
                    }
                }

                for (Wall w : walls) {
                    if (rx == w.getX() && ry == w.getY()) {
                        validPosition = false;
                        break;
                    }
                }
                attempts++;
            } while (!validPosition && attempts < 100);

            if (validPosition) {
                walls.add(new Wall(rx, ry, tileSize));
            }
        }
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public ArrayList<Wall> getWalls() { return walls; }
    public boolean isGameOver() { return isGameOver; }
    public void setGameOver(boolean over) { this.isGameOver = over; }
}