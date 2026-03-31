String[] columnNames = {"Hạng", "Tên người chơi", "Điểm số"};
JTable highscoreTable = new JTable(data, columnNames);
JScrollPane scrollPane = new JScrollPane(highscoreTable);
add(scrollPane, BorderLayout.CENTER);
