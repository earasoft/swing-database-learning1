package com.earasoft.db.dao

import java.sql.SQLException
import java.util.List

import com.earasoft.db.dao.PersonG;

public interface PeopleG{

	/**
	 * Return a list of people
	 * @return
	 * @throws SQLException
	 */
	public abstract List<PersonG> getPeople() throws SQLException
	
	public abstract PersonG getPersonById(Integer id) throws SQLException
	
	public abstract PersonG getPersonByIds(List<Integer> idList) throws SQLException
	
	public abstract List<PersonG> getPeopleByLastname(String lastNameSearch) throws SQLException
	
	public abstract PersonG deletePersonById(Integer id) throws SQLException
	
	public abstract PersonG deletePersonByIsds(List<Integer> idList) throws SQLException
	
	public abstract PersonG addPerson(PersonG PersonG) throws SQLException
	
}
