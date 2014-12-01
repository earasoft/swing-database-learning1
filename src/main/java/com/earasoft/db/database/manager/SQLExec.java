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
		
		public static final String CREATE_TIMESHEET_TABLE = "CREATE TABLE IF NOT EXISTS timesheet (taskID INTEGER PRIMARY KEY, resourceID INTEGER PRIMARY KEY, dateSubmitted date, hoursPerTask INTEGER);";
		
		public static final	String CREATE_PROJECT_TABLE = "CREATE TABLE IF NOT EXISTS project (projectID INTEGER PRIMARY KEY, taskID INTEGER, startDate date, endDate date);";
		
		public static final String CREATE_TASK_TABLE = "CREATE TABLE IF NOT EXISTS task (taskID INTEGER PRIMARY KEY, startDate date, endDate date, remainingHours INTEGER);";
		
		public static final String CREATE_CONTAIN_TABLE = "CREATE TABLE IF NOT EXISTS contain (taskID INTEGER PRIMARY KEY, resourceID INTEGER PRIMARY KEY, dateSubmitted date PRIMARY KEY, FOREIGN KEY taskID REFERENCES task(taskID), FOREIGN KEY resouceID REFERENCES resouce(resourceID), FOREIGN KEY dateSubmitted RESOURCES timesheet(dateSubmitted));";
		
		public static final String CREATE_ASSIGNEDTO_TABLE = "CREATE TABLE IF NOT EXISTS assignedTo (taskID INTEGER PRIMARY KEY, resourceID INTEGER PRIMARY KEY, FOREIGN KEY taskID REFERENCES task(taskID), FOREIGN KEY resouceID REFERENCES resouce(resourceID));";
		
	}
	
	static class MySQL{
		public static final String CREATE_PERSON_TABLE = Sqlite.CREATE_PERSON_TABLE.replace("AUTOINCREMENT", "AUTO_INCREMENT");
		public static final String CREATE_PERSON_INDEX_LAST_NAME ="";
		
		public static final String CREATE_RESOURCE_TABLE = "CREATE TABLE IF NOT EXISTS resource(resourceID INTEGER, resoureceTeam varchar(25), dateOfBirth date, fName varchar(25), lName varchar(25), PRIMARY KEY (resourceID));";
		
		public static final String CREATE_TIMESHEET_TABLE = "CREATE TABLE IF NOT EXISTS timesheet (resourceID INTEGER, dateSubmitted date, hoursPerTask INTEGER, PRIMARY KEY (resourceID, dateSubmitted));";
		
		public static final	String CREATE_PROJECT_TABLE = "CREATE TABLE IF NOT EXISTS project (projectID INTEGER PRIMARY KEY, taskID INTEGER, startDate date, endDate date);";
		
		public static final String CREATE_TASK_TABLE = "CREATE TABLE IF NOT EXISTS task (taskID INTEGER, startDate date, endDate date, remainingHours INTEGER, PRIMARY KEY (taskID));";
		
		public static final String CREATE_CONTAIN_TABLE = "CREATE TABLE IF NOT EXISTS contain (taskID INTEGER, resourceID INTEGER, dateSubmitted date,PRIMARY KEY (taskID, resourceID, dateSubmitted));";
		
		public static final String CREATE_ASSIGNEDTO_TABLE = "CREATE TABLE IF NOT EXISTS assignedTo (taskID INTEGER, resourceID INTEGER, PRIMARY KEY (taskID, resourceID));";
		
		public static final String ALTER_CONTAIN_TABLE = "ALTER TABLE contain ADD FOREIGN KEY (taskID) REFERENCES task(taskID), ADD FOREIGN KEY (resourceID) REFERENCES resource(resourceID), ADD FOREIGN KEY (dateSubmitted) REFERENCES timesheet(dateSubmitted);";
		public static final String ALTER_ASSIGNEDTO_TABLE = "ALTER TABLE assignedTo ADD FOREIGN KEY (resourceID) REFERENCES resource(resourceID), ADD FOREIGN KEY (taskID) REFERENCES task(taskID);";
	}
	
	
    public static final String DROP_PERSON_TABLE = "DROP TABLE IF exists person";    
    public static final String DROP_USER_TABLE = "DROP TABLE IF exists user";
    
    public void initialize(Connection currentConnection) throws SQLException{
        Statement statement = currentConnection.createStatement();
        statement.setQueryTimeout(10);
        
        if(database.equals("sqlite")){
 //          statement.executeUpdate(SQLExec.Sqlite.CREATE_PERSON_TABLE);
//           statement.executeUpdate(SQLExec.Sqlite.CREATE_USER_TABLE);
         
        }else if(database.equals("mysql")){
        	statement.executeUpdate(SQLExec.MySQL.CREATE_RESOURCE_TABLE);
            statement.executeUpdate(SQLExec.MySQL.CREATE_PERSON_TABLE);
//            statement.executeUpdate(SQLExec.MySQL.CREATE_USER_TABLE);
            statement.executeUpdate(SQLExec.MySQL.CREATE_TASK_TABLE);
            statement.executeUpdate(SQLExec.MySQL.CREATE_PROJECT_TABLE);
            statement.executeUpdate(SQLExec.MySQL.CREATE_TIMESHEET_TABLE);            
            statement.executeUpdate(SQLExec.MySQL.CREATE_CONTAIN_TABLE);
            statement.executeUpdate(SQLExec.MySQL.CREATE_ASSIGNEDTO_TABLE);
            statement.executeUpdate(SQLExec.MySQL.ALTER_ASSIGNEDTO_TABLE);
            statement.executeUpdate(SQLExec.MySQL.ALTER_CONTAIN_TABLE);
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
