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
        this.setBackground(new Color(34, 139, 34));
        
        // ===== PHẦN TIÊU ĐỀ =====
        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Vẽ gradient nền
                GradientPaint gradient = new GradientPaint(0, 0, new Color(76, 175, 80),
                        0, getHeight(), new Color(56, 142, 60));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("🏆 BẢNG XẾP HẠNG THẾ GIỚI 🏆");
        title.setFont(new Font("Arial Black", Font.BOLD, 44));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);
        
        JLabel subtitle = new JLabel("Những VĐV hàng đầu");
        subtitle.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitle.setForeground(new Color(255, 255, 150));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(subtitle);
        
        // ===== PHẦN BẢNG =====
        JPanel tablePanel = createTablePanel(scores);
        
        // ===== PHẦN THỐNG KÊ =====
        JPanel statsPanel = createStatsPanel(scores);
        
        // ===== PHẦN NÚT QUAY LẠI =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(20, 100, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        btnBack = new JButton("◀️  QUAY LẠI MENU");
        btnBack.setFont(new Font("Arial", Font.BOLD, 18));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(100, 100, 100));
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(250, 50));
        
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnBack.setBackground(new Color(150, 150, 150));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnBack.setBackground(new Color(100, 100, 100));
            }
        });
        
        buttonPanel.add(btnBack);
        
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(new Color(34, 139, 34));
        centerContainer.add(tablePanel, BorderLayout.CENTER);
        centerContainer.add(statsPanel, BorderLayout.SOUTH);
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerContainer, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createTablePanel(ArrayList<Integer> scores) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(34, 139, 34));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Tạo dữ liệu bảng
        String[] columnNames = {"🥇 Hạng", "📊 Điểm Số", "⭐ Đánh Giá"};
        Object[][] data = new Object[10][3];
        
        // Sắp xếp điểm (lớn nhất trước)
        ArrayList<Integer> sortedScores = new ArrayList<>(scores);
        sortedScores.sort((a, b) -> Integer.compare(b, a));
        
        // Lấy top 10
        for (int i = 0; i < 10; i++) {
            int score = (i < sortedScores.size()) ? sortedScores.get(i) : 0;
            String rank = (i + 1) + "";
            
            // Thêm medal cho Top 3
            if (i == 0) rank = "🥇 " + rank;  
            else if (i == 1) rank = "🥈 " + rank;
            else if (i == 2) rank = "🥉 " + rank;
            
            String rating = "";
            
            if (score == 0) {
                rating = "---";
            } else if (score >= 100) {
                rating = "⭐⭐⭐⭐⭐ SIÊU SAO";
            } else if (score >= 80) {
                rating = "⭐⭐⭐⭐ VÔ CÙNG TỐT";
            } else if (score >= 60) {
                rating = "⭐⭐⭐ TỐT";
            } else if (score >= 40) {
                rating = "⭐⭐ TRUNG BÌNH";
            } else if (score > 0) {
                rating = "⭐ CHƯA TỐT";
            }
            
            data[i][0] = rank;
            data[i][1] = score;
            data[i][2] = rating;
        }
        
        // Tạo Model và Table
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.BOLD, 18));
        table.setRowHeight(50);
        table.setBackground(new Color(50, 205, 50));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(34, 139, 34));
        table.setShowGrid(true);
        table.setSelectionBackground(new Color(100, 200, 100));
        table.setSelectionForeground(Color.WHITE);
        
        // Styling header
        table.getTableHeader().setBackground(new Color(34, 139, 34));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
        table.getTableHeader().setPreferredSize(new Dimension(0, 50));
        
        // Custom renderer cho từng hàng - áp dụng gradient màu
        DefaultTableCellRenderer gradientRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    c.setBackground(new Color(100, 200, 100));
                } else {
                    // Gradient: Top 3 sáng hơn, phía dưới tối hơn
                    if (row < 3) {
                        c.setBackground(new Color(100, 220, 100));
                    } else if (row < 6) {
                        c.setBackground(new Color(80, 200, 80));
                    } else {
                        c.setBackground(new Color(60, 180, 60));
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
        scrollPane.setBackground(new Color(34, 139, 34));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 4));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createStatsPanel(ArrayList<Integer> scores) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(20, 100, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        // Tính toán thống kê
        ArrayList<Integer> sortedScores = new ArrayList<>(scores);
        sortedScores.sort((a, b) -> Integer.compare(b, a));
        
        int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
        int maxScore = scores.isEmpty() ? 0 : sortedScores.get(0);
        int minScore = scores.isEmpty() ? 0 : scores.stream().mapToInt(Integer::intValue).min().orElse(0);
        double avgScore = scores.isEmpty() ? 0 : (double) totalScore / scores.size();
        int gameCount = scores.size();
        
        // Tạo các label thống kê
        JLabel statsLabel = new JLabel(
            String.format("📊 CẬP NHẬT | Cao nhất: %d | Thấp nhất: %d | Trung bình: %.1f | Tổng lượt: %d | Tổng điểm: %d",
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
