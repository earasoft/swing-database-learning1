package com.earasoft.learning1.gui.view

import com.earasoft.learning1.gui.ViewBind;
import com.earasoft.learning1.gui.windows.Login;

public class LoginView{
	private Login login
	
	public LoginView(Login login){
		this.login = login
	}
	
	public void setStatus(String status){
		this.login.txtStatus.setText(status)
	}
	
	public void reset(){
		this.login.txtUsername.setText("")
		this.login.passwordField.setText("")
		this.login.txtUsername.grabFocus()
		
	}
	
	public void center(){
		ViewBind.centerWindow(this.login.getFrmLogin())
	}	
	
}
