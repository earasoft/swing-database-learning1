package com.earasoft.learning1.gui

import com.earasoft.db.dao.Person
import com.earasoft.db.dao.impl.PersonDAO;
import com.earasoft.learning1.gui.view.GuiMainView;
import com.earasoft.learning1.gui.view.LoginView;
import com.earasoft.learning1.gui.windows.GuiMain;
import com.earasoft.learning1.gui.windows.Login
import java.awt.Dimension
import java.awt.Window
import java.awt.Toolkit;

/**
 * Class used to change Gui
 * @author riverema
 *
 */
class ViewBind{
    
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
	
	
    public setGuiMainView(GuiMain guiMain){
        this.guiMainView = new GuiMainView(guiMain);
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