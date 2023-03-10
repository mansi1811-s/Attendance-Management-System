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

@WebServlet("/EditBranchForm")
public class EditBranchForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		Branch branch = BranchDAO.getRecordById(id);
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Branch</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		out.print("<h1>Edit Branch Form</h1>");
		out.print("<form action='EditBranch' method='post'>");
		out.print("<table>");
		out.print("<tr><td><input type='hidden' name='id' value='"+branch.getBranchId()+"' /></td></tr>");
		out.print("<tr><td>Branch Name:</td><td><input type='text' name='branchName' value='"+branch.getBranchName()+"'/></td></tr>");
		out.print("<tr><td>Branch Description:</td><td><textarea name='branchDescription' style='width:300px;height:100px;'>"+branch.getBranchDescription()+"</textarea></td></tr>");
		out.print("<tr><td colspan='2' align='center'><input type='submit' value='Update Branch' class='btn btn-default'/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		
		out.close();
	}

}
