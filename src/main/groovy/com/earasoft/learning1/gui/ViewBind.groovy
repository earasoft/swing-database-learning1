package com.earasoft.learning1.gui
/**
 * Class used to change Gui
 * @author riverema
 *
 */
class ViewBind{
    
    public GuiMain guiMain;
    
    public ViewBind(GuiMain guiMain){
        this.guiMain = guiMain;
    }
    

    
    public void setStatus(String status){
        guiMain.lblStatus.setText(status);
    }
    
}