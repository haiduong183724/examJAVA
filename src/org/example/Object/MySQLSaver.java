package org.example.Object;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLSaver implements DataSaving{
    Connection connection = null;
    List<Animal> animals ;
    @Override
    public void SetUp() throws IOException {
        // Thông tin kết nối
        String url = "jdbc:mysql://localhost:3306/java";
        String username = "root";
        String password = "0matkhau";

        // Biến để lưu trữ kết nối
        try {
            // Tạo kết nối
            connection = DriverManager.getConnection(url, username, password);

            // Kiểm tra kết nối thành công hay không
            if (connection != null) {
                System.out.println("Connect to mysql success!");
            } else {
                System.out.println("Can't connect to mysql!");
            }

        } catch (SQLException e) {
            System.err.println("Connect to mysql error: " + e.getMessage());
        }
    }

    @Override
    public void SaveData(List<Animal> data) throws IOException {
        animals = data;
    }

    @Override
    public void Close() throws IOException {
            try {
                // Đóng kết nối sau khi sử dụng
                if (connection != null) {
                    connection.close();
                    System.out.println("Close connect mysql!");
                }
            } catch (SQLException e) {
                System.err.println("error: " + e.getMessage());
            }
    }

    @Override
    public void run() {
        String sql = "INSERT INTO animal (id, numFoot) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Iterate through the list and insert each element
            for (Animal animal : animals) {
                // Set parameters for the query
                preparedStatement.setInt(1, animal.id);
                preparedStatement.setInt(2, animal.numFoot);
                // Execute the query
                int rowsAffected = preparedStatement.executeUpdate();
                // Check the number of rows affected
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Failed to insert data!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
