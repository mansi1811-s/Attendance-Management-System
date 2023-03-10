package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/AddAssignFacultyForm")
public class AddAssignFacultyForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Add Assign Faculty</title>");
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
		out.println("$(\"<option>\").val(tempId).text(subject.subjectName).appendTo($select);");
		out.println(" });");
		out.println(" });");
		out.println("$.get(\"listuserajax\", function(responseJson) {");
		out.println("var $select = $(\"#facultyId\");");
		out.println("$.each(responseJson, function(index, user) {");
		out.println("var tempId = user.userId");
		out.println("$(\"<option>\").val(tempId).text(user.userName).appendTo($select);");
		out.println(" });");
		out.println(" });");
		out.println(" });");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		
		HttpSession session=request.getSession(false);
		
		if(session==null||session.getAttribute("admin")==null){
			out.println("<h1>Not Admin!</h1>");
			request.getRequestDispatcher("AdminLoginForm.html").include(request, response);
		}else{
			request.getRequestDispatcher("AddAssignFacultyForm.html").include(request, response);
		}
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
