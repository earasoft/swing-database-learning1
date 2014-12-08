package com.earasoft.learning1.gui.guiMain

import java.awt.Color

import javax.swing.JTextField

import com.earasoft.db.dao.Person

 class GuiMainResourceViewPersonForm{
		GuiMainView guiMainView
		GuiMain guiMain
		
		Person person
        Integer personId
        boolean isNewPerson
        
		public GuiMainResourceViewPersonForm(GuiMainView guiMainView, GuiMain guiMain){
			this.guiMainView  = guiMainView
			this.guiMain = guiMain
		}
		
        public show(Person person, boolean saved = false){
			//logger.debug("Show Person:" + person)
            isNewPerson = false
            this.person = person
			
			resetTextField(guiMain.txtFirstName, person.getFirstName())
			resetTextField(guiMain.txtLastName, person.getLastName())
			resetTextField(guiMain.txtPhoneNumber, person.getPhoneNumber())
			resetTextField(guiMain.txtAddress, person.getAddress())
			resetTextField(guiMain.txtCity, person.getCity())
			
			
            personId = person.getPersonId()
            guiMain.btnSavePersonForm.setEnabled(true)
			validate()
			
			if(saved){
				this.guiMainView.setStatus("Saved Person: " + person);
			}else{
				this.guiMainView.setStatus("Opened Person: " + person);
			}
			
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
			resetTextField(guiMain.txtPhoneNumber)
			resetTextField(guiMain.txtAddress)
			resetTextField(guiMain.txtCity)
			guiMain.btnSavePersonForm.setEnabled(false)
			this.guiMainView.setStatus("Form Reset: Ready to add new person")
        }
        
        public Map getFormInfo(){
            return ["orgPersonObj": person,
                    "isNewPerson":this.isNewPerson,
                    "data":["personId": personId,
                            "firstName": guiMain.txtFirstName.getText(),
                            "lastName": guiMain.txtLastName.getText(),
							"phoneNumber" : guiMain.txtPhoneNumber.getText(),
							"address": guiMain.txtAddress.getText(),
							"city": guiMain.txtCity.getText()]]
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
			
			if(guiMain.txtPhoneNumber.getText().trim().size() == 0){
				guiMain.txtPhoneNumber.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("Phone Number")
			}else{
				guiMain.txtPhoneNumber.setBackground(Color.WHITE)
			}
			
			if(guiMain.txtAddress.getText().trim().size() == 0){
				guiMain.txtAddress.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("Address")
			}else{
				guiMain.txtAddress.setBackground(Color.WHITE)
			}
			
			if(guiMain.txtCity.getText().trim().size() == 0){
				guiMain.txtCity.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("City")
			}else{
				guiMain.txtCity.setBackground(Color.WHITE)
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