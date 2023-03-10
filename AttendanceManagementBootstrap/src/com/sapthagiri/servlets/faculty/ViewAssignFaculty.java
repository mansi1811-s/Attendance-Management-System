package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.AssignFacultyDAO;
import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.User;
@WebServlet("/ViewAssignFaculty")
public class ViewAssignFaculty extends HttpServlet {
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
		out.print("<h1>View Faculty</h1>");
	
		List<AssignFaculty> list=AssignFacultyDAO.getAllRecords();
		out.println("<table class='table table-bordered table-striped'>");
		out.print("<tr><th>Id</th><th>Faculty Name</th><th>Branch Name</th><th>Subject Name</th><th>Semester</th><th>Total Class</th><th>Edit</th><th>Delete</th>");
		for(AssignFaculty bean:list){
			out.print("<tr><td>"+bean.getFacultyId()+"</td><td>"+bean.getUser().getUserName()+"</td><td>"+bean.getBranch().getBranchName()+"</td><td>"+bean.getSubject().getSubjectName()+"</td><td>"+bean.getFacultySemster()+"</td><td>"+bean.getFacultyTotalClasses()+"</td><td><a href='EditAssignFacultyForm?id="+bean.getFacultyId()+"'>Edit</a></td><td><a href='DeleteAssignFaculty?id="+bean.getFacultyId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
			
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}
