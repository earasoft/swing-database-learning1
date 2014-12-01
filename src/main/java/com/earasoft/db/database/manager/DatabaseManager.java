package com.earasoft.db.database.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.Projects;
import com.earasoft.db.dao.Resources;
import com.earasoft.db.dao.Tasks;

public interface DatabaseManager {

	public abstract boolean isOpen();

	public abstract void openDatabase() throws SQLException,
			ClassNotFoundException;

	public abstract void clearDatabase() throws SQLException,
			ClassNotFoundException;

	public abstract Connection getConnection();

	public abstract void close() throws SQLException;
	
	public abstract People getPeople();
	
	public abstract Resources getResources();
	
	public abstract Tasks getTasks();
	
	public abstract Projects getProjects();
	
}