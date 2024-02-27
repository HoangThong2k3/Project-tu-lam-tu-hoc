package test_service;

import org.junit.jupiter.api.Test;
import services.ClassService;
import services.ServiceSingleton;

public class TestClassService {
    private ClassService classService = ServiceSingleton.getClassServiceInstance();

    // Test addClass method
    @Test
    public void testAddClass() {
        models.student.Class studentClass = new models.student.Class();
        studentClass.setName("IOT101");
        studentClass.setRoom("415");
        studentClass.setTeacher("Nguyen Van Vinh");
        classService.addClass(studentClass);
    }

    // Test updateClass method
    @Test
    public void testUpdateClass() {
        models.student.Class studentClass = new models.student.Class();
        studentClass.setId(1);
        studentClass.setName("IOT101");
        studentClass.setRoom("415");
        studentClass.setTeacher("Truong Ngoc Hung");
        classService.updateClass(studentClass);
    }

    // Test deleteClass method
    @Test
    public void testDeleteClass() {
        classService.deleteClass(1);

    }

    //Test add 10 classes
    @Test
    public void testAdd10Classes() {
        for (int i = 0; i < 10; i++) {
            models.student.Class studentClass = new models.student.Class();
            studentClass.setName("IOT" + i);
            int room = 415;
            studentClass.setRoom(415+i+"");
            studentClass.setTeacher("Nguyen Van Vinh");
            classService.addClass(studentClass);
        }
    }

    //Test search class by ID
    @Test
    public void testSearchClass() {
        models.student.Class studentClass = classService.searchClass(8);
        System.out.println(studentClass);
    }

    //Test get all classes
    @Test
    public void testGetAllClasses() {
        System.out.println(classService.searchClassByKeyword("IOT"));
    }
}
