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

import com.sapthagiri.dao.AttendanceDAO;
import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.AttendanceResult;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;
import com.sapthagiri.model.Subject;

@WebServlet("/SearchAttendance")
public class SearchAttendance extends HttpServlet {
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
		
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		//int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		String semester = request.getParameter("semester");
		//Date attendanceDate = Date.valueOf(request.getParameter("attendanceDate"));
		//String studentRollNo = request.getParameter("studentRollNo");
		
		List<AttendanceResult> list=AttendanceDAO.getBranchSemesterRecords(branchId, semester);
		
		
		out.print("<h1>Attendance Student Form</h1>");
		if(list != null && list.size() >0) {
			out.println("<table class='table table-bordered table-striped'>");
			out.print("<tr><th>Student Name</th><th>Subject Name</th><th>Attendance Count</th><th>Total Classes</th><th>Percentage</th>");
			for(AttendanceResult bean:list){
				out.print("<tr><td>"+bean.getStudentName()+"</td><td>"+bean.getSubjectName()+"</td><td>"+bean.getAttendanceCount()+"</td><td>"+bean.getTotalClasses()+"</td><td>"+bean.getSubjectPercentage()+"</td></tr>");
			}
			out.println("</table>");
		} else {
			out.print("No Record for the matching criteria.");
		}
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
