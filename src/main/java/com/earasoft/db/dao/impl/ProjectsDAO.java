package com.earasoft.db.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.Project;
import com.earasoft.db.dao.Projects;
import com.earasoft.db.database.manager.DatabaseManager;

public class ProjectsDAO implements Projects {
	private final Connection connection;
	private final DatabaseManager databaseManager;
	
	public ProjectsDAO(DatabaseManager databaseManager){
	    this.databaseManager = databaseManager;
		this.connection = this.databaseManager.getConnection();
	}

	@Override
	public List<Project> getProjects() throws SQLException {
		PreparedStatement prep = getProjectPrepStatement();
		ResultSet rs = prep.executeQuery();
			
		List<Project> project = resultSetProjectListFillerHelper(rs);
			
		rs.close();
		prep.close();
		return project;
	}

	@Override
	public Project getProjectById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProjectByIds(List<Integer> idList) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project deleteProjectById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project addProject(Project project) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private PreparedStatement getProjectPrepStatement() throws SQLException{
        String sql = "SELECT projectId, startDate, endDate FROM project ORDER BY lower(projectId);";
        PreparedStatement prep = connection.prepareStatement(sql);
        return prep;
    }
	
	private List<Project> resultSetProjectListFillerHelper(ResultSet rs) throws SQLException{
	       List<Project> projectList = new ArrayList<Project>();
	       
	       while(rs.next()){
	            Integer projectId = rs.getInt("projectId");
	            Date startDate = rs.getDate("startDate");
	            Date endDate = rs.getDate("endDate");
	            String manager = rs.getString("manager");
	            projectList.add(new ProjectDAO(projectId, startDate, endDate, manager, this.databaseManager));
	        }
	       
	       return projectList;
	}

	@Override
	public void commit() throws SQLException {
		 this.connection.commit();
		
	}

	@Override
	public void rollback() throws SQLException {
		this.connection.rollback();
		
	}

}
