package com.earasoft.learning1.gui;

public class Controller {
    private ViewBind view;
    
    public Controller(GuiMain view){
        this.view = new ViewBind(view);
    }
    
    public init(){
		for (int i = 0; i < 15; i++)
			Models.getListModel().addElement("Element " + i);
    }
	
	public void exit(){
		System.exit(0);
	}
    
}
