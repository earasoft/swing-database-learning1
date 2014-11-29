package com.earasoft.learning1.gui.login

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.shared.Util;

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
	
	public Map getFormInfo(){
		return ["Username": this.login.txtUsername.getText(),
			    "Password": Util.checksum(Arrays.toString(this.login.passwordField.getPassword()))]
	}
	
	public void center(){
		ViewBind.centerWindow(this.login.frmLogin)
	}	
	
	public void dispose(){
		this.login.frmLogin.dispose();
	}
	
}
