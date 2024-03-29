package com.rest.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.*;
/**
 * This class returns the Oracle database connect object from
 * a CentOS Oracle Express Virtual Machine
 *
 * The method and variable in this class are static to save resources
 * You only need one instance of this class running.
 *
 * 
 *
 * 
 *
 *
 *
 */
public class DBClass {
	private static DataSource mySQLDataSource = null; //hold the database object
	private static Context context = null; //used to lookup the database connection in weblogic
	/**
	 * This is a public method that will return the  database connection.
	 *
	 *
	 *
	 * @return Database object
	 * @throws Exception
	 */
	private static DataSource getMySQLConnection() throws Exception {
		/**
		 * check to see if the database object is already defined...
		 * if it is, then return the connection, no need to look it up again.
		 */
		if (mySQLDataSource != null) {
			return mySQLDataSource;
		}
		try {
			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in JNDI
			 * 
			 */
			if (context == null) {
				context = new InitialContext();
			}
			/**
			 * ATTENTION: HERE PUT THE JNDI NAME 
			 * */

			mySQLDataSource = (DataSource) context.lookup("jdbc/ProjectDataSource");//JNDI NAME HERE
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mySQLDataSource;
	}
	/**
	 * This method will return the connection to the DB
	 * @return Connection to MySQL database.
	 */
	public static Connection returnConnection() {
		Connection conn = null;
		try {
			conn = getMySQLConnection().getConnection();
			return conn;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/WS_PROJECT";
		String username = "root";
		String password = "root";

		System.out.println("Connecting database...");
		System.out.println("Loading driver...");

		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}