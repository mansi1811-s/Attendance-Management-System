package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;
import com.sapthagiri.model.User;
import com.sapthagiri.util.Constants;
@WebServlet("/EditFaculty")
public class EditFaculty extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String userName = request.getParameter("userName");
	 	String userLogin = request.getParameter("userLogin");
	 	String userPassword = request.getParameter("userPassword");
	 	String userGender = request.getParameter("userGender");
	 	String userDob = request.getParameter("userDob");
	 	String userMobileNo = request.getParameter("userMobileNo");
	 	
		User user = new User();
		user.setUserId(id);;
		user.setUserRoleId(Constants.USER_FACULTY_ROLE);
		user.setUserName(userName);
		user.setUserLogin(userLogin);
		user.setUserPassword(userPassword);
		user.setUserGender(userGender);
		user.setUserDob(Date.valueOf(userDob));
		user.setUserMobileNo(userMobileNo);
		
		UserDAO.update(user);
		response.sendRedirect("ViewFaculty");
		
		out.close();
	}

}
