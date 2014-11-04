package com.earasoft.db.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import com.earasoft.db.PeopleI;
import com.earasoft.db.PersonI;
import com.earasoft.db.manager.DatabaseManager;

public class PeopleDAO implements PeopleI {
	private final Connection connection;
	private final DatabaseManager databaseManager;
	
	public PeopleDAO(DatabaseManager databaseManager){
	    this.databaseManager = databaseManager;
		this.connection = this.databaseManager.getConnection();
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PeopleDAOI#getPeople()
     */
	@Override
    public List<PersonI> getPeople() throws SQLException{
	    PreparedStatement prep = getPeoplePrepStatement();
		ResultSet rs = prep.executeQuery();
		
		List<PersonI> people = resultSetPeopleListFillerHelper(rs);
		
		rs.close();
		prep.close();

		return people;
	}
	
	public PersonI checkIfPersonExist(String hashCode){
		return PeopleUtils.checkIfPersonExist(hashCode, connection);
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PeopleDAOI#getPerson(com.earasoft.db.doa.PersonDAO)
     */
	@Override
    public PersonI getPerson(PersonI person) throws Exception{
		PersonI personDAO = checkIfPersonExist(person+"");
		if(personDAO != null){
			return personDAO;
		}else{
			throw new Exception("Person Not Found");
		}
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PeopleDAOI#addPerson(com.earasoft.db.doa.PersonDAO)
     */
	@Override
    public PersonI addPerson(PersonI person) throws SQLException{
		PreparedStatement prep = addPersonPrepStatement(person.getFirstName(), person.getLastName(), person.getPhoneNumber());		
		prep.executeUpdate();
		prep.close();
		person.setConnection(connection);
		return person;
	}
	
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PeopleDAOI#getPeopleByLastname(java.lang.String)
     */
	@Override
    public List<PersonI> getPeopleByLastname(String lastNameSearch) throws SQLException{
		PreparedStatement prep = getPeopleByLastnamePrepStatement(lastNameSearch);
		ResultSet rs = prep.executeQuery();
		
		List<PersonI> peopleList  = resultSetPeopleListFillerHelper(rs);
		
		rs.close();
		prep.close();
		return peopleList;
	}
	
	 /*
     * Helper Methods
     */
	private List<PersonI> resultSetPeopleListFillerHelper(ResultSet rs) throws SQLException{
	       List<PersonI> peopleList = new ArrayList<PersonI>();
	       
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
        prep.setQueryTimeout(10);
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
	public PersonI getPersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonI deletePersonById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
    //SELECT personId, firstName, lastName, phoneNumber FROM person WHERE personId IN (1,3,5)
    
}
