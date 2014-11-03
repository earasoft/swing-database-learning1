package com.earasoft.db;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManager {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
	public static final String DATABASE_KEY = "database";
    public static final String DATABASE_KEY_DEFAULT = "sqlite";
    
	final private DatabaseI database;
	final private String databaseString;
	
	private Connection currentConnection = null;
	
	public DatabaseManager(Configuration storageConfig) throws Exception{
		this.databaseString = storageConfig.getString(DATABASE_KEY, DATABASE_KEY_DEFAULT).trim();
		if(this.databaseString.equals("sqlite")){
			this.database = new DatabaseSqlite(storageConfig.subset("database.sqlite"));
		}else if(this.databaseString.equals("mysql")){
	    	this.database = new DatabaseMySql(storageConfig.subset("database.mysql"));
		}else{
			throw new Exception("Database ["+this.databaseString+"] not found");
		}
	}
	
	public boolean isOpen(){
		if(currentConnection == null){
			return false;
		}else{
			return true;
		}
	}
	public void openDatabase() throws SQLException, ClassNotFoundException{
		if(this.isOpen() == false)
			this.currentConnection = this.database.getConnection();
		this.database.init(currentConnection);
	}
	
	public void clearDatabase() throws SQLException, ClassNotFoundException{
		if(this.isOpen() == false)
			this.currentConnection = this.database.getConnection();
		Statement statement = this.currentConnection.createStatement();
		statement.setQueryTimeout(10);  // set timeout to 10 sec.
		statement.executeUpdate(SQLStrings.DROP_PERSON_TABLE);
		statement.close();
	}
	
	public Connection getConnection(){
		return currentConnection;
	}
	
	public void close() throws SQLException{
		if(currentConnection == null){
			//No Connection to close
		}else{
			currentConnection = null;
			currentConnection.close();
		}
	}
}
