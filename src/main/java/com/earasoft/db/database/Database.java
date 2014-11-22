package com.earasoft.db.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
	
	/**
	 * Opens a connection to the database
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException;
	
	/**
	 * Return an identifier for DatabaseManager
	 */
	public String getName();


}
