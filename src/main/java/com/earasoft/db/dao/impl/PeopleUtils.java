package com.earasoft.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.earasoft.db.dao.Person;
import com.earasoft.db.database.manager.DatabaseManager;

public class PeopleUtils {

	public static Person checkIfPersonExist(Integer personId, DatabaseManager databaseManager){
		Connection connection = databaseManager.getConnection();
		
		try {
			String sql = "SELECT firstName, lastName, phoneNumber FROM person WHERE hashcode like ? ;";
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, personId+"");
			
			ResultSet rs = prep.executeQuery();
			
			boolean exist = rs.next();
			Person person = null;
			if(exist){
				person = new PersonDAO(rs.getString("firstName"), rs.getString("lastName"), rs.getString("phoneNumber"));
				person.setConnection(connection);
			}
			//System.out.println(person.toStringFull());
			
			prep.close();
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	
}
