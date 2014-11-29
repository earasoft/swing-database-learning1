package com.earasoft.learning1.gui;

import com.earasoft.db.database.manager.DatabaseManager

public interface ControllerI {
	public ViewBind getViewBind()
	public DatabaseManager getDatabaseManger()
	public void initialize()
}
