package com.earasoft.db.database.manager;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earasoft.db.SQLStrings;
import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.impl.PeopleDAO;
import com.earasoft.db.database.Database;
import com.earasoft.db.database.mysql.MySqlDatabase;
import com.earasoft.db.database.sqlite.SqliteDatabase;

public class DatabaseManagerImpl implements DatabaseManager {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseManagerImpl.class);
	public static final String DATABASE_KEY = "database";
    public static final String DATABASE_KEY_DEFAULT = "sqlite";
    
	final private Database database;
	final private String databaseString;
	
	private Connection currentConnection = null;
	
	public DatabaseManagerImpl(Configuration storageConfig) throws Exception{
		this.databaseString = storageConfig.getString(DATABASE_KEY, DATABASE_KEY_DEFAULT).trim();
		
		logger.info(this.databaseString);
		if(this.databaseString.equals("sqlite")){
			this.database = new SqliteDatabase(storageConfig.subset("database.sqlite"));
		}else if(this.databaseString.equals("mysql")){
	    	this.database = new MySqlDatabase(storageConfig.subset("database.mysql"));
		}else{
			throw new Exception("Database ["+this.databaseString+"] not found");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.earasoft.db.manager.DatabaseManagerI#isOpen()
	 */
	@Override
	public boolean isOpen(){
		if(currentConnection == null){
			return false;
		}else{
			return true;
		}
	}
	/* (non-Javadoc)
	 * @see com.earasoft.db.manager.DatabaseManagerI#openDatabase()
	 */
	@Override
	public void openDatabase() throws SQLException, ClassNotFoundException{
		if(this.isOpen() == false)
			this.currentConnection = this.database.getConnection();
		    this.currentConnection.setAutoCommit(false);
		this.database.init(currentConnection);
	}
	
	/* (non-Javadoc)
	 * @see com.earasoft.db.manager.DatabaseManagerI#clearDatabase()
	 */
	@Override
	public void clearDatabase() throws SQLException, ClassNotFoundException{
		if(this.isOpen() == false)
			this.currentConnection = this.database.getConnection();
	        //this.currentConnection.setAutoCommit(false);
		Statement statement = this.currentConnection.createStatement();
		statement.setQueryTimeout(10);  // set timeout to 10 sec.
		statement.executeUpdate(SQLStrings.DROP_PERSON_TABLE);
		statement.close();
	}
	
	/* (non-Javadoc)
	 * @see com.earasoft.db.manager.DatabaseManagerI#getConnection()
	 */
	@Override
	public Connection getConnection(){
		
		return currentConnection;
	}
	
	/* (non-Javadoc)
	 * @see com.earasoft.db.manager.DatabaseManagerI#close()
	 */
	@Override
	public void close() throws SQLException{
		if(currentConnection == null){
			//No Connection to close
		}else{
			currentConnection = null;
			currentConnection.close();
		}
	}

    @Override
    public People people() {
        return new PeopleDAO(this);
    }

    @Override
    public Person person() {
        // TODO Auto-generated method stub
        return null;
    }
}
