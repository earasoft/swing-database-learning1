package com.earasoft.db.dao;

import java.sql.SQLException;

import com.earasoft.db.database.manager.DatabaseManager;
import com.earasoft.db.security.RolesE;

public interface Person {
    
	//PersonId
    public abstract Integer getPersonId();
    public abstract void setPersonId(Integer personId);
    
    //FirstName
    public abstract String getFirstName();
    public abstract void setFirstName(String firstName);
    
    //LastName
    public abstract String getLastName();
    public abstract void setLastName(String lastName);
    
    //PhoneNumber
    public abstract String getPhoneNumber();
    public abstract void setPhoneNumber(String phoneNumber);

    //Address
    public abstract String getAddress();
    public abstract void setAddress(String address);
    
    //City
    public abstract String getCity();
    public abstract void setCity(String city);
    
    //State
    public abstract String getState();
    public abstract void setState(String state);
    
    //Zip
    public abstract String getZip();
    public abstract void setZip(String zip);
    
    //Position
    public abstract String getPosition();
    public abstract void setPosition(String position);
    
    //Team
    public abstract String getTeam();
    public abstract void setTeam(String team);
    
    //Role
    public abstract String getRole();
    public abstract void setRole(RolesE role);
    
    //Password
    public abstract String getPassword();
    public abstract void setPassword(String password);
    
    //Database and Other
	public abstract String toStringFull();
	public abstract void setDatabase(DatabaseManager databaseManager);
	public abstract void save()  throws SQLException;
}