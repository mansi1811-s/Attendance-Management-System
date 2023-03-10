package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;
import com.sapthagiri.model.Subject;
import com.sapthagiri.model.User;
import com.sapthagiri.util.Constants;

@WebServlet("/AddFaculty")
public class AddFaculty extends HttpServlet {
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
		
		String userName = request.getParameter("userName");
	 	String userLogin = request.getParameter("userLogin");
	 	String userPassword = request.getParameter("userPassword");
	 	String userGender = request.getParameter("userGender");
	 	String userDob = request.getParameter("userDob");
	 	String userMobileNo = request.getParameter("userMobileNo");
	 	
		User user = new User();
		user.setUserRoleId(Constants.USER_FACULTY_ROLE);
		user.setUserName(userName);
		user.setUserLogin(userLogin);
		user.setUserPassword(userPassword);
		user.setUserGender(userGender);
		user.setUserDob(Date.valueOf(userDob));
		user.setUserMobileNo(userMobileNo);
		
		int status=UserDAO.save(user);
		
		out.print("<h1>Add Faculty Form</h1>");
		out.println("<p>Faculty is added successfully!</p>");
		request.getRequestDispatcher("AddFacultyForm.html").include(request, response);
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
