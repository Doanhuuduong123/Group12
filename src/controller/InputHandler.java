package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Snake; // Nhớ import Snake nhé

public class InputHandler extends KeyAdapter {
    private Snake snake;

    // PHẢI CÓ ĐOẠN NÀY: Constructor để nhận con rắn từ Main truyền vào
    public InputHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Sử dụng hướng di chuyển thực tế cuối cùng của rắn để chặn quay đầu 180 độ
        String lastDir = snake.getLastMovedDirection();

        // Xử lý đổi hướng cho rắn
        if (key == KeyEvent.VK_UP && !lastDir.equals("DOWN")) {
            snake.setDirection("UP");
        } else if (key == KeyEvent.VK_DOWN && !lastDir.equals("UP")) {
            snake.setDirection("DOWN");
        } else if (key == KeyEvent.VK_LEFT && !lastDir.equals("RIGHT")) {
            snake.setDirection("LEFT");
        } else if (key == KeyEvent.VK_RIGHT && !lastDir.equals("LEFT")) {
            snake.setDirection("RIGHT");
        }
    }
}