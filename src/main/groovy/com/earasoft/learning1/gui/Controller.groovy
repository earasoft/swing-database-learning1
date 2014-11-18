package com.earasoft.learning1.gui;

import java.io.IOException;
import java.sql.SQLException

import javax.swing.SwingWorker;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.earasoft.db.dao.People;
import com.earasoft.db.dao.Person;
import com.earasoft.db.dao.impl.PeopleDAO;
import com.earasoft.db.dao.impl.PersonDAO;
import com.earasoft.db.database.manager.DatabaseManagerBuilder;
import com.earasoft.db.database.manager.DatabaseManagerImpl;

public class Controller {
    private ViewBind view;
    PropertiesConfiguration propertiesConfiguration
    DatabaseManagerImpl DbMgrDemo
    
    public Controller(GuiMain view){
        this.view = new ViewBind(view);
        
    }
    
    public init(){
		//DbMgrDemo.clearDatabase();
        println "ke"
        
        propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
        DbMgrDemo = DatabaseManagerBuilder.getDatabaseManager(propertiesConfiguration);
        
        SwingWorker<Boolean, Void> worker1 = new SwingWorker<Boolean, Void>() {
            public Boolean doInBackground() throws SQLException {
                view.setStatus("Connecting to database")
                DbMgrDemo.openDatabase();
                return true
            }
            
            public void done() {
                try {
                    def doc = get();
                    System.out.println(doc);
                    //display(doc);
                    view.setStatus("Success Connecting to database")

                } catch (Exception ex) {
                    ex.printStackTrace();
                    view.setStatus("Error Connecting to database")
                }
            }
        };
        worker1.execute();

        println "he"
		
        init_people()
    }
	
    public void init_people(){
        if(DbMgrDemo.currentConnection == null)
            return
        People personDAO = new PeopleDAO(DbMgrDemo);
        
        //System.out.println(personDAO.checkIfPersonExist("1549490016"));
        
        //personDAO.addPerson(new PersonDAO("first","R4","445-555-4446"));
//      for(int i = 1; i<=1000; i++){
//          personDAO.addPerson(new PersonDAO("f54","R4","555-555-4444"));
//          System.out.println("person: " + i);
//      }

        SwingWorker<List<Person>, Void> worker = new SwingWorker<List<Person>, Void>() {
            public List<Person> doInBackground() throws IOException {
                return personDAO.getPeople(); // heavy task
            }
            
            public void done() {
                try {
                    List<Person> doc = get();
                    System.out.println(doc.size());
                    //display(doc);
                    
                    for(Person person : doc){
                        Models.getListModel().addElement(person);
                    }
                    
                    
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        worker.execute();
    }
	public void exit(){
		System.exit(0);
	}
    
}
