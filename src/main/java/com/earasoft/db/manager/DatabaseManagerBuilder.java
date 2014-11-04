package com.earasoft.db.manager;

import org.apache.commons.configuration.Configuration;

public class DatabaseManagerBuilder {
	private static DatabaseManager databaseManager;
	
	public static DatabaseManager getDatabaseManager(Configuration storageConfig) throws Exception{
		if(databaseManager == null){
			return databaseManager = new DatabaseManager(storageConfig);
		}else{
			return databaseManager;
		}
	}
	
	
}
