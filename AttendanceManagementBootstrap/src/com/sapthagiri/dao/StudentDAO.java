package com.sapthagiri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sapthagiri.model.Branch;
import com.sapthagiri.model.Student;
import com.sapthagiri.model.Subject;
import com.sapthagiri.util.DBConnection;

public class StudentDAO {

	public static int save(Student student) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"insert into college_attendance_system.college_student(student_roll_no, student_name, student_dob, student_mobile_no, student_gender, student_semester, student_email_id, father_email_id, father_mobile_no, branch_id)"
					+ " values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, student.getStudentRollNo());
			ps.setString(2, student.getStudentName());
			ps.setDate(3, student.getStudentDob());
			ps.setString(4, student.getStudentMobileNo());
			ps.setString(5, student.getStudentGender());
			ps.setString(6, student.getStudentSemester());
			ps.setString(7, student.getStudentEmailId());
			ps.setString(8, student.getFatherEmailId());
			ps.setString(9, student.getFatherMobileNo());
			ps.setInt(10,student.getBranch().getBranchId());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static List<Student> getAllRecords() {
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_student");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setStudentRollNo(rs.getString("student_roll_no"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentDob(rs.getDate("student_dob"));
				student.setStudentMobileNo(rs.getString("student_mobile_no"));
				student.setStudentGender(rs.getString("student_gender"));
				student.setStudentSemester(rs.getString("student_semester"));
				student.setStudentEmailId(rs.getString("student_email_id"));
				student.setFatherEmailId(rs.getString("father_email_id"));
				student.setFatherMobileNo(rs.getString("father_mobile_no"));
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				student.setBranch(branch);
				
				
				list.add(student);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}

	public static int update(Student student) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"update college_attendance_system.college_student set student_roll_no=?,student_name=?, student_dob=?, student_mobile_no=?, student_gender=? , student_semester =?, student_email_id=?, father_email_id=?, father_mobile_no=?, branch_id=? where student_id=?");
			ps.setString(1, student.getStudentRollNo());
			ps.setString(2, student.getStudentName());
			ps.setDate(3, student.getStudentDob());
			ps.setString(4, student.getStudentMobileNo());
			ps.setString(5, student.getStudentGender());
			ps.setString(6, student.getStudentSemester());
			ps.setString(7, student.getStudentEmailId());
			ps.setString(8, student.getFatherEmailId());
			ps.setString(9, student.getFatherMobileNo());
			ps.setInt(10,student.getBranch().getBranchId());
			ps.setInt(11,  student.getStudentId());
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
					.prepareStatement("delete from college_attendance_system.college_student where student_id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static Student getRecordById(int id) {
		Student student = new Student();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con
					.prepareStatement("select * from college_attendance_system.college_student where student_id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				student.setStudentId(rs.getInt("student_id"));
				student.setStudentRollNo(rs.getString("student_roll_no"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentDob(rs.getDate("student_dob"));
				student.setStudentMobileNo(rs.getString("student_mobile_no"));
				student.setStudentGender(rs.getString("student_gender"));
				student.setStudentSemester(rs.getString("student_semester"));
				student.setStudentEmailId(rs.getString("student_email_id"));
				student.setFatherEmailId(rs.getString("father_email_id"));
				student.setFatherMobileNo(rs.getString("father_mobile_no"));
				
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				student.setBranch(branch);
				
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return student;
	}

	public static List<Student> getRecordByName(String name) {
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"select * from college_attendance_system.college_student where student_name like '%?%' ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setStudentRollNo(rs.getString("student_roll_no"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentDob(rs.getDate("student_dob"));
				student.setStudentMobileNo(rs.getString("student_mobile_no"));
				student.setStudentGender(rs.getString("student_gender"));
				student.setStudentSemester(rs.getString("student_semester"));
				student.setStudentEmailId(rs.getString("student_email_id"));
				student.setFatherEmailId(rs.getString("father_email_id"));
				student.setFatherMobileNo(rs.getString("father_mobile_no"));
				
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				student.setBranch(branch);
				
				list.add(student);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}

	public static List<Student> getRecordsBySubject(Subject subject) {
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"select * from college_attendance_system.college_student where branch_id=? and student_semester=? ");
			ps.setInt(1, subject.getBranch().getBranchId());
			ps.setString(2, subject.getSemester());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setStudentRollNo(rs.getString("student_roll_no"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentDob(rs.getDate("student_dob"));
				student.setStudentMobileNo(rs.getString("student_mobile_no"));
				student.setStudentGender(rs.getString("student_gender"));
				student.setStudentSemester(rs.getString("student_semester"));
				student.setStudentEmailId(rs.getString("student_email_id"));
				student.setFatherEmailId(rs.getString("father_email_id"));
				student.setFatherMobileNo(rs.getString("father_mobile_no"));
				
				
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				student.setBranch(branch);
				
				list.add(student);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
}
