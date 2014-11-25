package com.earasoft.learning1.gui.guiMain

import java.awt.Color

import javax.swing.JTextField

import com.earasoft.db.dao.Person

 class PersonFormView{
		GuiMainView guiMainView
		GuiMain guiMain
		
		Person person
        Integer personId
        boolean isNewPerson
        
		public PersonFormView(GuiMainView guiMainView, GuiMain guiMain){
			this.guiMainView  = guiMainView
			this.guiMain = guiMain
		}
		
        public show(Person person){
			println "PERSON: " + person
			println "PERSON: " + person.toStringFull()
			//logger.debug("Show Person:" + person)
            isNewPerson = false
            this.person = person
			
			resetTextField(guiMain.txtFirstName, person.getFirstName())
			resetTextField(guiMain.txtLastName, person.getLastName())
			
            personId = person.getPersonId()
            guiMain.btnSavePersonForm.setEnabled(true)
			validate()
			this.guiMainView.setStatus("Opened Person: " + person);
        }
        
		public resetTextField(JTextField textField, String input = null){
			textField.setText("")
			textField.setEnabled(true)
			textField.setBackground(Color.WHITE)
			
			if(input != null)
				textField.setText(input)
		}
		
        public reset(){
            person = null
            personId = null
            isNewPerson = true
			resetTextField(guiMain.txtFirstName)
			resetTextField(guiMain.txtLastName)
			guiMain.btnSavePersonForm.setEnabled(false)
			this.guiMainView.setStatus("Form Reset: Ready to add new person")
        }
        
        public Map getFormInfo(){
            return ["orgPersonObj": person,
                    "isNewPerson":this.isNewPerson,
                    "data":["personId": personId,
                            "firstName": guiMain.txtFirstName.getText(),
                            "lastName": guiMain.txtLastName.getText()]]
        }
		
		public boolean validate(){
			boolean valid = true
			List fieldsToFix = []
			
			if(guiMain.txtFirstName.getText().trim().size() == 0){
				guiMain.txtFirstName.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("First Name")
			}else{
				guiMain.txtFirstName.setBackground(Color.WHITE)
			}
			
			if(guiMain.txtLastName.getText().trim().size() == 0){
				guiMain.txtLastName.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("Last Name")
			}else{
				guiMain.txtLastName.setBackground(Color.WHITE)
			}
			
			if(valid){
				guiMain.btnSavePersonForm.setEnabled(true)
				guiMainView.setStatus("Fields are valid")
			}else{
				guiMain.btnSavePersonForm.setEnabled(false)
				guiMainView.setStatus("Please fix fields: " + fieldsToFix)
			}
			return valid
		}
        
    }