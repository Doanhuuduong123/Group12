@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this); [cite: 31]

    for (Point p : snake.getNodes()) { [cite: 27]
        g.drawImage(bodyImage, p.x, p.y, this); [cite: 31]
    }

    g.drawImage(foodImage, food.getX(), food.getY(), this); [cite: 31]
}
