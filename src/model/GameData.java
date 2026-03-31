package model;
import java.util.ArrayList;

public class GameData {

    private int score;
    private boolean gameOver;
    private ArrayList<Wall> walls;

    public GameData() {
        score = 0;
        gameOver = false;
        walls = new ArrayList<>();
        initializeWalls();
    }
    
    private void initializeWalls() {
        // Tạo một số vật cản trong game
        walls.add(new Wall(200, 200, 25, 100));
        walls.add(new Wall(600, 300, 25, 100));
        walls.add(new Wall(350, 450, 150, 25));
    }
    
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public int getScore() { return score; }

    public void increaseScore() {
        score++;
    }
    
    public void increaseScore(int points) {
        score += points;
    }
    
    public void setScore(int points) {
        score = points;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}//Quản lý trạng thái game