package com.earasoft.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earasoft.db.dao.Person;
import com.earasoft.db.database.manager.DatabaseManager;

public class PersonDAO implements Person {
    private Integer personId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Connection connection;
	private DatabaseManager databaseManager;
	
	public PersonDAO() {
		this.firstName = "";
		this.lastName = "";
		this.phoneNumber = "";	
	}
	
	
	public PersonDAO(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public PersonDAO(String firstName, String lastName, String phoneNumber, DatabaseManager databaseManager){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.databaseManager = databaseManager;
        this.connection = this.databaseManager.getConnection();
	}

	public PersonDAO(Integer personId, String firstName, String lastName,
            String phoneNumber, DatabaseManager databaseManager) {
	    this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.databaseManager = databaseManager;
        this.connection = this.databaseManager.getConnection();
    }

	
    /* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#getPersonId()
     */
    @Override
    public Integer getPersonId() {
        return personId;
    }

    /* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#setPersonId(java.lang.Integer)
     */
    @Override
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
	public String toString() {
		return lastName + ", "+ firstName;
	}
	
	public String toStringFull() {
		return "Person [personId = "+ personId +", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#getFirstName()
     */
	@Override
    public String getFirstName() {
		return firstName;
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#setFirstName(java.lang.String)
     */
	@Override
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#getLastName()
     */
	@Override
    public String getLastName() {
		return lastName;
	}
	
	private Person checkIfPersonExist(Integer personId){
		return PeopleUtils.checkIfPersonExist(personId, this.databaseManager);
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#setLastName(java.lang.String)
     */
	@Override
    public void setLastName(String lastName){
	    this.lastName = lastName;
	}
	
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#getPhoneNumber()
     */
	@Override
    public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#setPhoneNumber(java.lang.String)
     */
	@Override
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
    
    
    @Override
    public void save() throws SQLException {
        PreparedStatement prep = setLastNamePrepStatement(this.lastName, this.firstName, this.personId);
        prep.executeUpdate();
        prep.close();
    }
    
    /*
     * Prepared Statements 
     */
    public PreparedStatement setLastNamePrepStatement(String lastName,String firstName, Integer personId) throws SQLException{
    	if(lastName.equals(null))throw new SQLException("Missing last name");
    	if(firstName.equals(null))throw new SQLException("Missing firstname");
    	if(personId.equals(null))throw new SQLException("Missing person Id");
    	if(connection == null)throw new SQLException("Connection Error");
    	
        String sql = "UPDATE person SET lastName = ?, firstName = ? where personId = ?;";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setString(1, lastName);
        prep.setString(2, firstName);
        prep.setInt(3, personId);
        return prep;
    }

	@Override
	public void setDatabase(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
		this.connection = this.databaseManager.getConnection();
	}


	
}
