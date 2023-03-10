package com.sapthagiri.model;

public class Branch {

	private int branchId;
	
	private String branchName;
	
	private String branchDescription;
	
	public Branch() {
		
	}

	public Branch(String branchName, String branchDescription) {
		this.branchName = branchName;
		this.branchDescription = branchDescription;
	}
	
	public Branch(int branchId, String branchName, String branchDescription) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchDescription = branchDescription;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchDescription() {
		return branchDescription;
	}

	public void setBranchDescription(String branchDescription) {
		this.branchDescription = branchDescription;
	}
	
	
}
