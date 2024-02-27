package controllers.student;

import services.StudentService;
import models.student.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentAddController", value = "/student-add")
public class StudentAddController extends HttpServlet {

    StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/student/add.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String studentName = req.getParameter("Name");
        String studentEmail = req.getParameter("Email");
        String studentPhone = req.getParameter("Phone");

        if (studentName != null && !studentName.isEmpty() &&
                studentEmail != null && !studentEmail.isEmpty() &&
                studentPhone != null && !studentPhone.isEmpty()) {

            Student newStudent = new Student();
            newStudent.setName(studentName);
            newStudent.setEmail(studentEmail);
            newStudent.setPhone(studentPhone);

            studentService.addStudent(newStudent);
            System.out.println(newStudent);

            // Chuyển hướng đến trang hiển thị thông tin sinh viên
            resp.sendRedirect("./");
        } else {
            // Nếu dữ liệu không đầy đủ, có thể xử lý theo ý bạn
            resp.sendRedirect( "./student/add");
        }
    }
}


