package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.User;
@WebServlet("/ViewFaculty")
public class ViewFaculty extends HttpServlet {
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
	
		List<User> list=UserDAO.getAllRecords();
		out.println("<table class='table table-bordered table-striped'>");
		out.print("<tr><th>User Id</th><th>User Name</th><th>Email Id</th><th>Birth Date</th><th>Gender</th><th>Mobile No</th><th>Edit</th><th>Delete</th>");
		for(User bean:list){
			out.print("<tr><td>"+bean.getUserId()+"</td><td>"+bean.getUserName()+"</td><td>"+bean.getUserLogin()+"</td><td>"+bean.getUserDob()+"</td><td>"+bean.getUserGender()+"</td><td>"+bean.getUserMobileNo()+"</td><td><a href='EditFacultyForm?id="+bean.getUserId()+"'>Edit</a></td><td><a href='DeleteFaculty?id="+bean.getUserId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
			
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}
