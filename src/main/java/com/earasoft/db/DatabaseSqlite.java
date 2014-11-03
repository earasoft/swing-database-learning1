package com.earasoft.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseSqlite implements DatabaseI {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseSqlite.class);
    
	public static final String FILENAME_KEY = "filename";
    public static final String FILENAME_KEY_DEFAULT = "data.db";
    	
    private final String filename;
    
	public DatabaseSqlite(Configuration storageConfig){
		this.filename = storageConfig.getString(FILENAME_KEY, FILENAME_KEY_DEFAULT).trim();
	}

	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.filename));
	}

	@Override
	public String getName() {
		return String.format("[]");
	}

    @Override
    public void init(Connection currentConnection) throws SQLException {
        Statement statement = currentConnection.createStatement();
        statement.setQueryTimeout(10);
        statement.executeUpdate(SQLStrings.CREATE_PERSON_TABLE);
        statement.close();
    }
}
