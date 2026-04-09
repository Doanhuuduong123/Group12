package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import model.Snake; 
import model.Food;  
import model.GameData;
import model.Wall;

import java.util.ArrayList;
import utils.ImageUtils;

public class GamePanel extends JPanel {
    private Snake snake; 
    private Food food;
    private GameData gameData;

    private final int TILE_SIZE = 30; 
    private final int BOTTOM_BAR_HEIGHT = 45;
    
    private Image headImg, bodyImg, bodyCurveImg, tailImg;
    private Image redAppleImg, yellowAppleImg, wallImg;

    public GamePanel(Snake snake, Food food, GameData gameData) {
        this.snake = snake;
        this.food = food;
        this.gameData = gameData;
        
        this.setBackground(new Color(34, 139, 34)); 
        this.setPreferredSize(new Dimension(900, 600));
        
        headImg = ImageUtils.loadImage("head.png");
        bodyImg = ImageUtils.loadImage("body.png");
        bodyCurveImg = ImageUtils.loadImage("body_curve.png");
        tailImg = ImageUtils.loadImage("tail.png");
        redAppleImg = ImageUtils.loadImage("red_apple.png");
        yellowAppleImg = ImageUtils.loadImage("yellow_apple.png");
        wallImg = ImageUtils.loadImage("block_w.png");
    }

    private void drawRotatedImage(Graphics2D g2d, Image img, int x, int y, String direction) {
        if (img == null || direction == null) return;

        double rotation = 0;
        switch (direction) {
            case "UP": rotation = Math.toRadians(0); break;
            case "DOWN": rotation = Math.toRadians(180); break;
            case "LEFT": rotation = Math.toRadians(-90); break;
            case "RIGHT": rotation = Math.toRadians(90); break;
        }
        
        AffineTransform old = g2d.getTransform();
        g2d.translate(x + TILE_SIZE / 2.0, y + TILE_SIZE / 2.0);
        g2d.rotate(rotation);
        
        int offset = 6; 
        g2d.drawImage(img,
                -(TILE_SIZE + offset) / 2,
                -(TILE_SIZE + offset) / 2,
                TILE_SIZE + offset,
                TILE_SIZE + offset,
                this);

        g2d.setTransform(old);
    }

    // WALL 3x3
    private void drawRotatedWall(Graphics2D g2d, Image img, int x, int y, String direction) {
        if (img == null || direction == null) return;

        int size = TILE_SIZE * 2;

        double rotation = 0;
        switch (direction) {
            case "UP": rotation = Math.toRadians(0); break;
            case "DOWN": rotation = Math.toRadians(180); break;
            case "LEFT": rotation = Math.toRadians(-90); break;
            case "RIGHT": rotation = Math.toRadians(90); break;
        }

        AffineTransform old = g2d.getTransform();

        g2d.translate(x + size / 2.0, y + size / 2.0);
        g2d.rotate(rotation);

        g2d.drawImage(
                img,
                -size / 2,
                -size / 2,
                size,
                size,
                this
        );

        g2d.setTransform(old);
    }

    private String getDir(Point from, Point to) {
        if (from.x < to.x) return "RIGHT";
        if (from.x > to.x) return "LEFT";
        if (from.y < to.y) return "DOWN";
        if (from.y > to.y) return "UP";
        return "UP";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        try {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            if (w <= 0 || h <= 0) return; 

            // thanh đen dưới
            g2d.setColor(new Color(30, 30, 30));
            g2d.fillRect(0, h - BOTTOM_BAR_HEIGHT, w, BOTTOM_BAR_HEIGHT);

            int playHeight = h - BOTTOM_BAR_HEIGHT;

            // nền caro
            for (int i = 0; i < (w / TILE_SIZE) + 1; i++) {
                for (int j = 0; j < (playHeight / TILE_SIZE) + 1; j++) {
                    g2d.setColor((i + j) % 2 == 0
                            ? new Color(170, 215, 81)
                            : new Color(162, 209, 73));
                    g2d.fillRect(i * TILE_SIZE,
                                 j * TILE_SIZE,
                                 TILE_SIZE,
                                 TILE_SIZE);
                }
            }

            // vẽ snake
            if (snake != null && snake.getBody() != null && !snake.getBody().isEmpty()) {
                ArrayList<Point> body = new ArrayList<>(snake.getBody());

                for (int i = 0; i < body.size(); i++) {
                    Point p = body.get(i);

                    if (p.y >= playHeight) continue;

                    if (i == 0) { 
                        drawRotatedImage(g2d, headImg, p.x, p.y, snake.getDirection());
                    } 
                    else if (i == body.size() - 1) { 
                        String dir = getDir(p, body.get(i-1));
                        drawRotatedImage(g2d, tailImg, p.x, p.y, dir);
                    } 
                    else { 
                        Point prev = body.get(i - 1);
                        Point next = body.get(i + 1);

                        if (prev.x == next.x || prev.y == next.y) {
                            String dir = getDir(p, prev);
                            drawRotatedImage(g2d, bodyImg, p.x, p.y, dir);
                        } else {
                            boolean up = (prev.y < p.y) || (next.y < p.y);
                            boolean down = (prev.y > p.y) || (next.y > p.y);
                            boolean left = (prev.x < p.x) || (next.x < p.x);
                            boolean right = (prev.x > p.x) || (next.x > p.x);

                            String curveDir = "UP";

                            if (up && right) curveDir = "UP";
                            else if (right && down) curveDir = "RIGHT";
                            else if (down && left) curveDir = "DOWN";
                            else if (left && up) curveDir = "LEFT";
                               
                            drawRotatedImage(g2d, bodyCurveImg, p.x, p.y, curveDir);
                        }
                    }
                }
            }

            // vẽ food
            if (food != null && food.getY() < playHeight) {
                Image img = food.isSpecial()
                        ? yellowAppleImg
                        : redAppleImg;

                if (img != null) {
                    g2d.drawImage(img,
                            food.getX(),
                            food.getY(),
                            TILE_SIZE,
                            TILE_SIZE,
                            this);
                }
            }

            // vẽ wall 3x3
            if (gameData != null && gameData.getWalls() != null) {
                for (Wall wWall : gameData.getWalls()) {

                    if (wWall.getY() >= playHeight) continue;

                    if (wallImg != null) {
                        drawRotatedWall(g2d,
                                wallImg,
                                wWall.getX(),
                                wWall.getY(),
                                wWall.getDirection());
                    } else {
                        g2d.setColor(Color.DARK_GRAY);
                        g2d.fillRect(
                                wWall.getX(),
                                wWall.getY(),
                                TILE_SIZE,
                                TILE_SIZE 
                        );
                    }
                }
            }

        } catch (Exception e) {
            g2d.setColor(Color.RED);
            g2d.drawString("LỖI: " + e.getMessage(), 50, 50);
        }
    }
}