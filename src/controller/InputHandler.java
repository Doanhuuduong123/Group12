package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    // Giả sử có enum Direction { UP, DOWN, LEFT, RIGHT }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Điều khiển hướng rắn 
        if (key == KeyEvent.VK_LEFT) {
            // snake.setDirection(LEFT);
        } else if (key == KeyEvent.VK_RIGHT) {
            // snake.setDirection(RIGHT);
        } else if (key == KeyEvent.VK_UP) {
            // snake.setDirection(UP);
        } else if (key == KeyEvent.VK_DOWN) {
            // snake.setDirection(DOWN);
        }
    }
}