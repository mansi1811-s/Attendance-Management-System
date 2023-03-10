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
@WebServlet("/EditBranch")
public class EditBranch extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String branchName = request.getParameter("branchName");
	 	String branchDescription = request.getParameter("branchDescription");
		
		Branch branch = new Branch (id, branchName, branchDescription);
		BranchDAO.update(branch);
		response.sendRedirect("ViewBranch");
		
		out.close();
	}

}
