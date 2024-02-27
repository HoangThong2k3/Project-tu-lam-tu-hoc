package test_service;

import services.ServiceSingleton;
import services.StudentService;
import models.student.Student;
import org.junit.jupiter.api.Test;

public class TestStudentService {
    StudentService studentService = ServiceSingleton.getStudentServiceInstance();

    @Test
    public void testAddStudent(){
        Student student = new Student();
        student.setName("Hoang PHam Thong");
        student.setEmail("thong@gnail.com");
        student.setPhone("0123456789");
        student.setClassId(3);
        studentService.addStudent(student);
    }


}
