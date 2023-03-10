package com.sapthagiri.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
	
	private static Logger logger = Logger.getLogger(DBConnection.class.getName());

	public static Connection getCon() {
		Connection con = null;
		try {
			Class.forName(Constants.CONNECTION_DRIVER_NAME);
			con = DriverManager.getConnection(Constants.CONNECTION_URL, Constants.CONNECTION_USERNAME,
					Constants.CONNECTION_PASSWORD);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception while DB Connection for URL : " + Constants.CONNECTION_URL);
		}
		return con;
	}
}
