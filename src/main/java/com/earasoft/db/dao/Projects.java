package com.earasoft.db.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface Projects {
	
	public abstract List<Project> getProjects() throws SQLException;
	
	public abstract Project getProjectById(Integer id) throws SQLException;
	
	public abstract Project getProjectByIds(List<Integer> idList) throws SQLException;
	
	public abstract Project deleteProjectById(Integer id) throws SQLException;
	//Does this even need to be here? Is there a scenario where you would actually
	//be deleting a project from the database?
	
	public abstract Project addProject(Project project) throws SQLException;
	
	public abstract void commit() throws SQLException;
    
    public abstract void rollback() throws SQLException;
	
	
    
}
