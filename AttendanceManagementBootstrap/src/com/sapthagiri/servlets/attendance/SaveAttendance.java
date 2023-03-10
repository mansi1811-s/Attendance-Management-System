package com.sapthagiri.servlets.attendance;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.AssignFacultyDAO;
import com.sapthagiri.dao.AttendanceDAO;
import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.Attendance;
import com.sapthagiri.model.Student;
import com.sapthagiri.model.Subject;

@WebServlet("/SaveAttendance")
public class SaveAttendance extends HttpServlet {
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
		
		int subjectId = (Integer)request.getSession().getAttribute("subjectId");
		Date attendanceDate = (Date)request.getSession().getAttribute("attendanceDate");
		String semester = (String)request.getSession().getAttribute("semester");
		List<Student> studentList = (List<Student>)request.getSession().getAttribute("studentList");
		List<AssignFaculty> facultyList = AssignFacultyDAO.getFilteredRecords(subjectId, Integer.parseInt(semester));
		int totalAttendanceClass = 0;
		if(facultyList != null && facultyList.size() >0) {
			totalAttendanceClass = facultyList.get(0).getFacultyTotalClasses();
		}
		
		
		Map<String, String[]> tableData = request.getParameterMap();
		
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		
		Map<Integer,Student> studentMap = new HashMap<Integer, Student>();
		for(Student student : studentList) {
			studentMap.put(student.getStudentId(), student);
			Attendance attendance = new Attendance();
			attendance.setAttendanceSemester(semester);
			attendance.setAttendanceDate(attendanceDate);
			attendance.setTotalAttendanceClass(totalAttendanceClass);
			Subject subject = new Subject();
			subject.setSubjectId(subjectId);
			attendance.setSubject(subject);
			attendance.setStudent(student);
			if(tableData.containsKey(student.getStudentRollNo())) {
				attendance.setAttendanceStatus(Integer.valueOf(tableData.get(student.getStudentRollNo())[0]));
			}else {
				attendance.setAttendanceStatus(0);
			}
			attendanceList.add(attendance);
		}
		
		boolean status = AttendanceDAO.save(attendanceList);
		if(status) {
			out.print("<h1>View Attendance Student Form</h1>");
			out.println("<p>Attendance List is saved successfully!</p>");
			out.println("<table id='attendanceTable' class='table table-bordered table-striped'>");
			out.print("<tr><th>Student Rollno</th><th>Student Name</th><th>Attendance Status</th><th>Attendance Date</th>");
			for(Attendance bean:attendanceList){
				Student viewStudent = studentMap.get(bean.getStudent().getStudentId());
				out.print("<tr><td>"+viewStudent.getStudentRollNo()+"</td><td>"+viewStudent.getStudentName()+"</td><td>"+(bean.getAttendanceStatus() == 1 ? "Present": "Absent")+"</td><td>"+bean.getAttendanceDate()+"</td></tr>");
			}
			out.println("</table>");
		}
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
