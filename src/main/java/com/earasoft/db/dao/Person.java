package com.earasoft.db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.earasoft.db.database.Database;
import com.earasoft.db.database.manager.DatabaseManager;

public interface Person {
    
    public abstract Integer getPersonId();
    
    public abstract void setPersonId(Integer personId);
    
    public abstract String getFirstName();
    
    public abstract void setFirstName(String firstName);
    
    public abstract String getLastName();
    
    public abstract void setLastName(String lastName);
    
    public abstract String getPhoneNumber();
    
    public abstract void setPhoneNumber(String phoneNumber);

	public abstract String toStringFull();

	public abstract void setDatabase(DatabaseManager databaseManager);
	
	public abstract void save()  throws SQLException;
    
}