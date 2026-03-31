package utils;

import javax.swing.*;
import java.awt.*;

public class HighscoreTable extends JPanel {
    
    public HighscoreTable(Object[][] data) {
        // Thiết lập layout cho Panel
        setLayout(new BorderLayout());

        String[] columnNames = {"Hạng", "Tên người chơi", "Điểm số"};
        
        // Tạo bảng với dữ liệu truyền vào
        JTable highscoreTable = new JTable(data, columnNames);
        
        // Đưa bảng vào thanh cuộn (ScrollPane)
        JScrollPane scrollPane = new JScrollPane(highscoreTable);
        
        // Thêm vào chính giữa Panel
        add(scrollPane, BorderLayout.CENTER);
    }
}
