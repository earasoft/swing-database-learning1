package com.earasoft.db.dao.impl

import java.sql.SQLException;
import java.util.List;

import com.earasoft.db.dao.PeopleG;
import com.earasoft.db.dao.PersonG;
import com.earasoft.db.dao.helpers.GBase;

class PeopleDaoG extends GBase implements PeopleG{

	@Override
	public List<PersonG> getPeople() throws SQLException {
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonG getPersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonG getPersonByIds(List<Integer> idList) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonG> getPeopleByLastname(String lastNameSearch)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonG deletePersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonG deletePersonByIsds(List<Integer> idList) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonG addPerson(PersonG PersonG) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
