package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.AssignFacultyDAO;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Subject;
import com.sapthagiri.model.User;

@WebServlet("/AddAssignFaculty")
public class AddAssignFaculty extends HttpServlet {
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
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		int userId = Integer.parseInt(request.getParameter("facultyId"));
	 	int subjectId = Integer.parseInt(request.getParameter("subjectId"));
	 	int totalClasses = Integer.parseInt(request.getParameter("facultyTotalClasses"));
	 	Subject subject = SubjectDAO.getRecordById(subjectId);
	 	User user = new User();
	 	user.setUserId(userId);
	 	Branch branch = new Branch();
	 	branch.setBranchId(subject.getBranch().getBranchId());
	 	
	 	AssignFaculty faculty = new AssignFaculty();
		faculty.setFacultySemster(subject.getSemester());
		faculty.setFacultyTotalClasses(totalClasses);
		faculty.setUser(user);
		faculty.setBranch(branch);
		faculty.setSubject(subject);
		
		int status=AssignFacultyDAO.save(faculty);
		
		//out.print("<h1>Add Assign Faculty Form</h1>");
		out.println("<p>Assign Faculty is added successfully!</p>");
		request.getRequestDispatcher("AddAssignFacultyForm.html").include(request, response);
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
