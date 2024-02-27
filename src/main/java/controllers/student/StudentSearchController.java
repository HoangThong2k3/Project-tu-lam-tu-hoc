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
import java.util.List;

@WebServlet(name = "StudentSearchController", value = "/student-search")
public class StudentSearchController extends HttpServlet {

    StudentService studentService = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Lấy parameter từ url
        //Gồm  param là keyword và email
        String keyword = req.getParameter("keyword");
        List<Student> students = studentService.searchStudentByKeyword(keyword);
        req.setAttribute("students", students);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/student/search.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Lấy parameter từ form vừa submit lên
        //Gồm  param là keyword và email

        String keyword = req.getParameter("keyword");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        List<Student> students = studentService.searchStudentByKeyword(keyword);
        req.setAttribute("students", students);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/student/search.jsp");
        requestDispatcher.forward(req, resp);

    }



}
