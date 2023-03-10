package com.sapthagiri.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sapthagiri.model.Attendance;
import com.sapthagiri.model.AttendanceResult;
import com.sapthagiri.util.DBConnection;

public class AttendanceDAO {

	public static boolean save(List<Attendance> attendanceList) {
		boolean status = true;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"insert into college_attendance_system.college_attendance(attendance_semester, attendance_date, attendance_total_class, attendance_status, student_id, subject_id) values(?,?,?,?,?,?)");
			for(Attendance attendance : attendanceList) {
				ps.setString(1, attendance.getAttendanceSemester());
				ps.setDate(2, attendance.getAttendanceDate());
				ps.setInt(3, attendance.getTotalAttendanceClass());
				ps.setInt(4, attendance.getAttendanceStatus());
				ps.setInt(5, attendance.getStudent().getStudentId());
				ps.setInt(6, attendance.getSubject().getSubjectId());
				ps.addBatch();
			}
			ps.executeBatch();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
			status=false;
		}
		return status;
	}

	public static List<AttendanceResult> getBranchSemesterRecords(int branchId, String semester) {
		List<AttendanceResult> list = new ArrayList<AttendanceResult>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("SELECT  catt.student_id, cstd.student_name,  catt.subject_id, csub.subject_name, catt.attendance_semester, \r\n" + 
					"  sum(catt.attendance_status) , catt.attendance_total_class, (sum(catt.attendance_status)/catt.attendance_total_class)*100 \r\n" + 
					"  FROM college_attendance_system.college_attendance catt, \r\n" + 
					"college_attendance_system.college_subject csub, college_attendance_system.college_student cstd \r\n" + 
					"where \r\n" + 
					"  catt.student_id = cstd.student_id AND\r\n" + 
					"  catt.subject_id = csub.subject_id AND\r\n" + 
					"  catt.student_id in\r\n" + 
					" ( select student_id from college_attendance_system.college_student where student_semester = ? and branch_id =?)\r\n" + 
					" group by  catt.student_id, catt.subject_id, catt.attendance_semester \r\n" + 
					" order by cstd.student_name, csub.subject_name\r\n" + 
					" ");
			ps.setString(1, semester);
			ps.setInt(2,  branchId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AttendanceResult result = new AttendanceResult();
				result.setStudentId(rs.getInt(1));
				result.setStudentName(rs.getString(2));
				result.setSubjectId(rs.getInt(3));
				result.setSubjectName(rs.getString(4));
				result.setSemster(rs.getString(5));
				result.setAttendanceCount(rs.getInt(6));
				result.setTotalClasses(rs.getInt(7));
				result.setSubjectPercentage(rs.getFloat(8));
				list.add(result);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
	
	public static List<AttendanceResult> getRecordsForDownload(int branchId, String semester, int subjectId, Date attendanceDate) {
		List<AttendanceResult> list = new ArrayList<AttendanceResult>();
		
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append("SELECT  catt.student_id, cstd.student_name,  catt.subject_id, csub.subject_name,  catt.attendance_semester,\r\n" + 
				"  sum(catt.attendance_status) , catt.attendance_total_class, (sum(catt.attendance_status)/catt.attendance_total_class)*100\r\n" + 
				"  FROM college_attendance_system.college_attendance catt, \r\n" + 
				"college_attendance_system.college_subject csub, college_attendance_system.college_student cstd\r\n" + 
				"where \r\n" + 
				"  catt.student_id = cstd.student_id AND\r\n" + 
				"  catt.subject_id = csub.subject_id AND\r\n" + 
				"  catt.student_id in\r\n" + 
				" ( select student_id from college_attendance_system.college_student where branch_id = " + branchId);
		if(semester != null) {
			sqlQuery.append(" AND student_semester =  " + semester );
		}
		sqlQuery.append(" )");
		
		if(subjectId > 0) {
			sqlQuery.append("  AND catt.subject_id =  " + subjectId);
		}
		
		if(attendanceDate != null) {
			sqlQuery.append(" AND catt.attendance_date = date('"+ attendanceDate.toString() + "')" );
		}
		sqlQuery.append(" group by  catt.student_id, catt.subject_id , catt.attendance_semester\r\n" + 
				" order by cstd.student_name, csub.subject_name ");
		
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(sqlQuery.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AttendanceResult result = new AttendanceResult();
				result.setStudentId(rs.getInt(1));
				result.setStudentName(rs.getString(2));
				result.setSubjectId(rs.getInt(3));
				result.setSubjectName(rs.getString(4));
				result.setSemster(rs.getString(5));
				result.setAttendanceCount(rs.getInt(6));
				result.setTotalClasses(rs.getInt(7));
				result.setSubjectPercentage(rs.getFloat(8));
				list.add(result);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
}
