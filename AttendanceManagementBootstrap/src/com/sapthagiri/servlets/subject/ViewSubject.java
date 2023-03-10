package com.sapthagiri.servlets.subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.Subject;
@WebServlet("/ViewSubject")
public class ViewSubject extends HttpServlet {
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
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		out.print("<h1>View Subject</h1>");
	
		List<Subject> list=SubjectDAO.getAllRecords();
		out.println("<table class='table table-bordered table-striped'>");
		out.print("<tr><th>Subject Id</th><th>Subject Code</th><th>Subject Name</th><th>Semester</th><th>Branch Name</th><th>Edit</th><th>Delete</th>");
		for(Subject bean:list){
			out.print("<tr><td>"+bean.getSubjectId()+"</td><td>"+bean.getSubjectCode()+"</td><td>"+bean.getSubjectName()+"</td><td>"+bean.getSemester()+"</td><td>"+bean.getBranch().getBranchName()+"</td><td><a href='EditSubjectForm?id="+bean.getSubjectId()+"'>Edit</a></td><td><a href='DeleteSubject?id="+bean.getSubjectId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
			
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}
