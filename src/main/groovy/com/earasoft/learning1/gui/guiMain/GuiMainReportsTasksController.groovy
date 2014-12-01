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
