package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // Thông tin kết nối
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "0matkhau";

        // Biến để lưu trữ kết nối
        Connection connection = null;

        try {
            // Tạo kết nối
            connection = DriverManager.getConnection(url, username, password);

            // Kiểm tra kết nối thành công hay không
            if (connection != null) {
                System.out.println("Kết nối thành công đến MySQL!");
            } else {
                System.out.println("Không thể kết nối đến MySQL!");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi kết nối đến MySQL: " + e.getMessage());
        } finally {
            try {
                // Đóng kết nối sau khi sử dụng
                if (connection != null) {
                    connection.close();
                    System.out.println("Đã đóng kết nối đến MySQL!");
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
}