package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbConnection {
	private static final String DRIVER = "org.h2.Driver";
	private static final String URL = "jdbc:h2:tcp://localhost/~/WebApp";
	private static final String USER_NAME = "sa";
	private static final String PASS = "";
	private static DbConnection instance;
	private static Connection conn;

	private DbConnection() {

	}

	public static DbConnection getInstanse() {
		if (null == instance) {
			instance = new DbConnection();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		connect();
		return conn;

	}

	private void connect() throws SQLException {
		if (null == conn) {
			try {
				Class.forName(DRIVER);
			} catch (final ClassNotFoundException e) {
				System.out.println("Driver not found.");
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(URL, USER_NAME, PASS);

		}
	}

	public void disconnect() {
		if (null != conn) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
