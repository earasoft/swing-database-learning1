package com.earasoft.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface People {
    
	/**
	 * Return a list of people
	 * @return
	 * @throws SQLException
	 */
    public abstract List<Person> getPeople() throws SQLException;
    
    public abstract Person getPersonById(Integer id) throws SQLException;
    
    public abstract Person getPersonByIds(List<Integer> idList) throws SQLException;
    
    public abstract List<Person> getPeopleByLastname(String lastNameSearch) throws SQLException;
    
    public abstract Person deletePersonById(Integer id) throws SQLException;
    
    public abstract Person deletePersonByIsds(List<Integer> idList) throws SQLException;
    
    public abstract Person addPerson(Person person) throws SQLException;
    
    public abstract void commit() throws SQLException;
    
    public abstract void rollback() throws SQLException;
}