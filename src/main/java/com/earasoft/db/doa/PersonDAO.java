package com.earasoft.db.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.earasoft.db.DatabaseManager;

public class PersonDAO implements PersonI {
    private Integer personId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Connection connection;
	private DatabaseManager databaseManager;
	
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
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#getFirstName()
     */
	@Override
    public String getFirstName() {
		return firstName;
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#setConnection(java.sql.Connection)
     */
	@Override
    public void setConnection(Connection connection) {
		this.connection = connection;
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
	
	public PersonI checkIfPersonExist(String hashCode){
		return PeopleUtils.checkIfPersonExist(hashCode, connection);
	}
	
	/* (non-Javadoc)
     * @see com.earasoft.db.doa.PersonI#setLastName(java.lang.String)
     */
	@Override
    public void setLastName(String lastName) throws SQLException {
	    this.lastName = lastName;
		PreparedStatement prep = setLastNamePrepStatement(this.lastName, this.personId);
		prep.executeUpdate();
		prep.close();
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
    
    /*
     * Prepared Statements 
     */
    public PreparedStatement setLastNamePrepStatement(String lastName, Integer personId) throws SQLException{
        String sql = "UPDATE person SET lastName = ? where personId = ?;";
        PreparedStatement prep = connection.prepareStatement(sql);
        prep.setString(1, lastName);
        prep.setInt(2, personId);
        return prep;
    }
	
}
