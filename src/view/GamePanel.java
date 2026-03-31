package view;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
    private Image bgImage, bodyImage, foodImage;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

        for (Point p : snake.getNodes()) {
            g.drawImage(bodyImage, p.x, p.y, this);
        }

        g.drawImage(foodImage, food.getX(), food.getY(), this);
    }
}