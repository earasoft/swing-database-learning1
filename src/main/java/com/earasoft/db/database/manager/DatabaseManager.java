package com.earasoft.db.database.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;

public interface DatabaseManager {

	public abstract boolean isOpen();

	public abstract void openDatabase() throws SQLException,
			ClassNotFoundException;

	public abstract void clearDatabase() throws SQLException,
			ClassNotFoundException;

	public abstract Connection getConnection();

	public abstract void close() throws SQLException;
	
	public abstract People people();
	
	public abstract Person person();

}