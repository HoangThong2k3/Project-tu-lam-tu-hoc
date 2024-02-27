package services;

import models.student.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClassService {

    // JDBC variables for opening, closing, and managing the connection

    // Constructor
    public ClassService() {
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

    // Method to add a new class
    public void addClass(Class studentClass) {
        try {
            String query = "INSERT INTO class (id, name, room, teacher) VALUES (?, ?, ?, ?)";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentClass.getId());
                preparedStatement.setString(2, studentClass.getName());
                preparedStatement.setString(3, studentClass.getRoom());
                preparedStatement.setString(4, studentClass.getTeacher());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClass(Class studentClass) {
        try {
            String query = "UPDATE class SET name = ?, room = ?, teacher = ? WHERE id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentClass.getName());
                preparedStatement.setString(2, studentClass.getRoom());
                preparedStatement.setString(3, studentClass.getTeacher());
                preparedStatement.setInt(4, studentClass.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClass(int id) {
        try {
            String query = "DELETE FROM class WHERE id = ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for a class by ID
    public Class searchClass(int classId) {
        try {
            String query = "SELECT * FROM class WHERE id=?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, classId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String room = resultSet.getString("room");
                        String teacher = resultSet.getString("teacher");

                        return new Class(id, name, room, teacher);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to search for a class by keyword
    public List<Class> searchClassByKeyword(String keyword) {
        try {
            String query = "SELECT * FROM class WHERE name LIKE ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + keyword + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return getClassesFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method getClassesFromResultSet
    public List<Class> getClassesFromResultSet(ResultSet resultSet) throws SQLException {
        List<Class> classes = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String room = resultSet.getString("room");
            String teacher = resultSet.getString("teacher");

            classes.add(new Class(id, name, room, teacher));
        }
        return classes;
    }
}
