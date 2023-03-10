package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.AssignFacultyDAO;
import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;
import com.sapthagiri.model.Subject;
import com.sapthagiri.model.User;
import com.sapthagiri.util.Constants;
@WebServlet("/EditAssignFaculty")
public class EditAssignFaculty extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		int userId = Integer.parseInt(request.getParameter("facultyId"));
	 	int subjectId = Integer.parseInt(request.getParameter("subjectId"));
	 	int totalClasses = Integer.parseInt(request.getParameter("facultyTotalClasses"));
	 	Subject subject = SubjectDAO.getRecordById(subjectId);
	 	User user = new User();
	 	user.setUserId(userId);
	 	Branch branch = new Branch();
	 	branch.setBranchId(subject.getBranch().getBranchId());
	 	
	 	AssignFaculty faculty = new AssignFaculty();
	 	faculty.setFacultyId(id);
		faculty.setFacultySemster(subject.getSemester());
		faculty.setFacultyTotalClasses(totalClasses);
		faculty.setUser(user);
		faculty.setBranch(branch);
		faculty.setSubject(subject);
		
		AssignFacultyDAO.update(faculty);
		response.sendRedirect("ViewAssignFaculty");
		
		out.close();
	}

}
