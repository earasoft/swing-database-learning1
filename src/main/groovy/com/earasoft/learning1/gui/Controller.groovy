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
import com.earasoft.db.database.manager.DatabaseManagerBuilder;
import com.earasoft.db.database.manager.DatabaseManagerImpl;
import com.earasoft.learning1.gui.windows.About;

public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    
    ViewBind view;
    PropertiesConfiguration propertiesConfiguration
    DatabaseManagerImpl DbMgrDemo
    
    boolean successfulInit = false
    
    public Controller(){
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
                    def doc = get();
                    System.out.println(doc);
                    
                    view.notifyAllViews("Success Connecting to database")
                    successfulInit = true
                    //loadPeople()
                    
                } catch (Exception ex) {
                    Throwable ee = ex.getCause()
                    if (ee instanceof SQLException){
                        view.notifyAllViews("Database Error: " + ee.message)
                    } else if (ee instanceof ConfigurationException){
                        view.notifyAllViews("Configuration Error: " + ee.message)
                    }else {
                        view.notifyAllViews("Error: " + ex.message)
                    }
                }
            }
        };
        worker1.execute();
    }
	
    public boolean initCheck(){
        if(successfulInit == false){
            logger.error("Controller Initization was not successful")
            return false
        }else
            return true
    }
    
    public boolean checkDatabase(){
        if(DbMgrDemo == null){
            logger.error("Could not load people due to <DbMgrDemo == null>")
            return false
        }else
            return true
    }
    public void loadPeople(){
        if(initCheck() == false) return
        if(checkDatabase() == false) return
        
        SwingWorker<List<Person>, Void> worker = new SwingWorker<List<Person>, Void>() {
            public List<Person> doInBackground() throws SQLException {
                People personDAO = new PeopleDAO(DbMgrDemo);
                return personDAO.getPeople(); // heavy task
            }
            
            public void done() {
                try {
                    List<Person> doc = get();
                    view.guiMainView.showPeopleList(doc)
                    view.guiMainView.setStatus("Loaded $doc.size People")
                } catch (Exception ex) {
                     Throwable ee = ex.getCause()
                    if (ee instanceof SQLException){
                        view.guiMainView.setStatus("Error Connecting to database: " + ee.message)
                    }else {
                        view.guiMainView.setStatus("Error: " + ex.message)
                    }
                }
            }
        };
        worker.execute();
    }
    
	public void exit(){
		System.exit(0);
	}
    
   public void savePersonOnForm(){
       if(initCheck() == false) return
       if(checkDatabase() == false) return
       if(view.guiMainView.personForm.validate() == false) return
	   
       Map formInfo = view.guiMainView.personForm.getFormInfo()
       println (formInfo.toString())
       
       try{
		   
           Person currentPerson = formInfo["orgPersonObj"]
           currentPerson.setFirstName(formInfo["data"]["firstName"])
           currentPerson.setLastName(formInfo["data"]["lastName"])
           currentPerson.save()
           DbMgrDemo.connection.commit()
           view.guiMainView.setStatus("Saved Person: " + currentPerson)
       }catch(SQLException ex){
           DbMgrDemo.connection.rollback()
           view.guiMainView.setStatus("Database Error: " + ex.getMessage())
       }catch(Exception ex){
           view.guiMainView.setStatus("Error: " + ex.getMessage())
       }
       view.guiMainView.repaintPeopleList()
   }
   
   private About aboutWindow
   
   public void setAboutWindow(About window){
       this.aboutWindow = window
   }
   
   public About getAboutWindow(){
       return this.aboutWindow
   }
}
