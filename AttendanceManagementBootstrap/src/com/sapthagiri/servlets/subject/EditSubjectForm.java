package com.sapthagiri.servlets.subject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.Subject;

@WebServlet("/EditSubjectForm")
public class EditSubjectForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		Subject subject = SubjectDAO.getRecordById(id);
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Branch</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("<script src=\"resources/jquery.min.js\"></script>");
		out.println("<script>");
		//out.println("$(document).on(\"click\", \"#subjectId\", function() {");
		out.println("$(document).ready(function() {");
		out.println("$.get(\"listbranchajax\", function(responseJson) {");
		out.println("var $select = $(\"#branchId\");");
		out.println("$.each(responseJson, function(index, branch) {");
		out.println("var tempId = branch.branchId + '-'+ branch.branchName ");
		out.println("$(\"<option>\").val(tempId).text(branch.branchName).appendTo($select);");
		out.println(" });");
		out.println(" });");
		out.println(" });");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		out.print("<h1>Edit Subject Form</h1>");
		out.print("<form action='EditSubject' method='post' id='subjectForm'>");
		out.print("<table>");
		out.print("<tr><td><input type='hidden' name='id' value='"+subject.getSubjectId()+"' /></td></tr>");
		out.print("<tr><td>Subject Code:</td><td><input type='text' name='subjectCode' value='"+subject.getSubjectCode()+"'/></td></tr>");
		out.print("<tr><td>Subject Name:</td><td><input type='text' name='subjectName' value='"+subject.getSubjectName()+"'/></td></tr>");
		out.print("<tr><td>Branch:</td><td><select id='branchId' name='branchId' value='"+subject.getBranch().getBranchId()+'-'+subject.getBranch().getBranchName()+"'/></td></tr>");
		out.print("<tr><td>Semester:</td><td><select name='semester' value='"+subject.getSemester()+"'>");
		for(int i=1; i<=8; i++) {
			out.print("<option>"+i+"</option>");
		}
		out.print("</select></td></tr>");
		out.print("<tr><td colspan='2' align='center'><input type='submit' value='Update Subject' class='btn btn-default'/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		
		out.close();
	}

}
