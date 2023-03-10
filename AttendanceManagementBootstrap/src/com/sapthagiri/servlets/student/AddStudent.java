package com.sapthagiri.servlets.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;

@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Branch Added</title>");
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
		
		String branchId = request.getParameter("branchId");
	 	String semester = request.getParameter("semester");
	 	String studentRollNo = request.getParameter("studentRollNo");
	 	String studentName = request.getParameter("studentName");
	 	String studentEmailId = request.getParameter("studentEmailId");
	 	String studentGender = request.getParameter("studentGender");
	 	String studentBirthDate = request.getParameter("studentBirthDate");
	 	String studentMobile = request.getParameter("studentMobile");
	 	String fatherEmailId = request.getParameter("studentFatherEmailId");
		String fatherMobileNo = request.getParameter("studentFatherMobile");
	 	
		Student student = new Student();
		student.setStudentRollNo(studentRollNo);
		student.setStudentName(studentName);
		student.setStudentDob(Date.valueOf(studentBirthDate));
		student.setStudentMobileNo(studentMobile);
		student.setStudentGender(studentGender);
		student.setStudentSemester(semester);
		student.setStudentEmailId(studentEmailId);
		student.setFatherEmailId(fatherEmailId);
		student.setFatherMobileNo(fatherMobileNo);
		
		Branch branch = new Branch();
		branch.setBranchId(Integer.parseInt(branchId));
		student.setBranch(branch);
		
		
		int status=StudentDAO.save(student);
		
		//out.print("<h1>Add Student Form</h1>");
		out.println("<p>Student is added successfully!</p>");
		request.getRequestDispatcher("AddStudentForm.html").include(request, response);
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
