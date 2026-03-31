package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MenuPanel extends JPanel {
    private JButton btnPlay, btnHighscore, btnExit;
    
    public MenuPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(34, 139, 34));
        
        // ===== PHẦN TIÊU ĐỀ =====
        JPanel titlePanel = createTitlePanel();
        
        // ===== PHẦN SỐC ĐƠNG =====
        JPanel buttonPanel = createButtonPanel();
        
        // ===== PHẦN FOOTER =====
        JPanel footerPanel = createFooterPanel();
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }
    
    // Tạo panel tiêu đề
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Vẽ nền gradient đẹp
                GradientPaint gradient = new GradientPaint(0, 0, new Color(50, 205, 50),
                        0, getHeight(), new Color(34, 139, 34));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        
        // Tiêu đề chính
        JLabel title = new JLabel("GAME RAN AN MOI");
        title.setFont(new Font("Arial", Font.BOLD, 56));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Mô tả game
        JLabel desc = new JLabel("Dieu khien ran, an tao, tranh va cham!");
        desc.setFont(new Font("Arial", Font.ITALIC, 18));
        desc.setForeground(new Color(255, 255, 200));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(desc);
        
        return panel;
    }
    
    // Tạo panel nút
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 139, 34));
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 150, 15, 150);
        gbc.ipady = 20;
        
        btnPlay = createModernButton("CHOI MOI", new Color(34, 200, 34));
        btnHighscore = createModernButton("BANG XEP HANG", new Color(255, 165, 0));
        btnExit = createModernButton("THOAT GAME", new Color(220, 50, 50));
        
        panel.add(btnPlay, gbc);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnHighscore, gbc);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnExit, gbc);
        
        return panel;
    }
    
    // Tạo panel footer
    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(20, 100, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel info = new JLabel("Huong dan: ↑↓←→ de dieu khien | Diem = So tao an duoc");
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        info.setForeground(Color.WHITE);
        
        panel.add(info);
        return panel;
    }
    
    // Tạo nút hiện đại
    private JButton createModernButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(
                        Math.max(bgColor.getRed() - 40, 0),
                        Math.max(bgColor.getGreen() - 40, 0),
                        Math.max(bgColor.getBlue() - 40, 0)
                    ));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(
                        Math.min(bgColor.getRed() + 40, 255),
                        Math.min(bgColor.getGreen() + 40, 255),
                        Math.min(bgColor.getBlue() + 40, 255)
                    ));
                } else {
                    g2d.setColor(bgColor);
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Border
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 15, 15);
                
                super.paintComponent(g);
            }
        };
        
        btn.setFont(new Font("Arial", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.repaint();
            }
        });
        
        return btn;
    }
    
    // Getter cho nút
    public JButton getBtnPlay() { return btnPlay; }
    public JButton getBtnHighscore() { return btnHighscore; }
    public JButton getBtnExit() { return btnExit; }
    
    // Thêm listener
    public void addPlayListener(ActionListener l) { btnPlay.addActionListener(l); }
    public void addHighscoreListener(ActionListener l) { btnHighscore.addActionListener(l); }
    public void addExitListener(ActionListener l) { btnExit.addActionListener(l); }
}