package com.earasoft.learning1.gui.view;

import java.util.List;
import java.util.Map;

import com.earasoft.db.dao.Person;
import com.earasoft.learning1.gui.Models;
import com.earasoft.learning1.gui.windows.GuiMain;
import com.earasoft.learning1.gui.ViewBind;

public class GuiMainView{
	
    private GuiMain guiMain;
    public PersonForm personForm = new PersonForm()
    
    public GuiMainView(GuiMain guiMain){
        this.guiMain = guiMain
    }
    
    public void setStatus(String status){
        guiMain.lblStatus.setText(status);
    }
    
    public showPeopleList(List<Person> people){
        for(Person person : people){
            Models.getListModel().addElement(person);
        }
    }
    
    public repaintPeopleList(){
        guiMain.lstPeople.repaint();
    }
    
    class PersonForm{
        Person person
        Integer personId
        boolean isNewPerson
        
        public show(Person person){
            isNewPerson = false
            this.person = person
            guiMain.txtFirstName.setText(person.getFirstName())
            guiMain.txtLastName.setText(person.getLastName())
            personId = person.getPersonId()
            guiMain.btnSavePersonForm.setEnabled(true)
        }
        
        public reset(){
            person = null
            personId = null
            
            isNewPerson = true
            guiMain.txtFirstName.setText("")
            guiMain.txtLastName.setText("")
        }
        
        public Map getFormInfo(){
            return ["orgPersonObj": person,
                    "isNewPerson":this.isNewPerson,
                    "data":["personId": personId,
                            "firstName": guiMain.txtFirstName.getText(),
                            "lastName": guiMain.txtLastName.getText()]]
        }
        
    }
	
	public void center(){
		ViewBind.centerWindow(this.guiMain.getFrmRexster())
	}
}