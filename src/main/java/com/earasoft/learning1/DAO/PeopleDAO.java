package com.earasoft.learning1.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeopleDAO {
	private final Connection connection;
	
	public PeopleDAO(Connection connection){
		this.connection = connection;
	}
	
	public ArrayList<PersonDAO> getPeople() throws SQLException{
		ArrayList<PersonDAO> people = new ArrayList<PersonDAO>();
		
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(10);
		ResultSet rs = statement.executeQuery("SELECT personId, firstName, lastName, phoneNumber FROM person");
		
		while(rs.next()){
		    Integer personId = rs.getInt("personId");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String phoneNumber = rs.getString("phoneNumber");
			
			people.add(new PersonDAO(personId, firstName, lastName, phoneNumber,this.connection));
		}
		rs.close();
		statement.close();

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
		String sql = "INSERT INTO person (firstName, lastName, phoneNumber) VALUES (?,?,?);";
		
		PreparedStatement prep = connection.prepareStatement(sql);
		prep.setQueryTimeout(10);

		prep.setString(1, person.getFirstName());
		prep.setString(2, person.getLastName());
		prep.setString(3, person.getPhoneNumber());
		
		prep.executeUpdate();
		
		prep.close();
		person.setConnection(connection);
		return person;
	}
	
	
	public ArrayList<PersonDAO> getPeopleByLastname(String lastNameSearch) throws SQLException{
		ArrayList<PersonDAO> people = new ArrayList<PersonDAO>();
		
		String sql = "SELECT personId, firstName, lastName, phoneNumber FROM person WHERE lastname like ? ;";
		PreparedStatement prep = connection.prepareStatement(sql);
		prep.setString(1, lastNameSearch);
		
		ResultSet rs = prep.executeQuery();
		
		while(rs.next()){
		    Integer personId = rs.getInt("personId");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String phoneNumber = rs.getString("phoneNumber");
			people.add(new PersonDAO(personId, firstName, lastName, phoneNumber, this.connection));
		}
		
		rs.close();
		prep.close();
		return people;
	}
	
}
