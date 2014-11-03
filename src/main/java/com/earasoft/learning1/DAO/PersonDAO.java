package com.earasoft.learning1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonDAO {
    private Integer personId;
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

	public PersonDAO(Integer personId, String firstName, String lastName,
            String phoneNumber, Connection connection) {
	    this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.connection = connection;
    }

	
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        //this.personId = personId;
    }

    @Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	public String toStringFull() {
		return "Person [personId = "+ personId +", firstName=" + firstName + ", lastName=" + lastName
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
	
	public void setLastName(String lastName) throws SQLException {
	    this.lastName = lastName;
	    
		String sql = "UPDATE person SET lastName = ? where personId = ?;";
		
		PreparedStatement prep = connection.prepareStatement(sql);
		
		prep.setString(1, this.lastName);
		prep.setInt(2, this.personId);
		
		prep.close();
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
                + ((personId == null) ? 0 : personId.hashCode());
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
        if (personId == null) {
            if (other.personId != null)
                return false;
        } else if (!personId.equals(other.personId))
            return false;
        return true;
    }
	
}
