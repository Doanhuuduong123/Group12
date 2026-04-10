package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Food extends GameObject {
    private boolean isSpecial;
    private Random rand = new Random();

    public Food(int x, int y, int size) {
        super(x, y, size);
        this.isSpecial = false;
    }

    public void spawn(Snake snake, ArrayList<Wall> walls) {

        int maxX = 25;
        int maxY = 15;

        boolean validPosition;
        int rx, ry;

        int wallSize = size * 3;

        do {
            validPosition = true;

            rx = (rand.nextInt(maxX) + 2) * size;
            ry = (rand.nextInt(maxY) + 2) * size;

            // rectangle food
            java.awt.Rectangle foodRect =
                    new java.awt.Rectangle(rx, ry, size, size);

            // 1. check wall (3x3)
            if (walls != null) {
                for (Wall w : walls) {

                    java.awt.Rectangle wallRect =
                            new java.awt.Rectangle(
                                    w.getX(),
                                    w.getY(),
                                    wallSize,
                                    wallSize
                            );

                    if (foodRect.intersects(wallRect)) {
                        validPosition = false;
                        break;
                    }
                }
            }

            // 2. check snake
            if (validPosition && snake != null && snake.getBody() != null) {
                for (Point p : snake.getBody()) {

                    java.awt.Rectangle snakeRect =
                            new java.awt.Rectangle(
                                    p.x,
                                    p.y,
                                    size,
                                    size
                            );

                    if (foodRect.intersects(snakeRect)) {
                        validPosition = false;
                        break;
                    }
                }
            }

        } while (!validPosition);

        this.x = rx;
        this.y = ry;

        // 20% táo vàng
        this.isSpecial = rand.nextInt(10) > 7;
    }

    public boolean isSpecial() {
        return isSpecial;
    }
}