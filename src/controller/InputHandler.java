package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Snake;

public class InputHandler extends KeyAdapter {
    private Snake snake;

    public InputHandler(Snake snake) { this.snake = snake; }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        String currentDir = snake.getDirection();

        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !currentDir.equals("DOWN")) 
            snake.setDirection("UP");
        if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !currentDir.equals("UP")) 
            snake.setDirection("DOWN");
        if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !currentDir.equals("RIGHT")) 
            snake.setDirection("LEFT");
        if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !currentDir.equals("LEFT")) 
            snake.setDirection("RIGHT");
    }
}