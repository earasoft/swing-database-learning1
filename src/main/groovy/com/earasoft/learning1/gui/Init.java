package com.earasoft.learning1.gui;

import java.awt.EventQueue;

import com.earasoft.learning1.gui.views.GuiMain;
import com.earasoft.learning1.gui.views.Login;

public class Init {
    
    private Controller controller;
    private ViewBind view;
    
    public static void main(String[] args) {
        
        
//        
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Login window = new Login();
//                    window.frmLogin.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        
        
        
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
        
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuiMain window = new GuiMain(controller, view);
                    window.getFrmRexster().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
