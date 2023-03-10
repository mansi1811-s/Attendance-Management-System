package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.AssignFacultyDAO;
import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.User;

@WebServlet("/EditAssignFacultyForm")
public class EditAssignFacultyForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		AssignFaculty faculty = AssignFacultyDAO.getRecordById(id);
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Assign Faculty</title>");
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
		
		out.print("<h1>Edit Assign Faculty Form</h1>");
		out.print("<form action='EditAssignFaculty' method='post'>");
		out.print("<table>");
		out.print("<tr><td><input type='hidden' name='id' value='"+faculty.getFacultyId()+"' /></td></tr>");
		out.print("<tr><td>Faculty Name::</td><td><select id='facultyId' name='facultyId' value='"+faculty.getUser().getUserId()+"'/></td></tr>");
		out.print("<tr><td>Subject Name:</td><td><select id='subjectId' name='subjectId' value='"+faculty.getSubject().getSubjectId()+"'/></td></tr>");
		out.print("<tr><td>Total Class:</td><td><input type='number' name='facultyTotalClasses' required value='"+faculty.getFacultyTotalClasses()+"'/></td></tr>");
		out.print("<tr><td colspan='2' align='center'><input type='submit' value='Update Assign Faculty' class='btn btn-default'/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		
		out.close();
	}

}
