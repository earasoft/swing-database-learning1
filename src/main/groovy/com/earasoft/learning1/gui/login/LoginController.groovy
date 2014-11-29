package com.earasoft.learning1.gui.login

import com.earasoft.db.database.manager.DatabaseManager;
import com.earasoft.db.database.manager.DatabaseManagerImpl;
import com.earasoft.learning1.gui.ControllerI;
import com.earasoft.learning1.gui.ViewBind;

import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JFrame;

import com.earasoft.learning1.gui.guiMain.GuiMain;
import com.earasoft.learning1.gui.guiMain.GuiMainController;
import com.earasoft.learning1.gui.guiMain.GuiMainView;
import com.earasoft.learning1.gui.login.Login;
import com.earasoft.learning1.gui.login.LoginController
import com.earasoft.learning1.gui.login.LoginView;
import com.earasoft.learning1.gui.SharedController

import org.apache.commons.configuration.PropertiesConfiguration;
import org.shared.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoginController  implements ControllerI {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private final ViewBind viewBind
	private final DatabaseManager databaseManager
	private final SharedController sharedController
	private final LoginController self
	
	private LoginView loginView
	
	public LoginController(SharedController sharedController){
		this.self = this
		this.sharedController = sharedController
		this.viewBind = sharedController.getViewBind()
		this.databaseManager = sharedController.getDatabaseManger()
	}
	
	public void initialize(){		
		EventQueue.invokeLater(new Runnable() {	
			public void run() {
				try {
					Login window = new Login(self, viewBind, sharedController);
					window.getFrmLogin().setVisible(true);
				} catch (Exception e) {
					logger.error(e.getMessage(), e)
				}
			}
		});
	}
	
	boolean developmentMode = true;
	protected void authenticate(){
		LoginView loginView = viewBind.getLoginView()
		Map formInfo = loginView.getFormInfo()
		
		if(formInfo["Username"].equals("admin") && formInfo["Password"].equals("xTKABkTa5GrTjtJxHJZnaFHpuNS4s9vHu4mCnULwkxM=") || developmentMode){
			loginView.setStatus("Success!");
			loginView.dispose();
			logger.info("AUDIT - User: (" + formInfo["Username"] + ") Login Success");
			runGuiMainWindow()
		}else{
			loginView.setStatus("Authenication Failed!");
			loginView.reset();
			logger.warn("AUDIT - User: (" + formInfo["Username"] + ") Login Failure");
		}
	}
	
	public runGuiMainWindow(){
		GuiMainController guiMainController = new GuiMainController(this.sharedController)
		guiMainController.initialize()
	}
	
	public ViewBind getViewBind(){
		return this.viewBind
	}
	
	public DatabaseManager getDatabaseManger(){
		return this.databaseManager
	}
}
