package services;

public class ServiceSingleton {

    private static StudentService studentServiceInstance;
    private static ClassService classServiceInstance;

    private ServiceSingleton() {
        // private constructor to prevent instantiation
    }

    public static synchronized StudentService getStudentServiceInstance() {
        if (studentServiceInstance == null) {
            studentServiceInstance = new StudentService();
        }
        return studentServiceInstance;
    }

    public static synchronized ClassService getClassServiceInstance() {
        if (classServiceInstance == null) {
            classServiceInstance = new ClassService();
        }
        return classServiceInstance;
    }
}
