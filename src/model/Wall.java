package model;

public class Wall extends GameObject {
    private int width;
    private int height;

    public Wall(int x, int y, int size) {
        super(x, y, size);
        this.width = size;
        this.height = size;
    }
    
    public Wall(int x, int y, int width, int height) {
        super(x, y, 25); // Default size = 25
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}