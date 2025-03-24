package org.example.database;

import org.example.model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:h2:./students_db";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String SCHEMA_FILE = "src/main/resources/database/schema.sql";

    public static void initDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = readSqlFile(SCHEMA_FILE);
            statement.execute(sql);
            System.out.println("База даних ініціалізована з файлу schema.sql.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String readSqlFile(String filePath) throws IOException {
        StringBuilder sql = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
        }
        return sql.toString();
    }

    public static List<Student> getStudentsBornInMonth(int month) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE MONTH(birth_date) = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, month);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getDate("birth_date").toString(),
                        resultSet.getString("record_book_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}

