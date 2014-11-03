package com.earasoft.learning1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleUtils {

	public static PersonDAO checkIfPersonExist(String hashCode, Connection connection){
		try {
			String sql = "SELECT firstName, lastName, phoneNumber FROM person WHERE hashcode like ? ;";
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, hashCode+"");
			
			ResultSet rs = prep.executeQuery();
			
			boolean exist = rs.next();
			PersonDAO person = null;
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
