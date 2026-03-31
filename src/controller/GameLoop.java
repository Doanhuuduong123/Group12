package controller;

import javax.swing.JPanel;

public class GameLoop extends Thread {
    private boolean running;
    private final int DELAY = 150; // Tốc độ ban đầu
    private JPanel gamePanel;
    private CollisionController collisionController;

    public GameLoop(JPanel panel) {
        this.gamePanel = panel;
        this.running = true;
        this.collisionController = new CollisionController();
    }

    @Override
    public void run() {
        while (running) {
            update();      // Cập nhật vị trí rắn
            checkCollision(); // Kiểm tra va chạm
            gamePanel.repaint(); // Vẽ lại giao diện
            
            try {
                Thread.sleep(DELAY); // Vòng lặp game 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        // Gọi logic di chuyển từ Model (do Thành viên 3 viết)
        // Ví dụ: snake.move();
    }

    private void checkCollision() {
        // Xử lý va chạm 
        if (collisionController.checkWallCollision()) {
            running = false; // Kết thúc game 
        }
    }
    
    public void stopGame() {
        this.running = false;
    }
}