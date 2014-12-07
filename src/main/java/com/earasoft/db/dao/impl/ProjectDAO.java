package com.earasoft.db.dao.impl;

import com.earasoft.db.dao.Project;
import com.earasoft.db.database.manager.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ProjectDAO implements Project {
	
	private Integer projectId;
	private Date startDate;
	private Date endDate;
	private String manager;
	private Connection connection;
	private DatabaseManager databaseManager;

	public ProjectDAO(Integer projectId, Date startDate, Date endDate, String manager) {
		super();
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.manager = manager;
		
	}
	
	public ProjectDAO(Integer projectId, Date startDate, Date endDate, String manager, DatabaseManager databaseManager) {
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.manager = manager;
        this.databaseManager = databaseManager;
        this.connection = this.databaseManager.getConnection();
    }

	

	@Override
	public Integer getProjectId() {
		return projectId;
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
		this.endDate= endDate;
		
	}

	@Override
	public String getManager() {
		return manager;
	}

	@Override
	public void setManager(String manager) {
		this.manager = manager;
				
	}

	@Override
	public String toStringFull() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProjectDAO other = (ProjectDAO) obj;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        return true;
    }
	
	
    
    /*
     * Prepared Statements 
     */
    public PreparedStatement setLastNamePrepStatement(Date lastName,Date firstName, Integer personId) throws SQLException{
    	if(startDate.equals(null))throw new SQLException("Missing start date.");
    	if(endDate.equals(null))throw new SQLException("Missing end date.");
    	if(projectId.equals(null))throw new SQLException("Missing project Id");
    	if(connection == null)throw new SQLException("Connection Error");
    	
        String sql = "UPDATE project SET startDate = ?, endDate = ? where projectID = ?;";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setDate(1, (java.sql.Date) startDate);
        prep.setDate(2, (java.sql.Date) endDate);
        prep.setInt(3, projectId);
        return prep;
    }

	@Override
	public void setDatabase(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
		this.connection = this.databaseManager.getConnection();
		
	}

	@Override
    public void save() throws SQLException {
        PreparedStatement prep = setLastNamePrepStatement(this.startDate, this.endDate, this.projectId);
        prep.executeUpdate();
        prep.close();
    }

	@Override
	public void setProjectId(Integer projectId) {
		this.projectId=projectId;
		
	}

	@Override
	public void setProjectId() {
		// TODO Auto-generated method stub
		
	}

	

}
