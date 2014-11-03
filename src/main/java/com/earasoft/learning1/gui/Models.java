package com.earasoft.learning1.gui;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Models {
	private static DefaultListModel<String> listModel;
	
	public static DefaultListModel<String> getListModel(){
		if(listModel==null){
			listModel = new DefaultListModel<String>();
			
			
			listModel.addListDataListener(new ListDataListener(){

				@Override
				public void intervalAdded(ListDataEvent e) {
					// TODO Auto-generated method stub
					System.out.println(e);
				}

				@Override
				public void intervalRemoved(ListDataEvent e) {
					// TODO Auto-generated method stub
					System.out.println(e);
					
				}

				@Override
				public void contentsChanged(ListDataEvent e) {
					// TODO Auto-generated method stub
					System.out.println(e);
					
				}
				
			}
			);
		}
		return listModel;
	}
	
}
