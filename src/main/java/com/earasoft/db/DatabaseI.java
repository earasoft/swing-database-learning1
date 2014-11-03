package com.earasoft.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseI {
	
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

    public void init(Connection currentConnection) throws SQLException;


}
