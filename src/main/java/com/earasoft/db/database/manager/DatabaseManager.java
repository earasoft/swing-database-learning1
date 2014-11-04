package com.earasoft.db.database.manager;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseManager {

	public abstract boolean isOpen();

	public abstract void openDatabase() throws SQLException,
			ClassNotFoundException;

	public abstract void clearDatabase() throws SQLException,
			ClassNotFoundException;

	public abstract Connection getConnection();

	public abstract void close() throws SQLException;

}