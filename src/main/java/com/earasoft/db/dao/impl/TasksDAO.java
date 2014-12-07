package com.earasoft.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.earasoft.db.dao.Tasks;
import com.earasoft.db.dao.Task;
import com.earasoft.db.database.manager.DatabaseManager;

public class TasksDAO implements Tasks {
	private final Connection connection;
	private final DatabaseManager databaseManager;
	
	public TasksDAO(DatabaseManager databaseManager){
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
	
	private PreparedStatement getTasksPrepStatement() throws SQLException{
	    String sql = "SELECT taskName, startDate, endDate, hours FROM task ORDER BY lower(taskName);";
	    PreparedStatement prep = connection.prepareStatement(sql);
	    return prep;
	}

	@Override
	public Task addTask(String task) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task delTask(String task) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task calRemainingHours(Integer remainingHours) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
