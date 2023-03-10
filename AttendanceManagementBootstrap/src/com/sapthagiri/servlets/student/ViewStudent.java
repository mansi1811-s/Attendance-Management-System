package com.sapthagiri.servlets.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.model.Student;
@WebServlet("/ViewStudent")
public class ViewStudent extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>View Branch</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session=request.getSession(false);
		if(session != null && request.getSession(false).getAttribute("admin") != null) {
			request.getRequestDispatcher("navadmin.html").include(request, response);
		}else if(session != null && request.getSession(false).getAttribute("faculty") != null) {
			request.getRequestDispatcher("navfaculty.html").include(request, response);
		}
		
		out.println("<div class='container'>");
		out.print("<h1>View Student</h1>");
	
		List<Student> list=StudentDAO.getAllRecords();
		out.println("<table class='table table-bordered table-striped'>");
		out.print("<tr><th>Student Rollno</th><th>Student Name</th><th>Semester</th><th>Mobile</th><th>Birth Date</th><th>Gender</th><th>Edit</th><th>Delete</th>");
		for(Student bean:list){
			out.print("<tr><td>"+bean.getStudentRollNo()+"</td><td>"+bean.getStudentName()+"</td><td>"+bean.getStudentSemester()+"</td><td>"+bean.getStudentMobileNo()+"</td><td>"+bean.getStudentDob()+"</td><td>"+bean.getStudentGender()+"</td><td><a href='EditStudentForm?id="+bean.getStudentId()+"'>Edit</a></td><td><a href='DeleteStudent?id="+bean.getStudentId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
			
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}
