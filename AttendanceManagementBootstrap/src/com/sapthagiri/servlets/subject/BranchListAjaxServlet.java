package com.sapthagiri.servlets.subject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sapthagiri.dao.BranchDAO;
import com.sapthagiri.model.Branch;
 
@WebServlet("/listbranchajax")
public class BranchListAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        	List<Branch> branchList = BranchDAO.getAllRecords();
        	System.out.println("Ajax invoked fetched records "+ branchList.size());
            String json = new Gson().toJson(branchList);
 
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
 
    }
 
}