package com.sapthagiri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sapthagiri.model.AssignFaculty;
import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Subject;
import com.sapthagiri.model.User;
import com.sapthagiri.util.DBConnection;

public class AssignFacultyDAO {

	public static int save(AssignFaculty faculty) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"insert into college_attendance_system.college_faculty(faculty_semester, faculty_total_classes, user_id, branch_id, subject_id) values(?,?,?,?,?)");
			ps.setString(1, faculty.getFacultySemster());
			ps.setInt(2, faculty.getFacultyTotalClasses());
			ps.setInt(3, faculty.getUser().getUserId());
			ps.setInt(4, faculty.getBranch().getBranchId());
			ps.setInt(5, faculty.getSubject().getSubjectId());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static List<AssignFaculty> getAllRecords() {
		List<AssignFaculty> list = new ArrayList<AssignFaculty>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_faculty");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AssignFaculty faculty = new AssignFaculty();
				faculty.setFacultyId(rs.getInt("faculty_id"));
				faculty.setFacultySemster(rs.getString("faculty_semester"));
				faculty.setFacultyTotalClasses(rs.getInt("faculty_total_classes"));
				int userId = rs.getInt("user_id");
				User user = UserDAO.getRecordById(userId);
				int branchId = rs.getInt("branch_id");
				Branch branch = BranchDAO.getRecordById(branchId);
				int subjectId = rs.getInt("subject_id");
				Subject subject = SubjectDAO.getRecordById(subjectId);

				faculty.setUser(user);
				faculty.setBranch(branch);
				faculty.setSubject(subject);
				
				list.add(faculty);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
	
	public static int update(AssignFaculty faculty){
		int status=0;
		try{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("update college_attendance_system.college_faculty set faculty_semester=?, faculty_total_classes=?, user_id=?, branch_id=?, subject_id=? where faculty_id=?");
			ps.setString(1, faculty.getFacultySemster());
			ps.setInt(2, faculty.getFacultyTotalClasses());
			ps.setInt(3, faculty.getUser().getUserId());
			ps.setInt(4, faculty.getBranch().getBranchId());
			ps.setInt(5, faculty.getSubject().getSubjectId());
			ps.setInt(6, faculty.getFacultyId());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		return status;
	}	

	public static int delete(int id){
		int status=0;
		try{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("delete from college_attendance_system.college_faculty where faculty_id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		return status;
	}
	
	public static AssignFaculty getRecordById(int id){
		AssignFaculty faculty = new AssignFaculty();
		try{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("select * from college_attendance_system.college_faculty where faculty_id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				faculty.setFacultyId(rs.getInt("faculty_id"));
				faculty.setFacultySemster(rs.getString("faculty_semester"));
				faculty.setFacultyTotalClasses(rs.getInt("faculty_total_classes"));
				int userId = rs.getInt("user_id");
				User user = UserDAO.getRecordById(userId);
				int branchId = rs.getInt("branch_id");
				Branch branch = BranchDAO.getRecordById(branchId);
				int subjectId = rs.getInt("subject_id");
				Subject subject = SubjectDAO.getRecordById(subjectId);
				
				faculty.setUser(user);
				faculty.setBranch(branch);
				faculty.setSubject(subject);
				
			}
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		
		return faculty;
	}
	
	public static List<AssignFaculty> getFilteredRecords(int facultySubjectId, int semesterId) {
		List<AssignFaculty> list = new ArrayList<AssignFaculty>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_faculty where subject_id=? and faculty_semester =?");
			ps.setInt(1, facultySubjectId);
			ps.setInt(2, semesterId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AssignFaculty faculty = new AssignFaculty();
				faculty.setFacultyId(rs.getInt("faculty_id"));
				faculty.setFacultySemster(rs.getString("faculty_semester"));
				faculty.setFacultyTotalClasses(rs.getInt("faculty_total_classes"));
				int userId = rs.getInt("user_id");
				User user = UserDAO.getRecordById(userId);
				int branchId = rs.getInt("branch_id");
				Branch branch = BranchDAO.getRecordById(branchId);
				int subjectId = rs.getInt("subject_id");
				Subject subject = SubjectDAO.getRecordById(subjectId);

				faculty.setUser(user);
				faculty.setBranch(branch);
				faculty.setSubject(subject);
				
				list.add(faculty);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
}
