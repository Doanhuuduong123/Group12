package model;

public abstract class GameObject {
    protected int x, y, size;
    public GameObject(int x, int y, int size) {
        this.x = x; this.y = y; this.size = size;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
}