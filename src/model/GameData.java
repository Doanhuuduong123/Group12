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
        int wallSize = tileSize * 3;

        int maxX = 26;
        int maxY = 16;

        for (int i = 0; i < count; i++) {

            int rx, ry;
            boolean validPosition;
            int attempts = 0;

            do {
                validPosition = true;

                rx = (rand.nextInt(maxX) + 2) * tileSize;
                ry = (rand.nextInt(maxY) + 2) * tileSize;

                java.awt.Rectangle wallRect =
                        new java.awt.Rectangle(rx, ry, wallSize, wallSize);

                // check food
                if (food != null) {

                    java.awt.Rectangle foodRect =
                            new java.awt.Rectangle(
                                    food.getX(),
                                    food.getY(),
                                    tileSize,
                                    tileSize
                            );

                    if (wallRect.intersects(foodRect)) {
                        validPosition = false;
                    }
                }

                // check snake
                if (snake != null && snake.getBody() != null) {
                    for (Point p : snake.getBody()) {

                        java.awt.Rectangle snakeRect =
                                new java.awt.Rectangle(
                                        p.x,
                                        p.y,
                                        tileSize,
                                        tileSize
                                );

                        if (wallRect.intersects(snakeRect)) {
                            validPosition = false;
                            break;
                        }
                    }
                }

                // check wall khác
                for (Wall w : walls) {

                    java.awt.Rectangle other =
                            new java.awt.Rectangle(
                                    w.getX(),
                                    w.getY(),
                                    wallSize,
                                    wallSize
                            );

                    if (wallRect.intersects(other)) {
                        validPosition = false;
                        break;
                    }
                }

                attempts++;

            } while (!validPosition && attempts < 200);

            if (validPosition) {
                walls.add(new Wall(rx, ry, wallSize));
            }
        }
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public ArrayList<Wall> getWalls() { return walls; }
    public boolean isGameOver() { return isGameOver; }
    public void setGameOver(boolean over) { this.isGameOver = over; }
}