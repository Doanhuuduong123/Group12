package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MenuPanel extends JPanel {
    private JButton btnPlay, btnHighscore, btnExit;
    
    public MenuPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(30, 30, 30));
        
        JPanel titlePanel = createTitlePanel();
        
        JPanel buttonPanel = createButtonPanel();
        
        JPanel footerPanel = createFooterPanel();
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                GradientPaint gradient = new GradientPaint(0, 0, new Color(45, 45, 45),
                        0, getHeight(), new Color(20, 20, 20));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                GradientPaint highlight = new GradientPaint(0, 0, new Color(255, 255, 255, 50),
                        0, getHeight() / 4, new Color(255, 255, 255, 0));
                g2d.setPaint(highlight);
                g2d.fillRect(0, 0, getWidth(), getHeight() / 3);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));
        
        JLabel topDecor = new JLabel("=== SNAKE ===");
        topDecor.setFont(new Font("Arial", Font.BOLD, 24));
        topDecor.setForeground(Color.WHITE);
        topDecor.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel title = new JLabel("SNAKE GAME");
        title.setFont(new Font("Arial", Font.BOLD, 64));
        title.setForeground(new Color(255, 215, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel slogan = new JLabel("CHINH PHUC DIEM SO CAO");
        slogan.setFont(new Font("Arial", Font.BOLD, 20));
        slogan.setForeground(new Color(200, 200, 200));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel desc = new JLabel("An moi de lon nhanh va tranh vat can");
        desc.setFont(new Font("Arial", Font.PLAIN, 18));
        desc.setForeground(new Color(255, 255, 200));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(topDecor);
        panel.add(Box.createVerticalStrut(5));
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(slogan);
        panel.add(Box.createVerticalStrut(8));
        panel.add(desc);
        panel.add(Box.createVerticalStrut(20));
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 100, 20, 100);
        gbc.ipady = 25;
        
        btnPlay = createModernButton("PLAY GAME", new Color(60, 120, 60));
        btnHighscore = createModernButton("HIGHSCORE", new Color(70, 70, 70));
        btnExit = createModernButton("EXIT", new Color(150, 50, 50));
        
        panel.add(btnPlay, gbc);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnHighscore, gbc);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnExit, gbc);
        
        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel info = new JLabel("DIEU KHIEN: Su dung cac phim mui ten");
        info.setFont(new Font("Arial", Font.BOLD, 14));
        info.setForeground(Color.LIGHT_GRAY);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel score = new JLabel("Moi con moi an duoc se tang diem so cua ban");
        score.setFont(new Font("Arial", Font.ITALIC, 13));
        score.setForeground(Color.WHITE);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(info);
        panel.add(Box.createVerticalStrut(5));
        panel.add(score);
        
        return panel;
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color fillColor = bgColor;
                Color borderColor = Color.WHITE;
                
                if (getModel().isPressed()) {
                    fillColor = new Color(
                        Math.max(bgColor.getRed() - 50, 0),
                        Math.max(bgColor.getGreen() - 50, 0),
                        Math.max(bgColor.getBlue() - 50, 0)
                    );
                    borderColor = new Color(200, 200, 200);
                } else if (getModel().isRollover()) {
                    fillColor = new Color(
                        Math.min(bgColor.getRed() + 30, 255),
                        Math.min(bgColor.getGreen() + 30, 255),
                        Math.min(bgColor.getBlue() + 30, 255)
                    );
                    borderColor = new Color(255, 255, 100);
                }

                g2d.setColor(new Color(0, 0, 0, 80));
                g2d.fillRoundRect(2, 2, getWidth()-2, getHeight()-2, 20, 20);

                GradientPaint gradient = new GradientPaint(
                    0, 0, fillColor,
                    0, getHeight(), new Color(
                        Math.max(fillColor.getRed() - 20, 0),
                        Math.max(fillColor.getGreen() - 20, 0),
                        Math.max(fillColor.getBlue() - 20, 0)
                    )
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 255, 255, 100));
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight()/3, 20, 20);
                }

                g2d.setColor(borderColor);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                
                super.paintComponent(g);
            }
        };
        
        btn.setFont(new Font("Arial Black", Font.BOLD, 24));
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(350, 60));
        
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

    public JButton getBtnPlay() { return btnPlay; }
    public JButton getBtnHighscore() { return btnHighscore; }
    public JButton getBtnExit() { return btnExit; }
    
    public void addPlayListener(ActionListener l) { btnPlay.addActionListener(l); }
    public void addHighscoreListener(ActionListener l) { btnHighscore.addActionListener(l); }
    public void addExitListener(ActionListener l) { btnExit.addActionListener(l); }
}