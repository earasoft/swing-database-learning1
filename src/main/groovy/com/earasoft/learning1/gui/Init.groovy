package com.earasoft.learning1.gui;

import java.awt.EventQueue;

import com.earasoft.learning1.gui.guiMain.GuiMain;
import com.earasoft.learning1.gui.guiMain.GuiMainView;
import com.earasoft.learning1.gui.login.Login;
import com.earasoft.learning1.gui.login.LoginView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Init {
	private static final Logger logger = LoggerFactory.getLogger(Init.class);
    private Controller controller;
    private ViewBind view;
    
    public static void main(String[] args) {
        Init init = new Init();
    }
    
    public Init(){
        init();
    }
    
    public void init(){
      controller = new Controller();
      view = controller.getView();
        
      EventQueue.invokeLater(new Runnable() {
          public void run() {
              try {
                  Login window = new Login(controller, view);
                  window.getFrmLogin().setVisible(true);
              } catch (Exception e) {
                  logger.error(e.getMessage(), e)
              }
          }
      });
        
    }
    
}
