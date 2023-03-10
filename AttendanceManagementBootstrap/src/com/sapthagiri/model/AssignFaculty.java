package com.sapthagiri.model;

public class AssignFaculty {

	private int facultyId;
	
	private String facultySemster;
	
	private int facultyTotalClasses;
	
	private User user;
	
	private Branch branch;
	
	private Subject subject;

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultySemster() {
		return facultySemster;
	}

	public void setFacultySemster(String facultySemster) {
		this.facultySemster = facultySemster;
	}

	public int getFacultyTotalClasses() {
		return facultyTotalClasses;
	}

	public void setFacultyTotalClasses(int facultyTotalClasses) {
		this.facultyTotalClasses = facultyTotalClasses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
	
}
