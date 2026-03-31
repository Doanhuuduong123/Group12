package controller;

import java.awt.Rectangle;

public class CollisionController {
    
    public boolean checkCollision(Rectangle r1, Rectangle r2) {
        // Sử dụng Rectangle.intersects() để xử lý va chạm 
        return r1.intersects(r2);
    }

    public boolean checkWallCollision() {
        // Logic kiểm tra nếu đầu rắn chạm tường 
        return false; 
    }
}