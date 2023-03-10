package com.sapthagiri.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sapthagiri.model.User;
import com.sapthagiri.util.DBConnection;

public class UserDAO {

	public static User validateUserAccount(String userName, String password, int roleId) {
		User user = new User();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"select * from college_attendance_system.college_user where user_login=? and user_password=? and role_id=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setInt(3, roleId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUserRoleId(rs.getInt("role_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLogin(rs.getString("user_login"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserDob(rs.getDate("user_dob"));
				user.setUserMobileNo(rs.getString("user_mobile_no"));
				user.setUserGender(rs.getString("user_gender"));
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return user;
	}
	
	public static int save(User user) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"insert into college_attendance_system.college_user(role_id, user_name, user_login, user_password, user_dob, user_mobile_no, user_gender, user_image) values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, user.getUserRoleId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserLogin());
			ps.setString(4, user.getUserPassword());
			ps.setDate(5, user.getUserDob());
			ps.setString(6, user.getUserMobileNo());
			ps.setString(7, user.getUserGender());
			ps.setBlob(8, user.getUserImage());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static List<User> getAllRecords() {
		List<User> list = new ArrayList<User>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserRoleId(rs.getInt("role_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLogin(rs.getString("user_login"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserDob(rs.getDate("user_dob"));
				user.setUserMobileNo(rs.getString("user_mobile_no"));
				user.setUserGender(rs.getString("user_gender"));
				user.setUserImage(rs.getBlob("user_image"));
				list.add(user);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		
		return list;
	}

	public static List<User> getAllRoleBasedRecords(int roleId) {
		List<User> list = new ArrayList<User>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from college_attendance_system.college_user where role_id = ?");
			ps.setInt(1, roleId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserRoleId(rs.getInt("role_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLogin(rs.getString("user_login"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserDob(rs.getDate("user_dob"));
				user.setUserMobileNo(rs.getString("user_mobile_no"));
				user.setUserGender(rs.getString("user_gender"));
				user.setUserImage(rs.getBlob("user_image"));
				list.add(user);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	return list;
}
	public static int update(User user) {
		int status = 0;
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"update college_attendance_system.college_user set role_id=?, user_name=?, user_login=?, user_password=?, user_dob=?, user_mobile_no=?, user_gender=?, user_image=? where user_id=?");
			ps.setInt(1, user.getUserRoleId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserLogin());
			ps.setString(4, user.getUserPassword());
			ps.setDate(5, user.getUserDob());
			ps.setString(6, user.getUserMobileNo());
			ps.setString(7, user.getUserGender());
			ps.setBlob(8, user.getUserImage());
			ps.setInt(9, user.getUserId());
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
					.prepareStatement("delete from college_attendance_system.college_user where user_id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return status;
	}

	public static User getRecordById(int id) {
		User user = new User();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con
					.prepareStatement("select * from college_attendance_system.college_user where user_id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUserRoleId(rs.getInt("role_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLogin(rs.getString("user_login"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserDob(rs.getDate("user_dob"));
				user.setUserMobileNo(rs.getString("user_mobile_no"));
				user.setUserGender(rs.getString("user_gender"));
				user.setUserImage(rs.getBlob("user_image"));
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return user;
	}

	public static List<User> getRecordByName(String name) {
		List<User> list = new ArrayList<User>();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement(
					"select * from college_attendance_system.college_user where user_name like '%?%' ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserRoleId(rs.getInt("role_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLogin(rs.getString("user_login"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserDob(rs.getDate("user_dob"));
				user.setUserMobileNo(rs.getString("user_mobile_no"));
				user.setUserGender(rs.getString("user_gender"));
				user.setUserImage(rs.getBlob("user_image"));
				
				list.add(user);
			}
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return list;
	}
	
}
