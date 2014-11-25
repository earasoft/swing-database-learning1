package com.earasoft.learning1.gui.login

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.earasoft.learning1.gui.ViewBind

public class LoginView{
	private static final Logger logger = LoggerFactory.getLogger(LoginView.class);
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
