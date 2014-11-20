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
import javax.swing.ListModel;
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
import javax.swing.border.BevelBorder;

import com.earasoft.db.dao.Person;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class GuiMain {

	private JFrame frmRexster;
	private Controller controller;
	private ViewBind view;
	
	int counter = 15;
	protected JTextField txtFirstName;
	protected JTextField txtLastName;
	protected JLabel lblStatus;
	private JButton btnSavePersonForm;
	private JList<Person> lstPeople;
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
	    controller = new Controller(this);
        view = controller.getView();
        
		frmRexster = new JFrame();
		frmRexster.setTitle("Client");
		frmRexster.setBounds(100, 100, 837, 657);
		frmRexster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(frmRexster.getContentPane());
		groupLayout.setHorizontalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
		            .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
		            .addContainerGap())
		        .addComponent(panelStatus, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(panelStatus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
		);
		
		lblStatus = new JLabel("Status");
		GroupLayout gl_panelStatus = new GroupLayout(panelStatus);
		gl_panelStatus.setHorizontalGroup(
		    gl_panelStatus.createParallelGroup(Alignment.LEADING)
		        .addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
		);
		gl_panelStatus.setVerticalGroup(
		    gl_panelStatus.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panelStatus.createSequentialGroup()
		            .addComponent(lblStatus)
		            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelStatus.setLayout(gl_panelStatus);
		
		JPanel panelPeople = new JPanel();
		tabbedPane.addTab("People", null, panelPeople, null);
		
		JPanel panelPersonForm = new JPanel();
		panelPersonForm.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblFirstName = new JLabel("First Name");
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		
		btnSavePersonForm = new JButton("Save");
		btnSavePersonForm.setEnabled(false);
		btnSavePersonForm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        controller.savePersonOnForm();
		        
		    }
		});
		GroupLayout gl_panelPersonForm = new GroupLayout(panelPersonForm);
		gl_panelPersonForm.setHorizontalGroup(
		    gl_panelPersonForm.createParallelGroup(Alignment.TRAILING)
		        .addGroup(gl_panelPersonForm.createSequentialGroup()
		            .addContainerGap()
		            .addComponent(lblFirstName)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
		            .addComponent(btnSavePersonForm)
		            .addContainerGap())
		);
		gl_panelPersonForm.setVerticalGroup(
		    gl_panelPersonForm.createParallelGroup(Alignment.TRAILING)
		        .addGroup(gl_panelPersonForm.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_panelPersonForm.createParallelGroup(Alignment.BASELINE)
		                .addComponent(lblFirstName)
		                .addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addComponent(lblLastName)
		                .addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addPreferredGap(ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
		            .addComponent(btnSavePersonForm)
		            .addContainerGap())
		);
		panelPersonForm.setLayout(gl_panelPersonForm);
		
		JScrollPane scrollPanePeople = new JScrollPane();
		
		JButton btnPersonAdd = new JButton("Add");
		btnPersonAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		    }
		});
		
		JButton btnPersonRemove = new JButton("Remove");
		GroupLayout gl_panelPeople = new GroupLayout(panelPeople);
		gl_panelPeople.setHorizontalGroup(
		    gl_panelPeople.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panelPeople.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_panelPeople.createParallelGroup(Alignment.LEADING)
		                .addGroup(gl_panelPeople.createSequentialGroup()
		                    .addComponent(scrollPanePeople, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addComponent(panelPersonForm, GroupLayout.PREFERRED_SIZE, 608, GroupLayout.PREFERRED_SIZE))
		                .addGroup(gl_panelPeople.createSequentialGroup()
		                    .addGap(10)
		                    .addGroup(gl_panelPeople.createParallelGroup(Alignment.TRAILING, false)
		                        .addComponent(btnPersonRemove, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(btnPersonAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))))
		            .addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panelPeople.setVerticalGroup(
		    gl_panelPeople.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panelPeople.createSequentialGroup()
		            .addGap(12)
		            .addGroup(gl_panelPeople.createParallelGroup(Alignment.TRAILING, false)
		                .addComponent(panelPersonForm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addComponent(scrollPanePeople, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(btnPersonAdd)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(btnPersonRemove)
		            .addGap(58))
		);
		
		lstPeople = new JList<Person>();
		lstPeople.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = lstPeople.locationToIndex(e.getPoint());
                    ListModel<Person> dlm = lstPeople.getModel();
                    Person person = (Person) dlm.getElementAt(index);
                    lstPeople.ensureIndexIsVisible(index);
                    
                    //System.out.println("Double clicked on " + person.toStringFull());
                    
                    view.personForm.show(person);//  showPersonOnForm(person);
                    view.setStatus("Opened Person: " + person);
                }
		    }
		});

		lstPeople.setModel(Models.getListModel());
		scrollPanePeople.setViewportView(lstPeople);
		panelPeople.setLayout(gl_panelPeople);
		
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
		
		controller.init();
	}
}
