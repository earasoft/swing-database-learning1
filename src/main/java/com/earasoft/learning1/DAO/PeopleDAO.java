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
	
	public ArrayList<PersonDAO> getPeople(){
		ArrayList<PersonDAO> people = new ArrayList<PersonDAO>();
		
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);  // set timeout to 30 sec.
			ResultSet rs = statement.executeQuery("SELECT firstName, lastName, phoneNumber FROM person");
			
			while(rs.next()){
				// read the result set
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				
				people.add(new PersonDAO(firstName, lastName, phoneNumber,this.connection));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return people;
	}
	
	public PersonDAO checkIfPersonExist(String hashCode){
		return PeopleUtils.checkIfPersonExist(hashCode, connection);
	}
	
	public PersonDAO getPerson(PersonDAO person) throws Exception{
		PersonDAO personDAO = checkIfPersonExist(person.hashCode()+"");
		if(personDAO != null){
			return personDAO;
		}else{
			throw new Exception("Person Not Found");
		}
		
	}
	
	public PersonDAO addPerson(PersonDAO person) throws Exception{
		System.out.println("addPerson");
		Statement statement;
		try {
			PersonDAO currentPerson = checkIfPersonExist(person.hashCode()+"");
			if( currentPerson != null){
				throw new Exception("Person Aleady Exist");
			}
					
			statement = connection.createStatement();
			statement.setQueryTimeout(10);  // set timeout to 30 sec.
			String sql = "INSERT INTO person (hashCode, firstName, lastName, phoneNumber) VALUES (?,?,?,?);";
			PreparedStatement prep = connection.prepareStatement(sql);
			prep = connection.prepareStatement(sql);
			prep.setString(1, person.hashCode()+"");
			prep.setString(2, person.getFirstName());
			prep.setString(3, person.getLastName());
			prep.setString(4, person.getPhoneNumber());
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		person.setConnection(connection);
		return person;
	}
	
	
	public ArrayList<PersonDAO> getPeopleByLastname(String lastNameSearch){
		ArrayList<PersonDAO> people = new ArrayList<PersonDAO>();
		try {
			String sql = "SELECT firstName, lastName, phoneNumber FROM person WHERE lastname like ? ;";
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, lastNameSearch);
			
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				// read the result set
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				
				people.add(new PersonDAO(firstName, lastName, phoneNumber, this.connection));
			}
			
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return people;
	}
	
}
