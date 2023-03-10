package com.sapthagiri.servlets.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sapthagiri.dao.StudentDAO;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;
@WebServlet("/EditStudent")
public class EditStudent extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String branchId = request.getParameter("branchId");
	 	String semester = request.getParameter("semester");
	 	String studentRollNo = request.getParameter("studentRollNo");
	 	String studentName = request.getParameter("studentName");
	 	String studentEmailId = request.getParameter("studentEmailId");
	 	String studentGender = request.getParameter("studentGender");
	 	String studentBirthDate = request.getParameter("studentBirthDate");
	 	String studentMobile = request.getParameter("studentMobile");
	 	String fatherEmailId = request.getParameter("studentFatherEmailId");
		String fatherMobileNo = request.getParameter("studentFatherMobile");
	 	
		Student student = new Student();
		student.setStudentId(id);
		student.setStudentRollNo(studentRollNo);
		student.setStudentName(studentName);
		student.setStudentDob(Date.valueOf(studentBirthDate));
		student.setStudentMobileNo(studentMobile);
		student.setStudentGender(studentGender);
		student.setStudentSemester(semester);
		student.setStudentEmailId(studentEmailId);
		student.setFatherEmailId(fatherEmailId);
		student.setFatherMobileNo(fatherMobileNo);
		
		Branch branch = new Branch();
		branch.setBranchId(Integer.parseInt(branchId));
		student.setBranch(branch);
		
		StudentDAO.update(student);
		response.sendRedirect("ViewStudent");
		
		out.close();
	}

}
