package com.earasoft.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import com.earasoft.db.database.manager.DatabaseManager;

public interface Resource {
	//Resource Id
	public Integer resourceId(); //Getter
	public Integer resourceId(Integer resourceId); // Setter
	
	//Team
	public String resourceTeam(); // Getter
	public String resourceTeam(String resourceTeam); //Setter
	
	//Works On Project
	public Set<Project> worksOnProjects(); //Getter
	

	//First Name
    public String firstName();
    public String firstName(String firstName);
    
    //Last Name
    public String lastName();
    public String lastName(String lastName);
    
    //Phone Number
    public String getPhoneNumber();
    public String setPhoneNumber(String phoneNumber);

    
    public abstract String toString();
	public abstract String toStringFull();

	public abstract void setDatabase(DatabaseManager databaseManager);
	public abstract void setConnection(Connection connection);
	public abstract void save()  throws SQLException;
	
}
