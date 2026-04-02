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
        int maxX = 900 / size;
        int maxY = 600 / size;
        boolean validPosition;
        int rx, ry;

        do {
            validPosition = true;
            rx = rand.nextInt(maxX) * size;
            ry = rand.nextInt(maxY) * size;

            if (walls != null) {
                for (Wall w : walls) {
                    if (rx == w.getX() && ry == w.getY()) {
                        validPosition = false;
                        break;
                    }
                }
            }

            if (validPosition && snake != null && snake.getBody() != null) {
                for (Point p : snake.getBody()) {
                    if (rx == p.x && ry == p.y) {
                        validPosition = false;
                        break;
                    }
                }
            }
        } while (!validPosition);

        this.x = rx;
        this.y = ry;
        this.isSpecial = rand.nextInt(10) > 7;
    }
    public boolean isSpecial() {
        return isSpecial;
    }
}