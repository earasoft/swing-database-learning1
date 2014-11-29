package com.earasoft.learning1.gui.guiMain

import com.earasoft.learning1.gui.windows.About;
import java.awt.EventQueue
import java.sql.SQLException

import javax.swing.SwingWorker

import org.apache.commons.configuration.PropertiesConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.earasoft.db.dao.People
import com.earasoft.db.dao.Person
import com.earasoft.db.dao.impl.PeopleDAO
import com.earasoft.db.dao.impl.PersonDAO
import com.earasoft.db.database.manager.DatabaseManager
import com.earasoft.db.database.manager.DatabaseManagerImpl
import com.earasoft.learning1.gui.ControllerI
import com.earasoft.learning1.gui.SharedController
import com.earasoft.learning1.gui.ViewBind
import com.earasoft.learning1.gui.login.LoginController
import com.earasoft.learning1.gui.windows.About

class GuiMainController  implements ControllerI {
	private static final Logger logger = LoggerFactory.getLogger(GuiMainController.class);

	private final ViewBind viewBind
	private final DatabaseManager databaseManager
	private final SharedController sharedController
	private final GuiMainController self


	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public GuiMainController(SharedController sharedController){
		this.self = this
		this.sharedController = sharedController
		this.viewBind = sharedController.getViewBind()
		this.databaseManager = sharedController.getDatabaseManger()
	}

	public void loadPeople(){
		GuiMainView guiMainView = viewBind.getGuiMainView()
		if(initCheck() == false){ return }
		if(checkDatabase() == false){ return }

		SwingWorker<List<Person>, Void> worker = new SwingWorker<List<Person>, Void>() {
					public List<Person> doInBackground() throws SQLException {
						People personDAO = new PeopleDAO(databaseManager);
						return personDAO.getPeople(); // heavy task
					}

					public void done() {
						try {
							List<Person> doc = get();
							guiMainView.showPeopleList(doc)
							guiMainView.setStatus("Loaded $doc.size People")
						} catch (Exception ex) {
							Throwable ee = ex.getCause()
							if (ee instanceof SQLException){
								guiMainView.setStatus("Error Connecting to database: " + ee.message)
								logger.error("Error Connecting to database: " + ee.message, ee)
							}else {
								guiMainView.setStatus("Error: " + ex.message)
								logger.error("Error Connecting to database: " + ex.message, ex)
							}
						}
					}
				};
		worker.execute();
	}

	public void exit(){
		logger.debug("Exiting Application")
		System.exit(0);
	}

	public void savePersonOnForm(){
		GuiMainView guiMainView = viewBind.getGuiMainView()
		if(initCheck() == false){ return }
		if(checkDatabase() == false){ return }
		if(guiMainView.personForm.validate() == false){ return }

		Map formInfo = guiMainView.personForm.getFormInfo()
		logger.debug("formInfo Variable:" + formInfo.toString())

		try{
			Person currentPerson
			if(formInfo["isNewPerson"] == true){
				currentPerson = new PersonDAO()
			}else{
				currentPerson = formInfo["orgPersonObj"]
			}

			currentPerson.setFirstName(formInfo["data"]["firstName"])
			currentPerson.setLastName(formInfo["data"]["lastName"])


			if(formInfo["isNewPerson"] == true){
				currentPerson.setDatabase(databaseManager)
				currentPerson = databaseManager.people().addPerson(currentPerson)
				println "CURRENT:" + currentPerson.toStringFull()
				guiMainView.showAddedPerson(currentPerson)
			}else{
				println "CURRENTSAVE:" + currentPerson.toStringFull()
				currentPerson.save()
			}

			databaseManager.connection.commit()
			guiMainView.setStatus("Saved Person: " + currentPerson)


			//guiMainView.personForm.reset()
			guiMainView.personForm.show(currentPerson, true)
		}catch(SQLException ex){
			databaseManager.connection.rollback()
			guiMainView.setStatus("Database Error: " + ex.getMessage())
			logger.error(ex.getMessage(), ex)
		}catch(Exception ex){
			guiMainView.setStatus("Error: " + ex.getMessage())
			logger.error(ex.getMessage(), ex)
		}
		guiMainView.repaintPeopleList()
	}

	private About aboutWindow

	public void setAboutWindow(About window){
		this.aboutWindow = window
	}

	public About getAboutWindow(){
		return this.aboutWindow
	}

	boolean successfulInit = true

	public boolean initCheck(){
		if(successfulInit == false){
			logger.error("SharedController Initization was not successful")
			return false
		}else
			return true
	}

	public boolean checkDatabase(){
		if(databaseManager == null){
			logger.error("Could not load people due to <databaseManager == null>")
			return false
		}else
			return true
	}

	public ViewBind getViewBind(){
		return this.viewBind
	}

	public DatabaseManager getDatabaseManger(){
		return this.databaseManager
	}

	@Override
	public void initialize() {
		EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GuiMain window = new GuiMain(self, viewBind, sharedController);
							window.getFrmRexster().setVisible(true);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				});
	}

}
