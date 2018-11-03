package com.sollers.banking.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IAppDbConn {

	public Connection getConnection() throws SQLException;

	public void closeConnection() throws SQLException;

	public void begin() throws SQLException;

	public void rollback() throws SQLException;

	public void commit() throws SQLException;
}
