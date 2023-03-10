package com.sapthagiri.servlets.attendance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sapthagiri.dao.BranchDAO;
import com.sapthagiri.model.Branch;
@WebServlet("/SearchAttendanceForm")
public class SearchAttendanceForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Search Attendance</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("<script src=\"resources/jquery.min.js\"></script>");
		out.println("<script>");
		//out.println("$(document).on(\"click\", \"#subjectId\", function() {");
		out.println("$(document).ready(function() {");
		out.println("$.get(\"listsubjectajax\", function(responseJson) {");
		out.println("var $select = $(\"#subjectId\");");
		out.println("$.each(responseJson, function(index, subject) {");
		out.println("var tempId = subject.subjectId ");
		out.println("$(\"<option>\").val(tempId).text(subject.subjectName +' - '+ subject.semester + 'Sem'+ ' - ' + subject.branch.branchName).appendTo($select);");
		out.println(" });");
		out.println(" });");
		out.println("$.get(\"listbranchajax\", function(responseJson) {");
		out.println("var $select = $(\"#branchId\");");
		out.println("$.each(responseJson, function(index, branch) {");
		out.println("var tempId = branch.branchId");
		out.println("$(\"<option>\").val(tempId).text(branch.branchName).appendTo($select);");
		out.println(" });");
		out.println(" });");
		out.println(" });");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navfaculty.html").include(request, response);
		out.println("<div class='container'>");
		
		
		HttpSession session=request.getSession(false);
		
		if(session==null||session.getAttribute("faculty")==null){
			out.println("<h1>Not Faculty!</h1>");
			request.getRequestDispatcher("FacultyLoginForm.html").include(request, response);
		}else{
			request.getRequestDispatcher("SearchAttendanceForm.html").include(request, response);
		}
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
