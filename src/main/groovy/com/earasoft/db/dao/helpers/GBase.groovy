package com.earasoft.db.dao.helpers

import java.sql.Connection;

import com.earasoft.db.database.manager.DatabaseManager;

class GBase {

	Connection connection
	DatabaseManager databaseManager
	

	
	public void setDatabaseManager(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager
		this.connection = this.databaseManager.getConnection()
	}
}
