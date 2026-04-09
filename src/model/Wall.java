package model;

import java.util.Random;

public class Wall extends GameObject {
    private String direction;

    public Wall(int x, int y, int size) {
        super(x, y, size);

        String[] dirs = {"UP", "DOWN", "LEFT", "RIGHT"};
        this.direction = dirs[new Random().nextInt(4)];
    }

    public String getDirection() {
        return direction;
    }
}