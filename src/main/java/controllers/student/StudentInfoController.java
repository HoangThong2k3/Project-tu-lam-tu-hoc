package controllers.student;

import services.StudentService;
import models.student.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentInfoController", value = "/student-info")
public class StudentInfoController extends HttpServlet {

    StudentService studentService = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Access student info controller");
        int studentId = Integer.parseInt(req.getParameter("id"));
        System.out.println(studentId);
        Student student = studentService.searchStudent(studentId);
        req.setAttribute("student", student);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/student/student-info.jsp");
        requestDispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
