package model;

import java.util.Random;

public class Food extends GameObject {

    private Random rand = new Random();

    // Sửa Constructor để nhận 3 tham số cho khớp với hàm Main
    public Food(int x, int y, int size) {
        super(x, y, size);
        // Nếu muốn thức ăn tự nhảy vị trí ngẫu nhiên ngay khi tạo thì gọi spawn()
        // spawn(); 
    }

    public void spawn() {
        // Với TILE_SIZE = 25: 900/25 = 36 ô ngang, 600/25 = 24 ô dọc
        this.x = rand.nextInt(36) * size;
        this.y = rand.nextInt(24) * size;
    }

    // Phải có Getter để GamePanel có thể lấy tọa độ vẽ ảnh
    public int getX() { return x; }
    public int getY() { return y; }
}