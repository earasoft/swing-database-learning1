package com.earasoft.db.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;
//imported the date utility here, not sure if this will work 
//with the date data type of the SQLExec.java class but it didn't seem
//right to have the dates as strings

import com.earasoft.db.database.manager.DatabaseManager;

public interface Project {
	
	public abstract Integer getProjectId();
	
	public abstract void setProjectId(Integer projectId);
	
	public abstract Date getStartDate();
    
    public abstract void setStartDate(Date startDate);
    
    public abstract Date getEndDate();
    
    public abstract void setEndDate(Date endDate);
    
    public abstract String getManager();
    
    public abstract void setManager(String manager);

	public abstract String toStringFull();

	public abstract void setDatabase(DatabaseManager databaseManager);
	
	public abstract void save()  throws SQLException;
	
}
