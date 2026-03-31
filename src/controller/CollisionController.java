package controller;

import java.awt.Rectangle;
import java.awt.Point;
import model.Snake;
import model.Food;
import model.GameData;
import model.Wall;
import java.util.ArrayList;

public class CollisionController {

    private final int TILE_SIZE = 25;
    
    public boolean checkCollision(Rectangle r1, Rectangle r2) {
        // Sử dụng Rectangle.intersects() để xử lý va chạm 
        return r1.intersects(r2);
    }

    // Kiểm tra rắn ăn food
    public boolean checkFoodCollision(Snake snake, Food food) {
        if (snake == null || snake.getBody() == null || snake.getBody().isEmpty()) {
            return false;
        }
        
        Point snakeHead = snake.getBody().get(0);
        Rectangle snakeHeadRect = new Rectangle(snakeHead.x, snakeHead.y, TILE_SIZE, TILE_SIZE);
        Rectangle foodRect = new Rectangle(food.getX(), food.getY(), TILE_SIZE, TILE_SIZE);
        
        return snakeHeadRect.intersects(foodRect);
    }

    public boolean checkWallCollision(Snake snake) {
        if (snake == null || snake.getBody() == null || snake.getBody().isEmpty()) {
            return false;
        }
        
        // Vì có wrap around, không check tường cạnh màn hình
        // Thay vào đó, check va chạm với các vật cản
        return false;
    }
    
    // Kiểm tra rắn chạm vào vật cản
    public boolean checkObstacleCollision(Snake snake, GameData gameData) {
        if (snake == null || snake.getBody() == null || snake.getBody().isEmpty() || gameData == null) {
            return false;
        }
        
        Point snakeHead = snake.getBody().get(0);
        Rectangle snakeHeadRect = new Rectangle(snakeHead.x, snakeHead.y, TILE_SIZE, TILE_SIZE);
        
        ArrayList<Wall> walls = gameData.getWalls();
        if (walls == null) {
            return false;
        }
        
        for (Wall wall : walls) {
            Rectangle wallRect = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
            if (snakeHeadRect.intersects(wallRect)) {
                return true; // Chạm vật cản - game over
            }
        }
        return false;
    }

    // Kiểm tra rắn chạm vào chính mình (game over)
    public boolean checkSelfCollision(Snake snake) {
        if (snake == null || snake.getBody() == null || snake.getBody().size() <= 4) {
            return false; // Rắn mới bắt đầu, không thể chạm vào chính mình
        }
        
        Point snakeHead = snake.getBody().get(0);
        Rectangle snakeHeadRect = new Rectangle(snakeHead.x, snakeHead.y, TILE_SIZE, TILE_SIZE);
        
        // Kiểm tra đầu rắn với các phần thân (bỏ qua đầu và các phần gần đầu)
        for (int i = 4; i < snake.getBody().size(); i++) {
            Point bodyPart = snake.getBody().get(i);
            Rectangle bodyPartRect = new Rectangle(bodyPart.x, bodyPart.y, TILE_SIZE, TILE_SIZE);
            
            if (snakeHeadRect.intersects(bodyPartRect)) {
                return true; // Chạm vào thân - game over
            }
        }
        return false;
    }
}