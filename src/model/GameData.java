package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GameData {
    private int score = 0;
    private boolean isGameOver = false;
    private ArrayList<Wall> walls = new ArrayList<>();
    private Random rand = new Random();

    public GameData() {
        walls.clear();
    }
public void spawnRandomWalls(int min, int max, Snake snake, Food food) {
        int count = rand.nextInt(max - min + 1) + min;
        int tileSize = 30;
        
        // 1. SỬA GIỚI HẠN refined (Total conceptual board 900x600, Border 60px all around)
        // Vùng chơi playable X [60, 840], Y [60, 540] conceptually.
        // maxX_playable = (840-60)/30 = 26 tiles, offset +2 for [60, 810] range
        int maxX = 26; // num tiles playable X
        // maxY_playable = (540-60)/30 = 16 tiles, offset +2 for [60, 510] range
        int maxY = 16; // num tiles playable Y

        for (int i = 0; i < count; i++) {
            int rx, ry;
            boolean validPosition;
            int attempts = 0;

            do {
                validPosition = true;
                
                // 2. TẠO TỌA ĐỘ refined (offset +2 tiles = 60px)
                // rx range: [2*30, (26-1+2)*30] = [60, 810]. Last wall tile 810-840X.
                rx = (rand.nextInt(maxX) + 2) * tileSize; 
                // ry range: [2*30, (16-1+2)*30] = [60, 510]. Last wall tile 510-540Y.
                ry = (rand.nextInt(maxY) + 2) * tileSize; 

                // ... (Phần code kiểm tra trùng lặp với Food, Snake, Wall bên dưới GIỮ NGUYÊN) ...
                if (food != null && rx == food.getX() && ry == food.getY()) {
                    validPosition = false;
                }

                if (snake != null && snake.getBody() != null) {
                    for (Point p : snake.getBody()) {
                        if (rx == p.x && ry == p.y) {
                            validPosition = false;
                            break;
                        }
                    }
                }

                for (Wall w : walls) {
                    if (rx == w.getX() && ry == w.getY()) {
                        validPosition = false;
                        break;
                    }
                }
                attempts++;
            } while (!validPosition && attempts < 100);

            if (validPosition) {
                walls.add(new Wall(rx, ry, tileSize));
            }
        }
    }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public ArrayList<Wall> getWalls() { return walls; }
    public boolean isGameOver() { return isGameOver; }
    public void setGameOver(boolean over) { this.isGameOver = over; }
}