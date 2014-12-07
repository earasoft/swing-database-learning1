package com.earasoft.learning1.gui.guiMain

import com.earasoft.learning1.gui.windows.About;
import java.awt.EventQueue
import java.sql.SQLException

import javax.swing.SwingWorker

import org.apache.commons.configuration.PropertiesConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.earasoft.db.dao.Task
import com.earasoft.db.dao.Tasks
import com.earasoft.db.dao.impl.TaskDAO
import com.earasoft.db.dao.impl.TasksDAO
import com.earasoft.db.database.manager.DatabaseManager
import com.earasoft.db.database.manager.DatabaseManagerImpl
import com.earasoft.learning1.gui.ControllerI
import com.earasoft.learning1.gui.SharedController
import com.earasoft.learning1.gui.ViewBind
import com.earasoft.learning1.gui.login.LoginController
import com.earasoft.learning1.gui.windows.About

class GuiMainReportsTasksController  implements ControllerI {
	private static final Logger logger = LoggerFactory.getLogger(GuiMainReportsTasksController.class);

	private final ViewBind viewBind
	private final DatabaseManager databaseManager
	private final SharedController sharedController
	private final GuiMainReportsTasksController self
	private final GuiMainController guiMainController

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public GuiMainReportsTasksController(SharedController sharedController, GuiMainController guiMainController){
		this.self = this
		this.sharedController = sharedController
		this.viewBind = sharedController.getViewBind()
		this.databaseManager = sharedController.getDatabaseManger()
		this.guiMainController = guiMainController
	}
	
	public void loadTasks(){
		GuiMainView guiMainView = viewBind.getGuiMainView()
		if(initCheck() == false){ return }
		if(checkDatabase() == false){ return }

		SwingWorker<List<Task>, Void> worker = new SwingWorker<List<Task>, Void>() {
					public List<Task> doInBackground() throws SQLException {
						logger.debug("Getting Tasks")
						Tasks taskDAO = new TasksDAO(databaseManager);
						return taskDAO.getTasks(); // heavy task
					}

					public void done() {
						try {
							logger.debug("Getting Tasks Results")
							List<Task> doc = get();
							guiMainView.showTasksList(doc)
							guiMainView.setStatus("Loaded $doc.size Tasks")
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
	
	public void saveTaskForm(){
		GuiMainReportsTasksView guiMainView = viewBind.getGuiMainView().tasksView
		if(guiMainView.validate() == false){ return }

		Map formInfo = guiMainView.getFormInfo()
		logger.debug("formInfo Variable:" + formInfo.toString())

		try{
			Task currentTask
			if(formInfo["isNewTask"] == true){
				currentTask = new TaskDAO()
			}else{
				currentTask = formInfo["orgTaskObj"]
			}

			currentTask.setTaskName(formInfo["data"]["taskName"])
			currentTask.setStartDate(formInfo["data"]["startDate"])
			currentTask.setEndDate(formInfo["data"]["endDate"])
			currentTask.setTotalHours(formInfo["data"]["totalHours"])
			currentTask.setRemainHours(formInfo["data"]["remainingHours"])


			if(formInfo["isNewTask"] == true){
				currentTask.setDatabase(databaseManager)
				currentTask = databaseManager.getTask().addTask(currentTask)
				println "CURRENT:" + currentTask.toStringFull()
				guiMainView.showAddedTask(currentTask)
			}else{
				println "CURRENTSAVE:" + currentTask.toStringFull()
				currentTask.save()
			}

			databaseManager.connection.commit()
			guiMainView.setStatus("Saved Task: " + currentTask)


			//guiMainView.TaskForm.reset()
			guiMainView.show(currentTask, true)
		}catch(SQLException ex){
			databaseManager.connection.rollback()
			guiMainView.setStatus("Database Error: " + ex.getMessage())
			logger.error(ex.getMessage(), ex)
		}catch(Exception ex){
			guiMainView.setStatus("Error: " + ex.getMessage())
			logger.error(ex.getMessage(), ex)
		}
		guiMainView.repaintTasksList()
	}

	@Override
	public ViewBind getViewBind(){
		return this.viewBind
	}

	@Override
	public DatabaseManager getDatabaseManger(){
		return this.databaseManager
	}

	@Override
	public void initialize() {
		
	}

}
