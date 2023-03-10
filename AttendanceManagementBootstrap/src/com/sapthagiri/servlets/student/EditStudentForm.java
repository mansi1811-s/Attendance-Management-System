package com.sapthagiri.servlets.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.model.Student;

@WebServlet("/EditStudentForm")
public class EditStudentForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		Student student = StudentDAO.getRecordById(id);
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Student</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("<script src=\"resources/jquery.min.js\"></script>");
		out.println("<script>");
		//out.println("$(document).on(\"click\", \"#subjectId\", function() {");
		out.println("$(document).ready(function() {");
		out.println("$.get(\"listbranchajax\", function(responseJson) {");
		out.println("var $select = $(\"#branchId\");");
		out.println("$.each(responseJson, function(index, branch) {");
		out.println("var tempId = branch.branchId ");
		out.println("$(\"<option>\").val(tempId).text(branch.branchName).appendTo($select);");
		out.println(" });");
		out.println(" });");
		out.println(" });");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		
		HttpSession session=request.getSession(false);
		if(session != null && request.getSession(false).getAttribute("admin") != null) {
			request.getRequestDispatcher("navadmin.html").include(request, response);
		}else if(session != null && request.getSession(false).getAttribute("faculty") != null) {
			request.getRequestDispatcher("navfaculty.html").include(request, response);
		}
		
		out.println("<div class='container'>");
		
		out.print("<h1>Edit Student Form</h1>");
		out.print("<form action='EditStudent' method='post' id='studentForm'>");
		out.print("<table>");
		out.print("<tr><td><input type='hidden' name='id' value='"+student.getStudentId()+"' /></td></tr>");
		
		out.print("<tr><td>Branch Name:</td><td><select id='branchId' name='branchId' value='"+student.getBranch().getBranchId()+"'/></td></tr>");
		out.print("<tr><td>Semester:</td><td><select name='semester' value='"+student.getStudentSemester()+"'>");
		for(int i=1; i<=8; i++) {
			out.print("<option>"+i+"</option>");
		}
		out.print("</select></td></tr>");
		out.print("<tr><td>Student Roll No:</td><td><input type='number' name='studentRollNo' required value='"+student.getStudentRollNo()+"'/></td></tr>");
		out.print("<tr><td>Student Name:</td><td><input type='text' name='studentName' required value='"+student.getStudentName()+"'/></td></tr>");
		out.print("<tr><td>Email Id:</td><td><input type='email' name='studentEmailId' required value='"+student.getStudentEmailId()+"'/></td></tr>");
		out.print("<tr><td>Gender:</td><td><input type='radio' name='studentGender' value='Male' "+ (student.getStudentGender().equalsIgnoreCase("Male")? "checked" : "")+ "/>Male <input type='radio' name='studentGender' value='Female' "+(student.getStudentGender().equalsIgnoreCase("Female")? "checked" : "") + "/>Female</td></tr>");
		out.print("<tr><td>Birth Date:</td><td><input type='date' id='studentBirthDate' name='studentBirthDate' required value='"+student.getStudentDob()+"'/></td></tr>");
		out.print("<tr><td>Mobile:</td><td><input type='number' name='studentMobile' required value='"+student.getStudentMobileNo()+"'/></td></tr>");
		out.print("<tr><td>Father/Guardian Email Id:</td><td><input type='email' name='studentFatherEmailId' required value='"+student.getFatherEmailId()+"'/></td></tr>");
		out.print("<tr><td>Father/Guardian Mobile:</td><td><input type='number' name='studentFatherMobile' required value='"+student.getFatherMobileNo()+"'/></td></tr>");
		out.print("<tr><td colspan='2' align='center'><input type='submit' value='Update Student' class='btn btn-default'/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		
		out.close();
	}

}
