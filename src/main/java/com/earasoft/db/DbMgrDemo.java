package com.earasoft.db;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DbMgrDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
		DatabaseManager DbMgrDemo = new DatabaseManager(propertiesConfiguration);
		DbMgrDemo.openDatabase();
		
	}

}
