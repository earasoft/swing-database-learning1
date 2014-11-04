package com.earasoft.db.database.manager;

import org.apache.commons.configuration.Configuration;

public class DatabaseManagerBuilder {
	private static DatabaseManagerImpl databaseManager;
	
	public static DatabaseManagerImpl getDatabaseManager(Configuration storageConfig) throws Exception{
		if(databaseManager == null){
			return databaseManager = new DatabaseManagerImpl(storageConfig);
		}else{
			return databaseManager;
		}
	}
	
	
}
