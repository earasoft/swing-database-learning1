package com.earasoft.learning1.gui;

import java.awt.EventQueue;

import com.earasoft.learning1.gui.guiMain.GuiMain;
import com.earasoft.learning1.gui.guiMain.GuiMainView;
import com.earasoft.learning1.gui.login.Login;
import com.earasoft.learning1.gui.login.LoginController
import com.earasoft.learning1.gui.login.LoginView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Boot {
	private static final Logger logger = LoggerFactory.getLogger(Boot.class);
    private SharedController sharedController;
    private ViewBind viewBind;
    
    public static void main(String[] args) {
        Boot init = new Boot();
    }
    
    public Boot(){
        initialize();
    }
    
    public void initialize(){
      sharedController = new SharedController();
      viewBind = sharedController.getViewBind();
        
      LoginController loginController = new LoginController(sharedController);
	  loginController.initialize()
        
    }
    
}//test comment
