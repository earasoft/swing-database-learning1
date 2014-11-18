package com.earasoft.db.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;
import com.earasoft.db.database.manager.DatabaseManagerImpl;

public class PeopleDAO implements People {
	private final Connection connection;
	private final DatabaseManagerImpl databaseManager;
	
	public PeopleDAO(DatabaseManagerImpl databaseManager){
	    this.databaseManager = databaseManager;
		this.connection = this.databaseManager.getConnection();
		
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PeopleDAOI#getPeople()
     */
	@Override
    public List<Person> getPeople() throws SQLException{
	    PreparedStatement prep = getPeoplePrepStatement();
		ResultSet rs = prep.executeQuery();
		
		List<Person> people = resultSetPeopleListFillerHelper(rs);
		
		rs.close();
		prep.close();
		return people;
	}
	
	public Person checkIfPersonExist(Integer personId){
		return PeopleUtils.checkIfPersonExist(personId, this.databaseManager);
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PeopleDAOI#addPerson(com.earasoft.db.doa.PersonDAO)
     */
	@Override
    public Person addPerson(Person person) throws SQLException{
		PreparedStatement prep = addPersonPrepStatement(person.getFirstName(), person.getLastName(), person.getPhoneNumber());		
		prep.executeUpdate();
		prep.close();
		person.setDatabase(databaseManager);
		return person;
	}
	
	public void commit() throws SQLException{
	    this.connection.commit();
	}
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PeopleDAOI#getPeopleByLastname(java.lang.String)
     */
	@Override
    public List<Person> getPeopleByLastname(String lastNameSearch) throws SQLException{
		PreparedStatement prep = getPeopleByLastnamePrepStatement(lastNameSearch);
		ResultSet rs = prep.executeQuery();
		
		List<Person> peopleList  = resultSetPeopleListFillerHelper(rs);
		
		rs.close();
		prep.close();
		return peopleList;
	}
	
	 /*
     * Helper Methods
     */
	private List<Person> resultSetPeopleListFillerHelper(ResultSet rs) throws SQLException{
	       List<Person> peopleList = new ArrayList<Person>();
	       
	       while(rs.next()){
	            Integer personId = rs.getInt("personId");
	            String firstName = rs.getString("firstName");
	            String lastName = rs.getString("lastName");
	            String phoneNumber = rs.getString("phoneNumber");
	            peopleList.add(new PersonDAO(personId, firstName, lastName, phoneNumber, this.databaseManager));
	        }
	       
	       return peopleList;
	}
	
	/*
	 * Prepared Statements 
	 */
	
    private PreparedStatement addPersonPrepStatement(String firstName, String lastName, String phoneNumber) throws SQLException{
        String sql = "INSERT INTO person (firstName, lastName, phoneNumber) VALUES (?,?,?);";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setQueryTimeout(30);
        prep.setString(1, firstName);
        prep.setString(2, lastName);
        prep.setString(3, phoneNumber);
        return prep;
    }
    
    private PreparedStatement getPeopleByLastnamePrepStatement(String lastNameSearch) throws SQLException{
        String sql = "SELECT personId, firstName, lastName, phoneNumber FROM person WHERE lastName like ?;";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setString(1, lastNameSearch);
        return prep;
    }
    
    private PreparedStatement getPeoplePrepStatement() throws SQLException{
        String sql = "SELECT personId, firstName, lastName, phoneNumber FROM person;";
        PreparedStatement prep = connection.prepareStatement(sql);
        return prep;
    }

	@Override
	public Person getPersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person deletePersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByIds(List<Integer> idList) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person deletePersonByIsds(List<Integer> idList) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void rollback() throws SQLException {
        this.connection.rollback();
        
    }
    
    //SELECT personId, firstName, lastName, phoneNumber FROM person WHERE personId IN (1,3,5)
    
}
