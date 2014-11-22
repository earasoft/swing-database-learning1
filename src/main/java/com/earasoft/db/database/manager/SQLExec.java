package com.earasoft.db.database.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLExec {
	private static final Logger logger = LoggerFactory.getLogger(SQLExec.class);
	
	final private String database;
	
	public SQLExec(String database) {
		super();
		this.database = database;
	}
	
	static class Sqlite{
		public static final String CREATE_PERSON_TABLE = "CREATE TABLE IF NOT EXISTS person (personId INTEGER PRIMARY KEY AUTOINCREMENT, firstName varchar(100), lastName varchar(255), phoneNumber varchar(32));";
		public static final String CREATE_PERSON_INDEX_LAST_NAME = "CREATE INDEX personByLastName ON person (lastName ASC);";
		
		public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS user (userId INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(100), password varchar(255), role varchar(32));";
		public static final String CREATE_USER_INDEX_USERNAME = "CREATE INDEX userByUsername ON user (username ASC);";
	}
	
	static class MySQL{
		public static final String CREATE_PERSON_TABLE = Sqlite.CREATE_PERSON_TABLE.replace("AUTOINCREMENT", "AUTO_INCREMENT");
		public static final String CREATE_PERSON_INDEX_LAST_NAME ="";
		
		public static final String CREATE_USER_TABLE = Sqlite.CREATE_USER_TABLE.replace("AUTOINCREMENT", "AUTO_INCREMENT");
		
	}
	
    public static final String DROP_PERSON_TABLE = "DROP TABLE IF exists person";    
    public static final String DROP_USER_TABLE = "DROP TABLE IF exists user";
    
    public void initialize(Connection currentConnection) throws SQLException{
        Statement statement = currentConnection.createStatement();
        statement.setQueryTimeout(10);
        
        if(database.equals("sqlite")){
            statement.executeUpdate(SQLExec.Sqlite.CREATE_PERSON_TABLE);
            statement.executeUpdate(SQLExec.Sqlite.CREATE_USER_TABLE);
        }else if(database.equals("mysql")){
            statement.executeUpdate(SQLExec.MySQL.CREATE_PERSON_TABLE);
            statement.executeUpdate(SQLExec.MySQL.CREATE_USER_TABLE);
        }
        
        statement.close();
        logger.debug("Finished Creating Tables and Indexes for database (" + this.database + ")");
    }
    
    public void clear(Connection currentConnection) throws SQLException{
		Statement statement = currentConnection.createStatement();
		statement.setQueryTimeout(10);  // set timeout to 10 sec.
		statement.executeUpdate(SQLExec.DROP_PERSON_TABLE);
		statement.executeUpdate(SQLExec.DROP_USER_TABLE);
		statement.close();
		logger.debug("Finished Dropping tables for database (" + this.database + ")");
    }
    
}
