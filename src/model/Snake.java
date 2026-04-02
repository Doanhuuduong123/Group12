package model;

import java.awt.Point;
import java.util.ArrayList;

public class Snake extends GameObject {
    private ArrayList<Point> body;
    private String direction = "RIGHT";
    private boolean isAlive = true;

    public Snake(int x, int y, int size) {
        super(x, y, size);
        reset();
    }

    public void reset() {
        body = new ArrayList<>();
        body.add(new Point(x, y));
        body.add(new Point(x - size, y));
        body.add(new Point(x - size * 2, y));
        direction = "RIGHT";
        isAlive = true;
    }

    public void move() {
        if (!isAlive) return;
        Point head = body.get(0);
        Point newHead = new Point(head.x, head.y);

        switch (direction) {
            case "UP": newHead.y -= size; break;
            case "DOWN": newHead.y += size; break;
            case "LEFT": newHead.x -= size; break;
            case "RIGHT": newHead.x += size; break;
        }

        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail.x, tail.y));
    }

    public ArrayList<Point> getBody() { return body; }
    public String getDirection() { return direction; }
    public void setDirection(String d) { this.direction = d; }
    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { this.isAlive = alive; }
}