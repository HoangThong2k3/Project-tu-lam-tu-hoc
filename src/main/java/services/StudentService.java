package services;

import models.student.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    // JDBC variables for opening, closing, and managing the connection

    // Constructor
    public StudentService() {
    }

    // Tạo kết nối đến cơ sở dữ liệu
    private Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            java.lang.Class.forName(Config.JDBC_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL, Config.USER, Config.PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to add a new student
    public void addStudent(Student student) {
        try {
            String query = "INSERT INTO student (id, name, email, phone, class_id) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, student.getId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3, student.getEmail());
                preparedStatement.setString(4, student.getPhone());
                preparedStatement.setInt(5, student.getClassId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            String query = "UPDATE student SET name = ?, email = ?, phone = ?, class_id = ? WHERE id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getEmail());
                preparedStatement.setString(3, student.getPhone());
                preparedStatement.setInt(4, student.getClassId());
                preparedStatement.setInt(5, student.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try {
            String query = "DELETE FROM student WHERE id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for a student by ID
    public Student searchStudent(int studentId) {
        try {
            String query = "SELECT * FROM student WHERE id=?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("phone");
                        int classId = resultSet.getInt("class_id");

                        return new Student(id, name, email, phone, classId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to search for a student by keyword
    public List<Student> searchStudentByKeyword(String keyword) {
        try {
            String query = "SELECT * FROM student WHERE name LIKE ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + keyword + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return getStudentsFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method getStudentsFromResultSet
    public List<Student> getStudentsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            int classId = resultSet.getInt("class_id");

            students.add(new Student(id, name, email, phone, classId));
        }
        return students;
    }
}
