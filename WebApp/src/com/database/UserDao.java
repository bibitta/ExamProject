package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.UserBean;

public class UserDao {
	public UserBean findUser(final String userName, final String pass) throws SQLException {
		UserBean result = null;
		final String sql = "SELECT * FROM USER where " +
		"username=? and passwor=?;";
		PreparedStatement prs = null;
		try {
			final Connection conn = DbConnection.getInstanse().getConnection();
			prs = conn.prepareStatement(sql);
			prs.setString(1, userName);
			prs.setString(2, pass);
			ResultSet resultSet = prs.executeQuery();
			if (resultSet.next()) {
				result = new UserBean();
				result.setUsername(resultSet.getString("username"));
				result.setEmail(resultSet.getString("email"));
				result.setPassword(resultSet.getString("passwor"));
				result.setPicturePath(resultSet.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (null != prs) {
				prs.close();
			}
			DbConnection.getInstanse().disconnect();
		}

		return result;
	}

}
