package com.earasoft.learning1.gui.guiMain;

import java.awt.Color
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField

import com.earasoft.db.dao.Person
import com.earasoft.learning1.gui.Models
import com.earasoft.learning1.gui.ViewBind

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuiMainView{
	private static final Logger logger = LoggerFactory.getLogger(GuiMainView.class);
    private GuiMain guiMain;
    public GuiMainResourceViewPersonForm personForm = new GuiMainResourceViewPersonForm(this, guiMain)
    
    public GuiMainView(GuiMain guiMain){
        this.guiMain = guiMain
    }
    
    public void setStatus(String status){
        guiMain.lblStatus.setText(status);
    }
    
    public showPeopleList(List<Person> people){
        Models.getListModel().clear()
        for(Person person : people){
            Models.getListModel().addElement(person);
        }
    }
    
	public showAddedPerson(Person person){
		Models.getListModel().addElement(person);
	}
	
    public repaintPeopleList(){
        guiMain.lstResources.repaint();
    }
	
	public void center(){
		ViewBind.centerWindow(this.guiMain.getFrmRexster())
	}
}