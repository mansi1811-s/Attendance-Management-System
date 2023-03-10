package com.sapthagiri.servlets.faculty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.dao.UserDAO;
import com.sapthagiri.model.Subject;
import com.sapthagiri.model.User;
import com.sapthagiri.util.Constants;
 
@WebServlet("/listuserajax")
public class UserListAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        	List<User> userList = UserDAO.getAllRoleBasedRecords(Constants.USER_FACULTY_ROLE);
        	System.out.println("Ajax invoked fetched records "+ userList.size());
            String json = new Gson().toJson(userList);
 
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
 
    }
 
}