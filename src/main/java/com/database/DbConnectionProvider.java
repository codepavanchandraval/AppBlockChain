package com.database;


import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnectionProvider {
	protected static Connection connection = null;
	
	public static Connection getConnection() {
		if (connection == null) {
			doOneTimeDBSetup();
		}
		return connection;
	}
	
	private static void doOneTimeDBSetup() {
		try {
			DbConnectionProvider.createConnection("jdbc:oracle:thin:@10.148.26.42:1521:DEVDB011", "FJKFF31_PD12102019", "etrans");
		} catch (Exception e) {
			System.out.println("Error while accessing Environment");
		}
	}
	
	public static void createConnection(String url, String userName, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			System.out.println("Error ::Creating Connection With DB");// In Server We should Use logger.
		}
	}
}
