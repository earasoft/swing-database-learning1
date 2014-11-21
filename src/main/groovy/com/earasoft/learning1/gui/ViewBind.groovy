package com.earasoft.learning1.gui

import com.earasoft.db.dao.Person
import com.earasoft.db.dao.impl.PersonDAO
import com.earasoft.learning1.gui.views.GuiMain;

/**
 * Class used to change Gui
 * @author riverema
 *
 */
class ViewBind{
    
    private GuiMainView guiMainView
    
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
        
    }
    
    class GuiMainView{
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
    }
    
    
    
    
    
}