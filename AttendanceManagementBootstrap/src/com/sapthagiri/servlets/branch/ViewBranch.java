package com.sapthagiri.servlets.branch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.BranchDAO;
import com.sapthagiri.model.Branch;
@WebServlet("/ViewBranch")
public class ViewBranch extends HttpServlet {
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
		out.print("<h1>View Branch</h1>");
	
		List<Branch> list=BranchDAO.getAllRecords();
		out.println("<table class='table table-bordered table-striped'>");
		out.print("<tr><th>Branch Id</th><th>Branch Name</th><th>Branch Description</th><th>Edit</th><th>Delete</th>");
		for(Branch bean:list){
			out.print("<tr><td>"+bean.getBranchId()+"</td><td>"+bean.getBranchName()+"</td><td>"+bean.getBranchDescription()+"</td><td><a href='EditBranchForm?id="+bean.getBranchId()+"'>Edit</a></td><td><a href='DeleteBranch?id="+bean.getBranchId()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
			
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
}
