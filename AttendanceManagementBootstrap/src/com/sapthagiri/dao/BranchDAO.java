package com.sapthagiri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sapthagiri.model.Branch;
import com.sapthagiri.util.DBConnection;

public class BranchDAO {

	public static int save(Branch branch) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"insert into college_attendance_system.college_branch(branch_name, branch_description) values(?,?)");
			ps.setString(1, branch.getBranchName());
			ps.setString(2, branch.getBranchDescription());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static List<Branch> getAllRecords() {
		List<Branch> list = new ArrayList<Branch>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_branch");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				branch.setBranchName(rs.getString("branch_name"));
				branch.setBranchDescription(rs.getString("branch_description"));
				list.add(branch);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
	
	public static int update(Branch branch){
		int status=0;
		try{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("update college_attendance_system.college_branch set branch_name=?,branch_description=? where branch_id=?");
			ps.setString(1,branch.getBranchName());
			ps.setString(2,branch.getBranchDescription());
			ps.setInt(3, branch.getBranchId());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		return status;
	}	

	public static int delete(int id){
		int status=0;
		try{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("delete from college_attendance_system.college_branch where branch_id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		return status;
	}
	
	public static Branch getRecordById(int id){
		Branch branch=new Branch();
		try{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("select * from college_attendance_system.college_branch where branch_id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				branch.setBranchId(rs.getInt("branch_id"));
				branch.setBranchName(rs.getString("branch_name"));
				branch.setBranchDescription(rs.getString("branch_description"));
			}
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		
		return branch;
	}
	
	public static List<Branch> getRecordByName(String name){
		List<Branch> list = new ArrayList<Branch>();
		try{
			Connection con=DBConnection.getCon();
			PreparedStatement ps=con.prepareStatement("select * from college_attendance_system.college_branch where branch_name like '%?%' ");
			ps.setString(1,name);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Branch branch = new Branch();
				branch.setBranchId(rs.getInt("branch_id"));
				branch.setBranchName(rs.getString("branch_name"));
				branch.setBranchDescription(rs.getString("branch_description"));
				list.add(branch);
			}
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		
		return list;
	}
}
