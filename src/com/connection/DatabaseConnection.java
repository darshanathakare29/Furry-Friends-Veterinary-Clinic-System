package com.connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseConnection {

	public static Connection connection;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ovcsdb?useSSL=false", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (connection);
	}

	public static void CloseConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
		ResultSet rs = null;
		try {
			if (connection == null) {
				getConnection();
			}
			rs = connection.createStatement().executeQuery(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int insertUpdateFromSqlQuery(String SqlQueryString) {
		int i = 2;
		try {
			if (connection == null) {
				getConnection();
			}
			i = connection.createStatement().executeUpdate(SqlQueryString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return i;
	}
	
	public static String generateClientId() {
		String cid = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		cid = sdf.format(new Date());
		cid = "C" + cid;
		System.out.println("Client Id " + cid);
		return cid;
	}
	
	public static String generateFarmerId() {
		String fid = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		fid = sdf.format(new Date());
		fid = "F" + fid;
		System.out.println("Farmer Id " + fid);
		return fid;
	}
	
	public static String generateDoctorId() {
		String did = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		did = sdf.format(new Date());
		did = "D" + did;
		System.out.println("Doctor Id " + did);
		return did;
	}
	
	public static String generateResponseId() {
		String rid = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		rid = sdf.format(new Date());
		rid = "R" + rid;
		System.out.println("Doctor Id " + rid);
		return rid;
	}
	
	public static String generateAnimalId() {
		String aid = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		aid = sdf.format(new Date());
		aid = "A" + aid;
		System.out.println("Animal Id " + aid);
		return aid;
	}
}
