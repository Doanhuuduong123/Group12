package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Food extends GameObject {
    private boolean isSpecial;
    private Random rand = new Random();

    public Food(int x, int y, int size) {
        super(x, y, size);
        this.isSpecial = false;
    }

    // Hàm spawn thông minh: Yêu cầu truyền vào rắn và danh sách tường để né
    public void spawn(Snake snake, ArrayList<Wall> walls) {
        int maxX = 900 / size; // 30 cột
        int maxY = 600 / size; // 20 hàng
        boolean validPosition;
        int rx, ry;

        // Vòng lặp tìm vị trí trống an toàn
        do {
            validPosition = true;
            rx = rand.nextInt(maxX) * size;
            ry = rand.nextInt(maxY) * size;

            // 1. Kiểm tra xem có đè lên tường không
            if (walls != null) {
                for (Wall w : walls) {
                    if (rx == w.getX() && ry == w.getY()) {
                        validPosition = false;
                        break;
                    }
                }
            }

            // 2. Kiểm tra xem có đè lên thân rắn không
            if (validPosition && snake != null && snake.getBody() != null) {
                for (Point p : snake.getBody()) {
                    if (rx == p.x && ry == p.y) {
                        validPosition = false;
                        break;
                    }
                }
            }
        } while (!validPosition); // Nếu không hợp lệ thì random lại vị trí khác

        // Chốt vị trí an toàn
        this.x = rx;
        this.y = ry;
        this.isSpecial = rand.nextInt(10) > 7; // 20% tỷ lệ ra mồi vàng
    }
    public boolean isSpecial() {
        return isSpecial;
    }
}