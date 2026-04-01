package utils;

import javax.swing.*;
import java.awt.*;

public class HighscoreTable extends JPanel {
    
    public HighscoreTable(Object[][] data) {
        setLayout(new BorderLayout());

        String[] columnNames = {"Rank", "Player", "Score"};
        
        JTable highscoreTable = new JTable(data, columnNames);
        
        JScrollPane scrollPane = new JScrollPane(highscoreTable);
        
        add(scrollPane, BorderLayout.CENTER);
    }
}
