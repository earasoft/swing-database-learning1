package com.earasoft.db.dao;

import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement
import org.apache.commons.configuration.PropertiesConfiguration;

import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.impl.PeopleDAO;
import com.earasoft.db.dao.impl.PeopleDaoG;
import com.earasoft.db.dao.impl.PersonDAO;
import com.earasoft.db.database.manager.DatabaseManager
import com.earasoft.db.database.manager.DatabaseManagerImpl;
import com.earasoft.db.database.manager.DatabaseManagerBuilder;

public class PersonDemo {

	static List<Map> genericSelectMap(DatabaseManager databaseManager, String tableName){
		List<Map> entries = []
		
		Statement st = databaseManager.getConnection().createStatement()
		ResultSet rs = st.executeQuery("SELECT * from " +  tableName);
		
		ResultSetMetaData md = rs.getMetaData();

		int colCount = md.getColumnCount();
		def Header = [:];

		for (int i = 1; i <= colCount; i++){
			Header[i] = md.getColumnName(i);
		}
		
		while (rs.next()) {
			Map entry = [:]
			Header.each{ columnId, columnName ->
				entry[Header[columnId]] =  rs.getString(columnId)
			}
		
			entries.add(entry)
		}
		
		rs.close()
		st.close()
		return entries
	}
	
	public static void main(String[] args) throws Exception {
		PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
		DatabaseManagerImpl DbMgrDemo = DatabaseManagerBuilder.getDatabaseManager(propertiesConfiguration);
		//DbMgrDemo.clearDatabase();
		DbMgrDemo.openDatabase();
		
		PeopleG people = new PeopleDaoG()
		people.setDatabaseManager(DbMgrDemo)
		
		println people.getPeople()
		
		println genericSelectMap(DbMgrDemo, "person")
		
		
		//System.out.println(personDAO.checkIfPersonExist("1549490016"));
		
//		//personDAO.addPerson(new PersonDAO("first","R4","445-555-4446"));
//		for(int i = 1; i<=1; i++){
//			personDAO.addPerson(new PersonDAO("f54","R4","555-555-4444"));
//			System.out.println("person: " + i);
//		}
//		
//		
//		personDAO.getPeople();
//		personDAO.getPeople();
//		personDAO.getPeople();
//		for(Person person : personDAO.getPeople()){
//			System.out.println(person.toStringFull());
//		}
		//System.out.println(personDAO.getPeopleByLastname("Rive%"));
		
		DbMgrDemo.getConnection().commit();
	}

}
