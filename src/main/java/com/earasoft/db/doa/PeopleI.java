package com.earasoft.db.doa;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PeopleI {
    
    public abstract ArrayList<PersonDAO> getPeople() throws SQLException;
    
    public abstract PersonI getPerson(PersonI person) throws Exception;
    
    public abstract PersonI addPerson(PersonI person) throws SQLException;
    
    public abstract ArrayList<PersonDAO> getPeopleByLastname(
            String lastNameSearch) throws SQLException;
    
}