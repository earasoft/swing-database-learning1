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
    
    public String getProjectName(){
        return guiMain.getTxtProjectname().getText().trim();
    }
    
    public String getIpAddress(){
        return guiMain.getTxtRexsterip().getText().trim();
    }
    
    public void setIpAddress(String hostname){
        guiMain.getTxtRexsterip().setText(hostname);
    }
    
    public void setTextOutput(String output){
        guiMain.getDtrpnOutput().setText(output);
    }
    
    public void resetTextOutput(){
        guiMain.getDtrpnOutput().setText("");
    }
    
}