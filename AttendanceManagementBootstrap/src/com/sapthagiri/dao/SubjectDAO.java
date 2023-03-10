package com.sapthagiri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Subject;
import com.sapthagiri.util.DBConnection;

public class SubjectDAO {

	public static int save(Subject subject) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"insert into college_attendance_system.college_subject(subject_code, subject_name, semester, branch_id, branch_name) values(?,?,?,?,?)");
			ps.setString(1, subject.getSubjectCode());
			ps.setString(2, subject.getSubjectName());
			ps.setString(3, subject.getSemester());
			ps.setInt(4, subject.getBranch().getBranchId());
			ps.setString(5, subject.getBranch().getBranchName());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static List<Subject> getAllRecords() {
		List<Subject> list = new ArrayList<Subject>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_subject");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectCode(rs.getString("subject_code"));
				subject.setSemester(rs.getString("semester"));
				subject.setSubjectName(rs.getString("subject_name"));
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				branch.setBranchName(rs.getString("branch_name"));
				subject.setBranch(branch);
				
				list.add(subject);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}

	public static int update(Subject subject) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"update college_attendance_system.college_subject set subject_code=?,subject_name=?, semester=?, branch_id=?, branch_name=? where subject_id=?");
			ps.setString(1, subject.getSubjectCode());
			ps.setString(2, subject.getSubjectName());
			ps.setString(3, subject.getSemester());
			ps.setInt(4, subject.getBranch().getBranchId());
			ps.setString(5, subject.getBranch().getBranchName());
			ps.setInt(6, subject.getSubjectId());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con
					.prepareStatement("delete from college_attendance_system.college_subject where subject_id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static Subject getRecordById(int id) {
		Subject subject = new Subject();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con
					.prepareStatement("select * from college_attendance_system.college_subject where subject_id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectCode(rs.getString("subject_code"));
				subject.setSemester(rs.getString("semester"));
				subject.setSubjectName(rs.getString("subject_name"));
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				branch.setBranchName(rs.getString("branch_name"));
				subject.setBranch(branch);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return subject;
	}

	public static List<Subject> getFilteredRecords(int facultyId) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_subject where subject_id "
					+ " in (select subject_id from college_attendance_system.college_faculty where user_id = ?  ) ");
			ps.setInt(1, facultyId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectCode(rs.getString("subject_code"));
				subject.setSemester(rs.getString("semester"));
				subject.setSubjectName(rs.getString("subject_name"));
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				branch.setBranchName(rs.getString("branch_name"));
				subject.setBranch(branch);
				
				list.add(subject);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
	public static List<Subject> getRecordByName(String name) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"select * from college_attendance_system.college_subject where subject_name like '%?%' ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setSubjectId(rs.getInt("subject_id"));
				subject.setSubjectCode(rs.getString("subject_code"));
				subject.setSemester(rs.getString("semester"));
				subject.setSubjectName(rs.getString("subject_name"));
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				branch.setBranchName(rs.getString("branch_name"));
				subject.setBranch(branch);
				
				list.add(subject);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}

}
