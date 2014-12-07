package com.earasoft.db.dao;

import java.sql.SQLException;
import java.util.List;

import com.earasoft.db.database.manager.DatabaseManager;
import com.earasoft.db.database.manager.DatabaseManager;
import com.earasoft.db.database.manager.DatabaseManagerImpl;

public interface Tasks {
	
	public abstract List<Task> getTasks() throws SQLException;
	
	public abstract Task addTask(String task) throws SQLException;
	
	public abstract Task delTask(String task) throws SQLException;
	
	public abstract Task calRemainingHours(Integer remainingHours) throws SQLException;
	
	public abstract void commit() throws SQLException;
	    
	public abstract void rollback() throws SQLException;		
    
}
