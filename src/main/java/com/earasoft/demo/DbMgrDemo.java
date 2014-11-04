package com.earasoft.demo;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.earasoft.db.database.manager.DatabaseManagerImpl;
import com.earasoft.db.database.manager.DatabaseManager;

public class DbMgrDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
		DatabaseManager DbMgrDemo = new DatabaseManagerImpl(propertiesConfiguration);
		DbMgrDemo.openDatabase();
		
	}

}
