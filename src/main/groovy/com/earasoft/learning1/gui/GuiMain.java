package com.earasoft.learning1.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JTree;
import javax.swing.JPasswordField;

import java.awt.CardLayout;

import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.TitledBorder;

public class GuiMain {

	private JFrame frmRexster;
	private Controller controller;
	int counter = 15;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMain window = new GuiMain();
					window.frmRexster.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRexster = new JFrame();
		frmRexster.setTitle("Client");
		frmRexster.setBounds(100, 100, 796, 645);
		frmRexster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(frmRexster.getContentPane());
		groupLayout.setHorizontalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
		                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE))
		            .addContainerGap())
		);
		groupLayout.setVerticalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel userInfo = new JPanel();
		tabbedPane.addTab("People", null, userInfo, null);
		
		JPanel panel_1 = new JPanel();
		
		JLabel label = new JLabel("First Name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Last Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
		    gl_panel_1.createParallelGroup(Alignment.LEADING)
		        .addGap(0, 450, Short.MAX_VALUE)
		        .addGroup(gl_panel_1.createSequentialGroup()
		            .addContainerGap()
		            .addComponent(label)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addComponent(label_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(138, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
		    gl_panel_1.createParallelGroup(Alignment.LEADING)
		        .addGap(0, 333, Short.MAX_VALUE)
		        .addGroup(gl_panel_1.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
		                .addComponent(label)
		                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addComponent(label_1)
		                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addContainerGap(302, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("addButton");
		
		JButton button_1 = new JButton("removeButton");
		GroupLayout gl_userInfo = new GroupLayout(userInfo);
		gl_userInfo.setHorizontalGroup(
		    gl_userInfo.createParallelGroup(Alignment.TRAILING)
		        .addGroup(Alignment.LEADING, gl_userInfo.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_userInfo.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_userInfo.createSequentialGroup()
		                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGroup(gl_userInfo.createSequentialGroup()
		                    .addGap(10)
		                    .addComponent(button, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
		                .addGroup(gl_userInfo.createSequentialGroup()
		                    .addGap(10)
		                    .addComponent(button_1)))
		            .addContainerGap(143, Short.MAX_VALUE))
		);
		gl_userInfo.setVerticalGroup(
		    gl_userInfo.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_userInfo.createSequentialGroup()
		            .addGap(12)
		            .addGroup(gl_userInfo.createParallelGroup(Alignment.LEADING)
		                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addGroup(gl_userInfo.createSequentialGroup()
		                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(button)))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(button_1)
		            .addGap(46))
		);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		userInfo.setLayout(gl_userInfo);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		frmRexster.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frmRexster.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.exit();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		//last lines
		controller = new Controller(this);
		controller.init();
	}
}
