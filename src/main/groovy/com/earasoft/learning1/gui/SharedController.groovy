package com.earasoft.learning1.gui;

import java.io.IOException;
import java.sql.SQLException

import javax.swing.SwingWorker;

import org.apache.commons.configuration.ConfigurationException
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.impl.PeopleDAO;
import com.earasoft.db.dao.impl.PersonDAO;
import com.earasoft.db.database.manager.DatabaseManager;
import com.earasoft.db.database.manager.DatabaseManagerBuilder;
import com.earasoft.db.database.manager.DatabaseManagerImpl;
import com.earasoft.learning1.gui.windows.About;

public class SharedController implements ControllerI {
    private static final Logger logger = LoggerFactory.getLogger(SharedController.class);
    
    ViewBind view;
    PropertiesConfiguration propertiesConfiguration
    DatabaseManagerImpl DbMgrDemo
    
    boolean successfulInit = false
    
    public SharedController(){
        this.view = new ViewBind();
    }
    
    public init(){
        SwingWorker<Boolean, Void> worker1 = new SwingWorker<Boolean, Void>() {
            public Boolean doInBackground() throws SQLException, ConfigurationException {
                propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
                DbMgrDemo = DatabaseManagerBuilder.getDatabaseManager(propertiesConfiguration);
                view.notifyAllViews("Connecting to database")
                DbMgrDemo.openDatabase();
                return true
            }
            
            public void done() {
                try {
                    def doc = get()
                    view.notifyAllViews("Success Connecting to database")
                    successfulInit = true
                    //loadPeople()
                } catch (Exception ex) {
                    Throwable ee = ex.getCause()
                    if (ee instanceof SQLException){
                        view.notifyAllViews("Database Error: " + ee.message)
						logger.error(ee.getMessage(), ee)
                    } else if (ee instanceof ConfigurationException){
                        view.notifyAllViews("Configuration Error: " + ee.message)
						logger.error(ee.getMessage(), ee)
                    }else {
                        view.notifyAllViews("Error: " + ex.message)
						logger.error(ee.getMessage(), ex)
                    }
                }
            }
        };
        worker1.execute();
    }
	

	public ViewBind getViewBind(){
		return view
	}
	
	public DatabaseManager getDatabaseManger(){
		return DbMgrDemo
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
}
