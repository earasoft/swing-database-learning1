package com.earasoft.db.dao;
import java.util.Date;
import java.sql.SQLException;

import com.earasoft.db.database.manager.DatabaseManager;


public interface Task {
	
	public abstract String getTaskName();
	public abstract void setTaskName(String taskName);
	
	public abstract Date getStartDate();
	public abstract void setStartDate(Date startDate);
	
	public abstract Date getEndDate();
	public abstract void setEndDate(Date endDate);
	
	public abstract Integer getTotalHours();
	public abstract void setHours(Integer hours);
	
	public abstract Integer getRemainingHours();
	public abstract void setRemainingHours(Integer remainingHours);
	
	public abstract String toStringFull();
	public abstract void setDatabase(DatabaseManager databaseManager);
	public abstract void save()  throws SQLException;
    
   
}
