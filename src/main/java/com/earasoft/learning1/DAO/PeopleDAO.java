package com.earasoft.learning1.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PeopleDAO {
	private final Connection connection;
	
	//TODO Change this to Database Class
	public PeopleDAO(Connection connection){
		this.connection = connection;
	}
	
	public ArrayList<PersonDAO> getPeople() throws SQLException{
	    PreparedStatement prep = getPeoplePrepStatement();
		ResultSet rs = prep.executeQuery();
		
		ArrayList<PersonDAO> people = resultSetPeopleListFillerHelper(rs);
		
		rs.close();
		prep.close();

		return people;
	}
	
	public PersonDAO checkIfPersonExist(String hashCode){
		return PeopleUtils.checkIfPersonExist(hashCode, connection);
	}
	
	public PersonDAO getPerson(PersonDAO person) throws Exception{
		PersonDAO personDAO = checkIfPersonExist(person+"");
		if(personDAO != null){
			return personDAO;
		}else{
			throw new Exception("Person Not Found");
		}
	}
	
	public PersonDAO addPerson(PersonDAO person) throws SQLException{
		PreparedStatement prep = addPersonPrepStatement(person.getFirstName(), person.getLastName(), person.getPhoneNumber());		
		prep.executeUpdate();
		prep.close();
		person.setConnection(connection);
		return person;
	}
	
	
	public ArrayList<PersonDAO> getPeopleByLastname(String lastNameSearch) throws SQLException{
		PreparedStatement prep = getPeopleByLastnamePrepStatement(lastNameSearch);
		ResultSet rs = prep.executeQuery();
		
		ArrayList<PersonDAO> peopleList  = resultSetPeopleListFillerHelper(rs);
		
		rs.close();
		prep.close();
		return peopleList;
	}
	
	 /*
     * Helper Methods
     */
	 
	public ArrayList<PersonDAO> resultSetPeopleListFillerHelper(ResultSet rs) throws SQLException{
	       ArrayList<PersonDAO> peopleList = new ArrayList<PersonDAO>();
	       
	       while(rs.next()){
	            Integer personId = rs.getInt("personId");
	            String firstName = rs.getString("firstName");
	            String lastName = rs.getString("lastName");
	            String phoneNumber = rs.getString("phoneNumber");
	            peopleList.add(new PersonDAO(personId, firstName, lastName, phoneNumber, this.connection));
	        }
	       
	       return peopleList;
	}
	
	/*
	 * Prepared Statements 
	 */
	
    public PreparedStatement addPersonPrepStatement(String firstName, String lastName, String phoneNumber) throws SQLException{
        String sql = "INSERT INTO person (firstName, lastName, phoneNumber) VALUES (?,?,?);";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setQueryTimeout(10);
        prep.setString(1, firstName);
        prep.setString(2, lastName);
        prep.setString(3, phoneNumber);
        return prep;
    }
    
    public PreparedStatement getPeopleByLastnamePrepStatement(String lastNameSearch) throws SQLException{
        String sql = "SELECT personId, firstName, lastName, phoneNumber FROM person WHERE lastName like ?;";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setString(1, lastNameSearch);
        return prep;
    }
    
    public PreparedStatement getPeoplePrepStatement() throws SQLException{
        String sql = "SELECT personId, firstName, lastName, phoneNumber FROM person;";
        PreparedStatement prep = connection.prepareStatement(sql);
        return prep;
    }
    
    //SELECT personId, firstName, lastName, phoneNumber FROM person WHERE personId IN (1,3,5)
    
}
