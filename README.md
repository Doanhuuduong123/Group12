# 🐍 Đồ Án Game Rắn Săn Mồi (Snake Game)
**Bài tập lớn cuối kỳ môn Lập trình Java - Nhóm 12** 👥

## 👥 Thông tin nhóm (Team Members)

| STT | Họ và Tên | Mã Sinh Viên | Vai trò / Nhiệm vụ | Link GitHub |
| :-- | :--- | :--- | :--- | :--- |
| 1 | ** Đoàn Hữu Dương** | MSV:3120225034 25CNTT3 | **Phần 1**: Code Controller, Xử lý va chạm, Game Loop | [GitHub] (https://github.com/Doanhuuduong123) |
| 2 | ** Phan Trần Mai Uyên** (Nhóm trưởng) |MSV:3120225174 25CNTT3 | **Phần 2**: Code View, Thiết kế giao diện Menu, Đồ họa Asset | [GitHub] (https://github.com/DayunJ1410) |
| 3 | ** Trần Quốc Trung** | MSV: 3120225167 25CNTT3 | **Phần 3**: Code Model (Snake, Food), Xử lý File I/O | [GitHub] (https://github.com/abachaap) |

## 📝 Giới thiệu dự án (Description)
Dự án này xây dựng trò chơi "Rắn Săn Mồi" kinh điển trên nền tảng Desktop sử dụng ngôn ngữ Java. Trò chơi giúp người dùng giải trí thông qua việc điều khiển chú rắn ăn mồi để tăng điểm số, đồng thời yêu cầu sự khéo léo để tránh va phải tường hoặc thân mình. Ứng dụng tập trung vào việc áp dụng các kiến thức về lập trình hướng đối tượng (OOP) và cấu trúc dữ liệu giải thuật.

## ✨ Các chức năng chính (Features)
- [x] **Điều khiển mượt mà**: Sử dụng phím mũi tên để điều hướng rắn di chuyển.
- [x] **Logic va chạm**: Xử lý va chạm chính xác với tường, mồi và bản thân rắn.
- [x] **Độ khó tăng dần**: Tốc độ di chuyển của rắn tăng lên sau mỗi 5 lần ăn thành công.
- [x] **Lưu trữ kỷ lục**: Hệ thống lưu và hiển thị điểm cao nhất (Highscore) qua File I/O.
- [x] **Giao diện trực quan**: Sử dụng Java Swing và Assets hình ảnh sinh động.
- [x] **Hiệu ứng hiện đại**: Hiệu ứng hào quang (Glow) cho rắn và mồi đặc biệt.

## 💻 Công nghệ & Thư viện sử dụng (Technologies)
*   **Ngôn ngữ**: Java (JDK 17+)
*   **Giao diện**: Java Swing, AWT
*   **Lưu trữ**: File Text (.txt) để lưu trữ điểm số.
*   **Công cụ**: Git, GitHub, VS Code / IntelliJ IDEA.

## 📂 Cấu trúc thư mục (Project Structure)
Mã nguồn được tổ chức chặt chẽ theo mô hình **MVC** (Model - View - Controller - Utils):

```plaintext
📦 src
   ┣ 📂 model       # Snake.java, Food.java, Wall.java, GameData.java, GameObject.java
   ┣ 📂 view        # MainFrame.java, GamePanel.java, MenuPanel.java, ShowHighscorePanel.java
   ┣ 📂 controller  # GameLoop.java, InputHandler.java
   ┣ 📂 utils       # AssetManager.java, ImageUtils.java, ExceptionHandler.java
   ┗ 📜 Main.java   # Entry point khoi chay ung dung
```

## 🚀 Hướng dẫn cài đặt và chạy (Installation)
1. **Clone repository** về máy cá nhân:
   ```bash
   git clone https://github.com/Doanhuuduong123/Group12.git
   ```
2. **Yêu cầu hệ thống**: Đảm bảo máy tính đã cài đặt JDK 17 trở lên.
3. **Chạy ứng dụng**:
   - Mở thư mục dự án bằng IDE của bạn.
   - Kiểm tra các file hình ảnh trong thư mục `asset/` đã nằm đúng vị trí.
   - Chạy file `src/Main.java` để bắt đầu trò chơi.

## 📸 Ảnh chụp màn hình (Screenshots)
*Dưới đây là giao diện minh họa của trò chơi:*

[Giao diện Menu Game] https://drive.google.com/file/d/1MScwdcii85BYbQGkD2lhA75UDdXPGM1l/view?usp=sharing

[Giao diện Game] https://drive.google.com/file/d/1yx96RZjDZkK1yq1O69kx-D9TdrEwBhBL/view?usp=sharing
[Game Over] https://drive.google.com/file/d/18YYqxIJtg34A9k1boO35SU86Fl6_2R4t/view?usp=sharing
