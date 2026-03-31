package model;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int size;

    public GameObject(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
} //Gom thuộc tính chung (x, y, size) giúp food snake wall kế thừa