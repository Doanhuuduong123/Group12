package model;

import java.awt.Point;
import java.util.ArrayList;

public class Snake extends GameObject {

    private ArrayList<Point> body;
    private String direction;
    private String lastMovedDirection;

    public Snake(int x, int y, int size) {
        super(x, y, size);
        body = new ArrayList<>();
        // Khởi tạo rắn với 3 phần: đầu ở x, thân ở x-size, đuôi ở x-2*size
        body.add(new Point(x, y));        // Đầu
        body.add(new Point(x - size, y)); // Thân
        body.add(new Point(x - 2*size, y)); // Đuôi
        direction = "RIGHT";
        lastMovedDirection = "RIGHT";
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLastMovedDirection() {
        return lastMovedDirection;
    }

    public void move() {
        lastMovedDirection = direction; // Lưu lại hướng vừa thực hiện
        Point head = body.get(0);
        int newX = head.x;
        int newY = head.y;

        switch (direction) {
            case "UP": newY -= size; break;
            case "DOWN": newY += size; break;
            case "LEFT": newX -= size; break;
            case "RIGHT": newX += size; break;
        }

        // Wrap around - kết nối đầu đuôi con rắn
        final int GAME_WIDTH = 900;
        final int GAME_HEIGHT = 600;
        
        if (newX < 0) newX = GAME_WIDTH - size;
        if (newX >= GAME_WIDTH) newX = 0;
        if (newY < 0) newY = GAME_HEIGHT - size;
        if (newY >= GAME_HEIGHT) newY = 0;

        body.add(0, new Point(newX, newY));
        body.remove(body.size() - 1);
    }

    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail.x, tail.y));
    }
    
    public void reset() {
        body.clear();
        body.add(new Point(x, y));
        direction = "RIGHT";
        lastMovedDirection = "RIGHT";
    }
}