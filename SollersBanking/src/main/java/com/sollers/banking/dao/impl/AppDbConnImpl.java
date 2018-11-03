package com.sollers.banking.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.sollers.banking.dao.IAppDbConn;

@Component
public class AppDbConnImpl implements IAppDbConn {

	private static Connection connection = null;
	private static Properties prop = null;

	@Override
	public Connection getConnection() throws SQLException {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("G:\\app.properties")));
			Class.forName(prop.getProperty("sollers.db.driver"));
			this.closeConnection();
			connection = DriverManager.getConnection(prop.getProperty("sollers.db.url"),
					prop.getProperty("sollers.db.id"), prop.getProperty("sollers.db.pwd"));
			connection.setAutoCommit(false);
		} catch (Exception exception) {
			throw new SQLException(exception);
		}
		return connection;
	}

	@Override
	public void begin() throws SQLException {
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException sqlException) {
				throw new SQLException(sqlException);
			}
		}
	}

	@Override
	public void rollback() throws SQLException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException sqlException) {
				throw new SQLException(sqlException);
			}
		}
	}

	@Override
	public void commit() throws SQLException {
		try {
			connection.commit();
		} catch (SQLException sqlCommitException) {
			throw new SQLException(sqlCommitException);
		} finally {
			connection.close();
		}
	}

	@Override
	public void closeConnection() throws SQLException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqlException) {
				throw new SQLException(sqlException);
			}
		}
	}

}
