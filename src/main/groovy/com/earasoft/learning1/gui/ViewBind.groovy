package com.earasoft.learning1.gui

import com.earasoft.db.dao.Person
import com.earasoft.db.dao.impl.PersonDAO

/**
 * Class used to change Gui
 * @author riverema
 *
 */
class ViewBind{
    
    private GuiMain guiMain;
    public PersonForm personForm = new PersonForm()
    
    public ViewBind(GuiMain guiMain){
        this.guiMain = guiMain;
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
        
        public show(Person person){
            this.person = person
            guiMain.txtFirstName.setText(person.getFirstName())
            guiMain.txtLastName.setText(person.getLastName())
            personId = person.getPersonId()
            guiMain.btnSavePersonForm.setEnabled(true)
        }
        
        public Map getFormInfo(){
            return ["orgPersonObj": person,
                    "data":["personId": personId,
                            "firstName": guiMain.txtFirstName.getText(),
                            "lastName": guiMain.txtLastName.getText()]]
        }
    }
    
    
}