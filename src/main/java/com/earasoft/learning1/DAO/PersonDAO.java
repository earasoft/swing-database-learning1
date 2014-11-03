package com.earasoft.learning1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.JDBC4PreparedStatement;

public class PersonDAO {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Connection connection;
	
	public PersonDAO(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public PersonDAO(String firstName, String lastName, String phoneNumber, Connection connection){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.connection = connection;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	public String toStringFull() {
		return "Person [hashCode = "+ this.hashCode() +", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public PersonDAO checkIfPersonExist(String hashCode){
		return PeopleUtils.checkIfPersonExist(hashCode, connection);
	}
	
	public void setLastName(String lastName) {
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);  // set timeout to 10 sec.
			
			String sql = "UPDATE person SET lastName = ?, hashCode = ? where hashCode = ?;";
			PreparedStatement prep = connection.prepareStatement(sql);

			prep = connection.prepareStatement(sql);
			
			String hashCodeBefore = this.hashCode() + "";
			
			this.lastName = lastName;
			prep.setString(3, hashCodeBefore);
			prep.setString(1, this.lastName);
			prep.setString(2, this.hashCode()+"");
			
//			System.out.println(sql);
//			System.out.println(hashCodeBefore + " , " + this.lastName);
//			
//			
//			System.out.println(this.toStringFull());
//			System.out.println(this.hashCode());
			
			if(checkIfPersonExist(this.hashCode()+"") == null){
				prep.executeUpdate();
			}
			
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDAO other = (PersonDAO) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
	

}
