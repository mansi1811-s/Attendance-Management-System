package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.User;

@WebServlet("/EditFacultyForm")
public class EditFacultyForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		User user = UserDAO.getRecordById(id);
		
		
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
		
		out.print("<h1>Edit Faculty Form</h1>");
		out.print("<form action='EditFaculty' method='post'>");
		out.print("<table>");
		out.print("<tr><td><input type='hidden' name='id' value='"+user.getUserId()+"' /></td></tr>");
		out.print("<tr><td>Name:</td><td><input type='text' name='userName' required value='"+user.getUserName()+"'/></td></tr>");
		out.print("<tr><td>Login Id:</td><td><input type='text' name='userLogin' required value='"+user.getUserLogin()+"'/></td></tr>");
		out.print("<tr><td>Password:</td><td><input type='password' name='userPassword' required value='"+user.getUserPassword()+"'/></td></tr>");
		out.print("<tr><td>Gender:</td><td><input type='radio' name='userGender' value='Male' "+ (user.getUserGender().equalsIgnoreCase("Male")? "checked" : "")+ "/>Male <input type='radio' name='userGender' value='Female' "+(user.getUserGender().equalsIgnoreCase("Female")? "checked" : "") + "/>Female</td></tr>");
		out.print("<tr><td>Birth Date:</td><td><input type='date' id='userDob' name='userDob' required value='"+user.getUserDob()+"'/></td></tr>");
		out.print("<tr><td>Mobile:</td><td><input type='number' name='userMobileNo' required value='"+user.getUserMobileNo()+"'/></td></tr>");
		out.print("<tr><td colspan='2' align='center'><input type='submit' value='Update Faculty' class='btn btn-default'/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		
		out.close();
	}

}
