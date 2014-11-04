package com.earasoft.db.doa;

import java.sql.Connection;
import java.sql.SQLException;

public interface PersonI {
    
    public abstract Integer getPersonId();
    
    public abstract void setPersonId(Integer personId);
    
    public abstract String getFirstName();
    
    public abstract void setConnection(Connection connection);
    
    public abstract void setFirstName(String firstName) throws SQLException;
    
    public abstract String getLastName();
    
    public abstract void setLastName(String lastName) throws SQLException;
    
    public abstract String getPhoneNumber();
    
    public abstract void setPhoneNumber(String phoneNumber) throws SQLException;
    
}