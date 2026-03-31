package model;

import java.awt.Point;
import java.util.ArrayList;

public class Snake extends GameObject {

    private ArrayList<Point> body;
    private String direction;

    public Snake(int x, int y, int size) {
        super(x, y, size);
        body = new ArrayList<>();
        body.add(new Point(x, y));
        direction = "RIGHT";
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

    public void move() {
        Point head = body.get(0);
        int newX = head.x;
        int newY = head.y;

        switch (direction) {
            case "UP": newY -= size; break;
            case "DOWN": newY += size; break;
            case "LEFT": newX -= size; break;
            case "RIGHT": newX += size; break;
        }

        body.add(0, new Point(newX, newY));
        body.remove(body.size() - 1);
    }

    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail.x, tail.y));
    }
}