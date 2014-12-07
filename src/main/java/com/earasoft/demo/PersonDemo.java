package com.earasoft.demo;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.impl.PeopleDAO;
import com.earasoft.db.dao.impl.PersonDAO;
import com.earasoft.db.database.manager.DatabaseManagerImpl;
import com.earasoft.db.database.manager.DatabaseManagerBuilder;

public class PersonDemo {

	public static void main(String[] args) throws Exception {
		PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
		
		DatabaseManagerImpl DbMgrDemo = DatabaseManagerBuilder.getDatabaseManager(propertiesConfiguration);
		//DbMgrDemo.clearDatabase();
		DbMgrDemo.openDatabase();
		
		
		People personDAO = DbMgrDemo.getPeople();
		
		//System.out.println(personDAO.checkIfPersonExist("1549490016"));
		
		//personDAO.addPerson(new PersonDAO("first","R4","445-555-4446"));
		for(int i = 1; i<=1000; i++){
			personDAO.addPerson(new PersonDAO("f54","R4","555-555-4444"));
			System.out.println("person: " + i);
		}
		
		
		personDAO.getPeople();
		personDAO.getPeople();
		personDAO.getPeople();
		for(Person person : personDAO.getPeople()){
			System.out.println(person.toStringFull());
		}
		//System.out.println(personDAO.getPeopleByLastname("Rive%"));
		
		DbMgrDemo.getConnection().commit();
	}

}
