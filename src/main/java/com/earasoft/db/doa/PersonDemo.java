package com.earasoft.db.doa;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.earasoft.db.DatabaseManager;
import com.earasoft.db.DatabaseManagerBuilder;

public class PersonDemo {

	public static void main(String[] args) throws Exception {
		PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
		DatabaseManager DbMgrDemo = DatabaseManagerBuilder.getDatabaseManager(propertiesConfiguration);
		//DbMgrDemo.clearDatabase();
		DbMgrDemo.openDatabase();
		
		PeopleI personDAO = new PeopleDAO(DbMgrDemo);
		
		//System.out.println(personDAO.checkIfPersonExist("1549490016"));
		
		personDAO.addPerson(new PersonDAO("first","R4","445-555-444"));
		personDAO.addPerson(new PersonDAO("f54","R4","555-555-44"));
		
		for(PersonDAO person : personDAO.getPeople()){
			System.out.println(person.toStringFull());
		}
		//System.out.println(personDAO.getPeopleByLastname("Rive%"));
		
	}

}
