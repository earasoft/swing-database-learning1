package com.earasoft.db.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqliteDatabase implements Database {
	private static final Logger logger = LoggerFactory.getLogger(SqliteDatabase.class);
    
	public static final String FILENAME_KEY = "filename";
    public static final String FILENAME_KEY_DEFAULT = "data.db";
    	
    private final String filename;
    
	public SqliteDatabase(Configuration storageConfig){
		this.filename = storageConfig.getString(FILENAME_KEY, FILENAME_KEY_DEFAULT).trim();
		logger.info("Connecting to Database [SQLite ("+ this.filename + ")]");
	}

	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.filename));
	}

	@Override
	public String getName() {
		return String.format("sqlite");
	}

}
