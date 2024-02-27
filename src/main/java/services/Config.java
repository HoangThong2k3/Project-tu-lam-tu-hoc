package services;

public enum Config {
    INSTANCE;
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/student-manager";
    public static final String USER = "root";
    public static final String PASS = "123456789";
}
