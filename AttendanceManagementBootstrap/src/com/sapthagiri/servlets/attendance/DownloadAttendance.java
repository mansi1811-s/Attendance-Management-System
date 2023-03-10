package com.sapthagiri.servlets.attendance;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.AttendanceDAO;
import com.sapthagiri.model.AttendanceResult;

@WebServlet("/DownloadAttendance")
public class DownloadAttendance extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		String semester = request.getParameter("semester");
		//Date attendanceDate = Date.valueOf(request.getParameter("attendanceDate"));
		String studentRollNo = request.getParameter("studentRollNo");
		
		List<AttendanceResult> list=AttendanceDAO.getBranchSemesterRecords(branchId, semester);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if(list != null && list.size() >0) {
			response.setContentType("text/csv");
		    response.setHeader("Content-Disposition", "attachment; filename="+"Sapthagiri_"+timestamp.getTime()+".csv");
		    
		    OutputStream outputStream = response.getOutputStream();
	        outputStream.write(extractDownloadResult(list).getBytes());
	        outputStream.flush();
	        outputStream.close();

		} else {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
			out.println("<link rel='stylesheet' href='style.css'/>");
			out.println("<title>No Records Attendance</title>");
			out.println("</head>");
			out.println("<body>");
			request.getRequestDispatcher("navfaculty.html").include(request, response);
			out.println("<div class='container'>");
			out.print("<h1>No Record for the matching criteria.</h1>");
			//request.getRequestDispatcher("DownloadAttendanceForm.html").include(request, response);
			out.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
		
	}

	private String  extractDownloadResult(List<AttendanceResult> attendanceResults) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Student Id, Student Name, Subject Name, Semester, Attendance Count, Total Classes, Percentage \n");
		for(AttendanceResult result : attendanceResults) {
			buffer.append(result.getStudentId()+","+result.getStudentName()+","+result.getSubjectName()+","	+ result.getSemster() 
			+","+result.getAttendanceCount()+","+ result.getTotalClasses()+","+result.getSubjectPercentage()+"\n");
		}
		
		return buffer.toString();
	}
}
