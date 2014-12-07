package com.earasoft.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.Task;
import com.earasoft.db.database.manager.DatabaseManager;

public class TaskDAO implements Task {	
	private final Connection connection;
	private final DatabaseManager databaseManager;


public TaskDAO(DatabaseManager databaseManager){
    this.databaseManager = databaseManager;
	this.connection = this.databaseManager.getConnection();
	
}

public List<Task> getTasks() throws SQLException{
    PreparedStatement prep = getTasksPrepStatement();
	ResultSet rs = prep.executeQuery();
	
	List<Task> tasks = resultSetTaskListFillerHelper(rs);
	
	rs.close();
	prep.close();
	return tasks;
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
         Integer hours = rs.getInt("hours");
         taskList.add(new TaskDAO(this.databaseManager));
     }
    
    return taskList;
}

@Override
public String getTaskName() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setTaskName(String taskName) {
	// TODO Auto-generated method stub
	
}

@Override
public Date getStartDate() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setStartDate(Date startDate) {
	// TODO Auto-generated method stub
	
}

@Override
public Date getEndDate() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setEndDate(Date endDate) {
	// TODO Auto-generated method stub
	
}

@Override
public Integer getTotalHours() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setHours(Integer hours) {
	// TODO Auto-generated method stub
	
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
	// TODO Auto-generated method stub
	
}

@Override
public void save() throws SQLException {
	// TODO Auto-generated method stub
	
}

	
}