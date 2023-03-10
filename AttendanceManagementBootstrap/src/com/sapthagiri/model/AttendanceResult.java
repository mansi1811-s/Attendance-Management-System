package com.sapthagiri.model;

public class AttendanceResult {

	private int studentId;
	
	private String studentName;
	
	private int subjectId;
	
	private String semster; 
	
	private String subjectName;
	
	private int attendanceCount;
	
	private int totalClasses;
	
	private float subjectPercentage;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getSemster() {
		return semster;
	}

	public void setSemster(String semster) {
		this.semster = semster;
	}

	public int getAttendanceCount() {
		return attendanceCount;
	}

	public void setAttendanceCount(int attendanceCount) {
		this.attendanceCount = attendanceCount;
	}

	public int getTotalClasses() {
		return totalClasses;
	}

	public void setTotalClasses(int totalClasses) {
		this.totalClasses = totalClasses;
	}

	public float getSubjectPercentage() {
		return subjectPercentage;
	}

	public void setSubjectPercentage(float subjectPercentage) {
		this.subjectPercentage = subjectPercentage;
	}
	
}
