package com.sapthagiri.servlets.branch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.BranchDAO;
import com.sapthagiri.model.Branch;

@WebServlet("/AddBranch")
public class AddBranch extends HttpServlet {
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
		
		String branchName = request.getParameter("branchName");
	 	String branchDescription = request.getParameter("branchDescription");
		
		Branch branch =new Branch(branchName, branchDescription);
		int status=BranchDAO.save(branch);
		
		out.print("<h1>Add Branch Form</h1>");
		out.println("<p>Branch is added successfully!</p>");
		request.getRequestDispatcher("AddBranchForm.html").include(request, response);
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
