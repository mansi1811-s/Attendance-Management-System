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

import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Subject;

@WebServlet("/AddSubject")
public class AddSubject extends HttpServlet {
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
		
		String subjectName = request.getParameter("subjectName");
	 	String subjectCode = request.getParameter("subjectCode");
	 	String branchDetails = request.getParameter("branchId");
	 	List<String> branchList = Arrays.asList(branchDetails.split("-"));
	 	String semester = request.getParameter("semester");
	 	
	 	Branch branch = new Branch( Integer.parseInt(branchList.get(0)), branchList.get(1), null);
	 	Subject subject = new Subject();
	 	subject.setSubjectCode(subjectCode);
		subject.setSubjectName(subjectName);
		subject.setSemester(semester);
		subject.setBranch(branch);
		
		int status=SubjectDAO.save(subject);
		
		//out.print("<h1>Add Subject Form</h1>");
		out.println("<p>Subject is added successfully!</p>");
		request.getRequestDispatcher("AddSubjectForm.html").include(request, response);
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
