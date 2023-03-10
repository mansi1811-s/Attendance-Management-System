package com.sapthagiri.model;

import java.sql.Date;

public class Attendance {

	private int attendanceId;
	
	private String attendanceSemester;
	
	private Date attendanceDate;
	
	private int attendanceStatus;
	
	private int totalAttendanceClass;
	
	private Student student;
	
	private Subject subject;

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getAttendanceSemester() {
		return attendanceSemester;
	}

	public void setAttendanceSemester(String attendanceSemester) {
		this.attendanceSemester = attendanceSemester;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
	public int getTotalAttendanceClass() {
		return totalAttendanceClass;
	}

	public void setTotalAttendanceClass(int totalAttendanceClass) {
		this.totalAttendanceClass = totalAttendanceClass;
	}

	public int getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(int attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
	
}
