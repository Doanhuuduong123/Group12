package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import model.Snake; 
import model.Food;  
import model.GameData;
import model.Wall;
import java.awt.Point;
import java.util.ArrayList;
import utils.ImageUtils;

public class GamePanel extends JPanel {
    private Snake snake; 
    private Food food;
    private GameData gameData;
    private final int TILE_SIZE = 25;
    
    // Ảnh
    private Image snakeHeadImg, snakeBodyImg, snakeTailImg;
    private Image foodImg, wallImg;

    public GamePanel(Snake snake, Food food, GameData gameData) {
        this.snake = snake;
        this.food = food;
        this.gameData = gameData;
        
        // Load ảnh
        snakeHeadImg = ImageUtils.loadImage("snake_head.png");
        snakeBodyImg = ImageUtils.loadImage("snake_body.png");
        snakeTailImg = ImageUtils.loadImage("snake_tail.png");
        foodImg = ImageUtils.loadImage("redapple.png");
        wallImg = ImageUtils.loadImage("block.png");
        
        this.setPreferredSize(new Dimension(900, 600)); 
        this.setBackground(new Color(34, 139, 34));
    }

    private void drawRotatedImage(Graphics2D g2d, Image img, int x, int y, String direction) {
        if (img == null || direction == null) return;
        double rotation = 0;
        switch (direction) {
            case "UP": rotation = Math.toRadians(-90); break;
            case "DOWN": rotation = Math.toRadians(90); break;
            case "LEFT": rotation = Math.toRadians(180); break;
            case "RIGHT": rotation = 0; break;
            default: rotation = 0; break;
        }
        
        AffineTransform old = g2d.getTransform();
        g2d.translate(x + TILE_SIZE / 2.0, y + TILE_SIZE / 2.0);
        g2d.rotate(rotation);
        g2d.drawImage(img, -TILE_SIZE / 2, -TILE_SIZE / 2, TILE_SIZE, TILE_SIZE, this);
        g2d.setTransform(old);
    }

    // Tính hướng từ điểm A đến điểm B 
    private String getDirectionBetweenPoints(Point from, Point to) {
        int dx = to.x - from.x;
        int dy = to.y - from.y;
        
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? "RIGHT" : "LEFT";
        } else {
            return dy > 0 ? "DOWN" : "UP";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // 1. Vẽ nền kiểu bàn cờ (Checkerboard) cho chuyên nghiệp
        for (int row = 0; row < getHeight() / TILE_SIZE; row++) {
            for (int col = 0; col < getWidth() / TILE_SIZE; col++) {
                if ((row + col) % 2 == 0) {
                    g2d.setColor(new Color(170, 215, 81)); // Xanh cỏ nhạt
                } else {
                    g2d.setColor(new Color(162, 209, 73)); // Xanh cỏ đậm hơn tí
                }
                g2d.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        
        // Vẽ vật cản
        if (gameData != null && gameData.getWalls() != null) {
            for (Wall wall : gameData.getWalls()) {
                if (wallImg != null) {
                    g2d.drawImage(wallImg, wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight(), this);
                } else {
                    // Fallback: vẽ hình vuông
                    g2d.setColor(new Color(80, 80, 80));
                    g2d.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
                }
            }
        }

        // Vẽ rắn
        if (snake != null && snake.getBody() != null && snake.getBody().size() > 0) {
            ArrayList<Point> body = snake.getBody();
            
            for (int i = 0; i < body.size(); i++) {
                Point p = body.get(i);
                
                // Vẽ bóng đổ nhẹ cho rắn (Shadow effect)
                g2d.setColor(new Color(0, 0, 0, 50));
                g2d.fillRoundRect(p.x + 2, p.y + 2, TILE_SIZE, TILE_SIZE, 10, 10);

                if (i == 0) {
                    // Vẽ ĐẦU rắn - xanh đậm với mắt
                    if (snakeHeadImg != null) {
                        drawRotatedImage(g2d, snakeHeadImg, p.x, p.y, snake.getDirection());
                    } else {
                        g2d.setColor(new Color(0, 100, 0));
                        g2d.fillRoundRect(p.x, p.y, TILE_SIZE, TILE_SIZE, 10, 10);
                        g2d.setStroke(new BasicStroke(2));
                        g2d.setColor(new Color(0, 150, 0));
                        g2d.drawRect(p.x, p.y, TILE_SIZE, TILE_SIZE);
                        // Vẽ mắt
                        g2d.setColor(Color.WHITE);
                        g2d.fillOval(p.x + 5, p.y + 5, 6, 6);
                        g2d.fillOval(p.x + 14, p.y + 5, 6, 6);
                        g2d.setColor(Color.BLACK);
                        g2d.fillOval(p.x + 6, p.y + 6, 3, 3);
                        g2d.fillOval(p.x + 15, p.y + 6, 3, 3);
                    }
                } else if (i == body.size() - 1) {
                    // Vẽ ĐUÔI rắn - xoay theo hướng từ phần body trước nó
                    Point prevPart = body.get(i - 1);
                    String tailDirection = getDirectionBetweenPoints(prevPart, p);
                    
                    if (snakeTailImg != null) {
                        drawRotatedImage(g2d, snakeTailImg, p.x, p.y, tailDirection);
                    } else {
                        g2d.setColor(new Color(255, 200, 0));
                        g2d.fillOval(p.x + 3, p.y + 3, TILE_SIZE - 6, TILE_SIZE - 6);
                        g2d.setStroke(new BasicStroke(1));
                        g2d.setColor(new Color(200, 150, 0));
                        g2d.drawRect(p.x + 3, p.y + 3, TILE_SIZE - 6, TILE_SIZE - 6);
                    }
                } else {
                    // Vẽ THÂN rắn - xanh vừa, xoay để nối đúng với body trước
                    Point prevPart = body.get(i - 1);
                    String bodyDirection = getDirectionBetweenPoints(prevPart, p);
                    
                    if (snakeBodyImg != null) {
                        drawRotatedImage(g2d, snakeBodyImg, p.x, p.y, bodyDirection);
                    } else {
                        g2d.setColor(new Color(50, 180, 50));
                        g2d.fillRoundRect(p.x + 1, p.y + 1, TILE_SIZE - 2, TILE_SIZE - 2, 8, 8);
                        g2d.setStroke(new BasicStroke(1));
                        g2d.setColor(new Color(0, 100, 0));
                        g2d.drawRect(p.x, p.y, TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        }

        // Vẽ thức ăn
        if (food != null) {
            if (foodImg != null) {
                g2d.drawImage(foodImg, food.getX(), food.getY(), TILE_SIZE, TILE_SIZE, this);
            } else {
                // Vẽ táo tròn đẹp
                g2d.setColor(new Color(255, 0, 0));
                g2d.fillOval(food.getX() + 2, food.getY() + 2, TILE_SIZE - 4, TILE_SIZE - 4);
                g2d.setColor(new Color(200, 0, 0));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawOval(food.getX() + 2, food.getY() + 2, TILE_SIZE - 4, TILE_SIZE - 4);
                // Vẽ cuống táo
                g2d.setColor(new Color(100, 150, 0));
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(food.getX() + TILE_SIZE/2, food.getY(), food.getX() + TILE_SIZE/2, food.getY() + 3);
            }
        }
    }
}
