package com.sapthagiri.servlets.attendance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;
import com.sapthagiri.model.Subject;

@WebServlet("/AddAttendance")
public class AddAttendance extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Attendance Fill</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navfaculty.html").include(request, response);
		out.println("<div class='container'>");
		
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		Date attendanceDate = Date.valueOf(request.getParameter("attendanceDate"));
		Subject attendanceSubject = SubjectDAO.getRecordById(subjectId);
		List<Student> list=StudentDAO.getRecordsBySubject(attendanceSubject);
		HttpSession session=request.getSession();
		session.setAttribute("subjectId",subjectId);
		session.setAttribute("attendanceDate",attendanceDate);
		session.setAttribute("semester",attendanceSubject.getSemester());
		session.setAttribute("studentList",list);
		
		out.println("<form action='SaveAttendance' method='POST'>");
		out.print("<h1>Attendance Student Form</h1>");
		out.println("<table id='attendanceTable' class='table table-bordered table-striped'>");
		out.print("<tr><th>Student Rollno</th><th>Student Name</th><th>Absent/Present</th><th>Attendance Date</th>");
		for(Student bean:list){
		out.print("<tr><td>"+bean.getStudentRollNo()+"</td><td>"+bean.getStudentName()+"</td><td><input type='radio' id='absent' name="+bean.getStudentRollNo()+" value='0'><label for='absent'> Absent </label>&nbsp;<input type='radio' id='present' name='"+bean.getStudentRollNo()+"' value='1'><label for='present'>  Present  </label></td><td>"+attendanceDate+"</td></tr>");
		}
		out.println("</table>");
		out.println("<input type='submit' value='Save Attendance' />");
		out.println("</form>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
