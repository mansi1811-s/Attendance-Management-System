package com.sapthagiri.servlets.attendance;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sapthagiri.dao.SubjectDAO;
import com.sapthagiri.model.Subject;
 
@WebServlet("/facultylistsubjectajax")
public class FacultySubjectListAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    		int facultyId = (Integer)request.getSession().getAttribute("facultyId");
        	List<Subject> subjectList = SubjectDAO.getFilteredRecords(facultyId);
        	System.out.println("Ajax invoked fetched records "+ subjectList.size());
            String json = new Gson().toJson(subjectList);
 
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
 
    }
 
}