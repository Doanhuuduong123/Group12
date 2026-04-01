package model;

import java.util.Random;

public class Wall extends GameObject {
    private String direction;

    public Wall(int x, int y, int size) {
        super(x, y, size);
        
        // Random 1 trong 4 hướng bất kỳ cho khối block
        String[] dirs = {"UP", "DOWN", "LEFT", "RIGHT"};
        this.direction = dirs[new Random().nextInt(4)];
    }

    // Getter để View lấy hướng ra vẽ
    public String getDirection() {
        return direction;
    }
}