package view;

import javax.swing.JPanel;
import javax.swing.JButton;

JPanel menuPanel = new JPanel(new GridLayout(3, 1, 10, 10));
menuPanel.add(new JButton("Chơi mới"));
menuPanel.add(new JButton("Bảng xếp hạng"));
menuPanel.add(new JButton("Thoát"));
