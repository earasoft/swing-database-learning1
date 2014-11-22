package com.earasoft.learning1.gui;

import java.awt.EventQueue;

import com.earasoft.learning1.gui.windows.GuiMain;
import com.earasoft.learning1.gui.windows.Login;

public class Init {
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
                  e.printStackTrace();
              }
          }
      });
        
    }
    
}
