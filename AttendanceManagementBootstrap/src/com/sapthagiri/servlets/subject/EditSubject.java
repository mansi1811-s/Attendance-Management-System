package com.sapthagiri.servlets.subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.BranchDAO;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Subject;
@WebServlet("/EditSubject")
public class EditSubject extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String subjectName = request.getParameter("subjectName");
	 	String subjectCode = request.getParameter("subjectCode");
	 	String branchDetails = request.getParameter("branchId");
	 	List<String> branchList = Arrays.asList(branchDetails.split("-"));
	 	String semester = request.getParameter("semester");
	 	
	 	Branch branch = new Branch( Integer.parseInt(branchList.get(0)), branchList.get(1), null);
	 	Subject subject = new Subject();
	 	subject.setSubjectId(id);
	 	subject.setSubjectCode(subjectCode);
		subject.setSubjectName(subjectName);
		subject.setSemester(semester);
		subject.setBranch(branch);
		
		SubjectDAO.update(subject);
		response.sendRedirect("ViewSubject");
		
		out.close();
	}

}
