package com.earasoft.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.earasoft.db.dao.Task;
import com.earasoft.db.database.manager.DatabaseManager;

public class TaskDAO implements Task {	
	private Connection connection;
	private DatabaseManager databaseManager;
	private String taskName;
	private Date startDate;
	private Date endDate;
	private Integer totalHours;
	private Integer remainingHours;


	public TaskDAO() {
		this.taskName = "";
		this.startDate = null;
		this.endDate = null;
		this.totalHours = null;
		this.remainingHours = null;		
	}
	
	
	public TaskDAO(String taskName, Date startDate, Date endDate, Integer totalHours, Integer remainingHours) {
		super();
		this.taskName = "";
		this.startDate = null;
		this.endDate = null;
		this.totalHours = null;
		this.remainingHours = null;
	}
	
	public TaskDAO(String taskName, Date startDate, Date endDate, Integer totalHours, Integer remainingHours, DatabaseManager databaseManager) {
	    this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalHours = totalHours;
        this.remainingHours = remainingHours;
        this.databaseManager = databaseManager;
        this.connection = this.databaseManager.getConnection();
    }
	




private PreparedStatement getTasksPrepStatement() throws SQLException{
    String sql = "SELECT taskName, startDate, endDate, hours FROM task ORDER BY lower(taskName);";
    PreparedStatement prep = connection.prepareStatement(sql);
    return prep;
}

private List<Task> resultSetTaskListFillerHelper(ResultSet rs) throws SQLException{
    List<Task> taskList = new ArrayList<Task>();
    
    while(rs.next()){
         String taskName = rs.getString("taskName");
         Date startDate = rs.getDate("startDate");
         Date endDate = rs.getDate("endDate");
         Integer totalHours = rs.getInt("totalHours");
         Integer remaingingHours = rs.getInt("remainginHours");
         taskList.add(new TaskDAO(taskName, endDate, endDate, remaingingHours, remaingingHours, this.databaseManager));
     }
    
    return taskList;
}

@Override
public String getTaskName() {
	return taskName;
}

@Override
public void setTaskName(String taskName) {
	this.taskName = taskName;
	
}

@Override
public Date getStartDate() {
	return startDate;
}

@Override
public void setStartDate(Date startDate) {
	this.startDate = startDate;
	
}

@Override
public Date getEndDate() {
	return endDate;
}

@Override
public void setEndDate(Date endDate) {
	this.endDate = endDate;
	
}

@Override
public Integer getTotalHours() {
	return totalHours;
}





@Override
public Integer getRemainingHours() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setRemainingHours(Integer remainingHours) {
	// TODO Auto-generated method stub
	
}

@Override
public String toStringFull() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setDatabase(DatabaseManager databaseManager) {
	this.databaseManager = databaseManager;
	this.connection = this.databaseManager.getConnection();
}

@Override
public void save() throws SQLException {
	// TODO Auto-generated method stub
	
}


@Override
public void setTotalHours(Integer hours) {
	this.totalHours = totalHours;
	
}

	
}