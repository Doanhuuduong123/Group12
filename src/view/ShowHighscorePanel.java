package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ShowHighscorePanel extends JPanel {
    private JTable table;
    private JButton btnBack;
    private ArrayList<Integer> scores;
    
    public ShowHighscorePanel(ArrayList<Integer> scores) {
        this.scores = scores;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(25, 25, 25));
        
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(0, 0, new Color(60, 60, 60),
                        0, getHeight(), new Color(30, 30, 30));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("RANKING");
        title.setFont(new Font("Arial Black", Font.BOLD, 44));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);
        
        JLabel subtitle = new JLabel("TOP PLAYER");
        subtitle.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitle.setForeground(new Color(255, 255, 150));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(subtitle);

        JPanel tablePanel = createTablePanel(scores);

        JPanel statsPanel = createStatsPanel(scores);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(20, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
       // 🔴 TỰ VẼ NỀN CHO NÚT BẤM (BẤT CHẤP MỌI HỆ ĐIỀU HÀNH)
        btnBack = new JButton("BACK TO MENU") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Kiểm tra xem chuột có đang nằm trên nút không
                if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 50, 50)); // Đỏ tươi khi hover
                } else {
                    g2d.setColor(new Color(180, 0, 0));   // Đỏ thẫm mặc định
                }
                
                // Vẽ một hình chữ nhật bo góc làm nền
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); 
                super.paintComponent(g); // Vẽ chữ đè lên trên nền
            }
        };
        
        btnBack.setFont(new Font("Arial", Font.BOLD, 18));
        btnBack.setForeground(Color.WHITE); // Chữ màu trắng
        
        // 3 lệnh cực kỳ quan trọng để lột bỏ lớp vỏ mặc định của nút
        btnBack.setContentAreaFilled(false); 
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(250, 50));
        
        // Đoạn này KHÔNG CẦN addMouseListener nữa vì paintComponent đã tự lo phần hover rồi.
        
        // 🔴 ÉP NÚT BẤM HIỂN THỊ NỀN ĐỎ (Fix lỗi mất nền)
        btnBack.setBackground(new Color(180, 0, 0)); 
        btnBack.setOpaque(true); // Dòng này cực kỳ quan trọng để hiện nền màu
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false); // Bỏ viền để nút trông phẳng và hiện đại
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(250, 50));
        
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnBack.setBackground(new Color(255, 50, 50)); 
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnBack.setBackground(new Color(180, 0, 0)); 
            }
        });
        
        buttonPanel.add(btnBack);
        
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(new Color(25, 25, 25));
        centerContainer.add(tablePanel, BorderLayout.CENTER);
        centerContainer.add(statsPanel, BorderLayout.SOUTH);
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerContainer, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createTablePanel(ArrayList<Integer> scores) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(25, 25, 25));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        String[] columnNames = {"RANK", "SCORE", "RATING"};
        Object[][] data = new Object[10][3];
        
        ArrayList<Integer> sortedScores = new ArrayList<>(scores);
        sortedScores.sort((a, b) -> Integer.compare(b, a));
        
        for (int i = 0; i < 10; i++) {
            int score = (i < sortedScores.size()) ? sortedScores.get(i) : 0;
            String rank = (i + 1) + "";
            
            if (i == 0) rank = "1st";  
            else if (i == 1) rank = "2nd";
            else if (i == 2) rank = "3rd";
            
            String rating = "";
            
            if (score == 0) {
                rating = "---";
            } else if (score >= 100) {
                rating = "LEGEND";
            } else if (score >= 80) {
                rating = "MASTER";
            } else if (score >= 60) {
                rating = "PRO";
            } else if (score >= 40) {
                rating = "AVERAGE";
            } else if (score > 0) {
                rating = "NEWBIE";
            }
            
            data[i][0] = rank;
            data[i][1] = score;
            data[i][2] = rating;
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.BOLD, 18));
        table.setRowHeight(50);
        table.setBackground(new Color(45, 45, 45));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(60, 60, 60));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(80, 80, 80));
        table.setSelectionForeground(Color.WHITE);

        // 🔴 ÉP THANH TIÊU ĐỀ (HEADER) HIỂN THỊ NỀN ĐỎ BẰNG RENDERER
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(180, 0, 0)); // Nền đỏ thẫm
        headerRenderer.setForeground(Color.WHITE);          // Chữ trắng
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        headerRenderer.setFont(new Font("Arial", Font.BOLD, 20));
        
        table.getTableHeader().setPreferredSize(new Dimension(0, 50));
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        DefaultTableCellRenderer gradientRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    c.setBackground(new Color(100, 100, 100));
                } else {
                    if (row < 3) {
                        c.setBackground(new Color(55, 55, 55));
                    } else if (row < 6) {
                        c.setBackground(new Color(45, 45, 45));
                    } else {
                        c.setBackground(new Color(35, 35, 35));
                    }
                }
                
                c.setForeground(Color.WHITE);
                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
                ((JLabel) c).setFont(new Font("Arial", Font.BOLD, 18));
                
                return c;
            }
        };
        
        for (int i = 0; i < 3; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(gradientRenderer);
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(25, 25, 25));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 0, 0), 4));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createStatsPanel(ArrayList<Integer> scores) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(20, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        ArrayList<Integer> sortedScores = new ArrayList<>(scores);
        sortedScores.sort((a, b) -> Integer.compare(b, a));
        
        int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
        int maxScore = scores.isEmpty() ? 0 : sortedScores.get(0);
        int minScore = scores.isEmpty() ? 0 : scores.stream().mapToInt(Integer::intValue).min().orElse(0);
        double avgScore = scores.isEmpty() ? 0 : (double) totalScore / scores.size();
        int gameCount = scores.size();
        
        JLabel statsLabel = new JLabel(
            String.format("THONG KE | Cao nhat: %d | Thap nhat: %d | Trung binh: %.1f | Luot choi: %d | Tong diem: %d",
                maxScore, minScore, avgScore, gameCount, totalScore)
        );
        statsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statsLabel.setForeground(new Color(255, 215, 0));
        
        panel.add(statsLabel);
        panel.add(Box.createHorizontalGlue());
        
        return panel;
    }
    
    public JButton getBtnBack() {
        return btnBack;
    }
}