package com.petstore.utilities;

import java.sql.*;
import java.util.*;

public class DBUtils {
	public static Connection connection;
	public static Statement statement;
	public static ResultSet resultSet;

	public static void createConnection() {
		String url = "jdbc:mysql://localhost:3306/petstore_db";
		String username = "root";
		String password = "your_password";

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Map<String, Object>> getQueryResultMap(String query) {
		List<Map<String, Object>> rowList = new ArrayList<>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<>();
				for (int i = 1; i <= md.getColumnCount(); i++) {
					row.put(md.getColumnName(i), resultSet.getObject(i));
				}
				rowList.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowList;
	}

	public static void destroy() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
