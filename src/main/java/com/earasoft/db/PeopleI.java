package com.earasoft.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PeopleI {
    
	/**
	 * Return a list of people
	 * @return
	 * @throws SQLException
	 */
    public abstract List<PersonI> getPeople() throws SQLException;
    
    public abstract PersonI getPerson(PersonI person) throws SQLException, Exception;
    
    public abstract PersonI getPersonById(Integer id) throws SQLException;
    
    public abstract PersonI deletePersonById(Integer id) throws SQLException;
    
    public abstract PersonI addPerson(PersonI person) throws SQLException;
    
    public abstract List<PersonI> getPeopleByLastname(String lastNameSearch) throws SQLException;
    
}