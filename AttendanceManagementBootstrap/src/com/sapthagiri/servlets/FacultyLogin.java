package com.sapthagiri.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.User;
import com.sapthagiri.util.Constants;
@WebServlet("/FacultyLogin")
public class FacultyLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Admin Panel</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navfaculty.html").include(request, response);
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User facultyUser = UserDAO.validateUserAccount(email, password, Constants.USER_FACULTY_ROLE);
		if(facultyUser != null && facultyUser.getUserId() > 0){
			HttpSession session=request.getSession();
			session.setAttribute("faculty","true");
			session.setAttribute("facultyId",facultyUser.getUserId());
			request.getRequestDispatcher("facultyhome.html").include(request, response);
		}else{
			out.println("<p>Sorry, username or password error!</p>");
			request.getRequestDispatcher("FacultyLoginForm.html").include(request, response);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
 
}
