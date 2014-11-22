package com.earasoft.db.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earasoft.learning1.Settings;

public class MySQLDatabase implements Database {
	private static final Logger logger = LoggerFactory.getLogger(MySQLDatabase.class);

	public static final String HOSTNAME_KEY = "hostname";
    public static final String HOSTNAME_KEY_DEFAULT = "127.0.0.1";
    
	public static final String DATABASE_KEY = "database";
    public static final String DATABASE_KEY_DEFAULT = "database";
    
	public static final String PORT_KEY = "port";
    public static final String PORT_KEY_DEFAULT = "127.0.0.1";
    
	public static final String USERNAME_KEY = "username";
    public static final String USERNAME_KEY_DEFAULT = "root";
    
	public static final String PASSWORD_KEY = "password";
    public static final String PASSWORD_KEY_DEFAULT = "password";
    
    protected final String hostname;
    protected final String port;
    protected final String database;
    protected final String username;
    protected final String password;
    protected final String url;
    	
	public MySQLDatabase(Configuration storageConfig){
		 this.hostname = storageConfig.getString(HOSTNAME_KEY, HOSTNAME_KEY_DEFAULT).trim();
		 this.port = storageConfig.getString(PORT_KEY, PORT_KEY_DEFAULT).trim();
		 this.database = storageConfig.getString(DATABASE_KEY, DATABASE_KEY_DEFAULT).trim(); 
		 this.username = storageConfig.getString(USERNAME_KEY, USERNAME_KEY_DEFAULT).trim();
		 this.password = storageConfig.getString(PASSWORD_KEY, PASSWORD_KEY_DEFAULT).trim();
		 this.url = String.format("jdbc:mysql://%s:%s/%s", this.hostname, this.port, this.database);
		 logger.info("Connecting to Database [MySql ("+ this.url + ")]");
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public String getName() {
	    return String.format("mysql");
	}
	
}
