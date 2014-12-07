package com.earasoft.learning1.gui.guiMain

import java.awt.Color
import java.util.Map;

import javax.swing.JTextField

import com.earasoft.db.dao.Person
import com.earasoft.db.dao.Task

 class GuiMainReportsTasksView{
		GuiMainView guiMainView
		GuiMain guiMain
		
		Task task
		String taskName		
		Boolean isNewTask
		
		
		
		public GuiMainReportsTasksView(GuiMainView guiMainView, GuiMain guiMain){
			this.guiMainView  = guiMainView
			this.guiMain = guiMain
		}
		
		public show(Task task, boolean saved = false){
			//logger.debug("Show Task:" + task)
			isNewTask = false
			this.task = task
			
			resetTextField(guiMain.txtTaskName, task.getTaskName())
			resetTextField(guiMain.txtTaskStart, task.getTaskStart())
			resetTextField(guiMain.txtTaskEnd, task.getTaskEnd())
			
			taskName = task.getTaskName()
			guiMain.btnSavePersonForm.setEnabled(true)
			validate()
			
			if(saved){
				this.guiMainView.setStatus("Saved Task: " + task);
			}else{
				this.guiMainView.setStatus("Opened task: " + task);
			}			
			
		}
		
		public resetTextField(JTextField textField, String input = null){
			textField.setText("")
			textField.setEnabled(true)
			textField.setBackground(Color.WHITE)
			
			if(input != null)
				textField.setText(input)
		}
		
		public Map getFormInfo(){
			return ["orgTaskObj": task,
					"isNewTask":this.isNewTask,
					"data":["personId": personId,
							"TaskName": guiMain.txtTaskName.getText(),
							"TaskStart": guiMain.txtTaskStart.getText(),
							"TaskEnd": guiMain.txtTaskEnd.getText()]]
		}
		
		public boolean validate(){
			boolean valid = true
			List fieldsToFix = []
			
			if(guiMain.txtTaskName.getText().trim().size() == 0){
				guiMain.txtTaskName.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("Task Name")
			}else{
				guiMain.txtTaskName.setBackground(Color.WHITE)
			}
			
			if(guiMain.txtTaskStart.getText().trim().size() == 0){
				guiMain.txtTaskStart.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("Task Start Time")
			}else{
				guiMain.txtTaskStart.setBackground(Color.WHITE)
			}
			
			if(guiMain.txtTaskEnd.getText().trim().size() == 0){
				guiMain.txtTaskEnd.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("Task End Time")
			}else{
				guiMain.txtTaskEnd.setBackground(Color.WHITE)
			}
			
			if(guiMain.txtTaskTotalHours.getText().trim().size() == 0){
				guiMain.txtTaskTotalHours.setBackground(Color.RED)
				valid = false
				fieldsToFix.add("Task Hours")
			}else{
				guiMain.txtTaskEnd.setBackground(Color.WHITE)
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