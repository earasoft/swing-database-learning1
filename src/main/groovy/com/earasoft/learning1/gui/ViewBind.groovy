package com.earasoft.learning1.gui

import com.earasoft.db.dao.Person
import com.earasoft.db.dao.impl.PersonDAO;
import com.earasoft.learning1.gui.guiMain.GuiMain;
import com.earasoft.learning1.gui.guiMain.GuiMainView;
import com.earasoft.learning1.gui.login.Login;
import com.earasoft.learning1.gui.login.LoginView;

import java.awt.Dimension
import java.awt.Window
import java.awt.Toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to change Gui
 * @author riverema
 *
 */
class ViewBind{
	private static final Logger logger = LoggerFactory.getLogger(ViewBind.class);
    private GuiMainView guiMainView
	private LoginView loginView
    
	
	public setLoginView(Login login){
		loginView = new LoginView(login)
	}
	
	public LoginView getLoginView(){
		if(loginView==null)
			throw new Exception("Error Getting GuiMainView")
		return loginView;
	}
	
	
    public GuiMainView setGuiMainView(GuiMain guiMain){
        this.guiMainView = new GuiMainView(guiMain);
		return this.guiMainView
    }
	
    public GuiMainView getGuiMainView() {
        if(guiMainView==null)
            throw new Exception("Error Getting GuiMainView")
        return guiMainView;
    }

    public void notifyAllViews(String message){
        if(guiMainView!=null)
            guiMainView.setStatus(message)
        
		if(loginView!=null)
			loginView.setStatus(message)
    }
	
	public static void centerWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
    
}