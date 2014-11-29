package com.earasoft.learning1.gui.guiMain;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earasoft.db.dao.Person;
import com.earasoft.learning1.gui.SharedController;
import com.earasoft.learning1.gui.Boot;
import com.earasoft.learning1.gui.Models;
import com.earasoft.learning1.gui.ViewBind;
import com.earasoft.learning1.gui.login.LoginController;
import com.earasoft.learning1.gui.windows.About;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JPasswordField;

public class GuiMain {
    private static final Logger logger = LoggerFactory.getLogger(GuiMain.class);

	private JFrame frmRexster;
	
	private GuiMainController guiMainController;
	 private SharedController sharedController;
	private ViewBind viewBind;
	
	int counter = 15;
	protected JTextField txtFirstName;
	protected JTextField txtLastName;
	protected JLabel lblStatus;
	protected JButton btnSavePersonForm;
	protected JList<Person> lstResources;
	protected JPasswordField pwdResourcepassword;
	protected JTextField txtPhoneNumber;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Boot init = new Boot();
	}

    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public GuiMain(GuiMainController controller, ViewBind viewBind, SharedController sharedController) {
    	this.guiMainController = controller;
    	this.viewBind = viewBind;
    	this.sharedController = sharedController;
    	initialize();    	
    }
    
    GuiMainView guiMainView;
    /**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        this.guiMainView = viewBind.setGuiMainView(this);
        
		setFrmRexster(new JFrame());
		getFrmRexster().addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        About aboutWindowInstance = guiMainController.getAboutWindow();
		        if(aboutWindowInstance != null){
		            aboutWindowInstance.dispose();
		        }
		    }
		});
		
		getFrmRexster().setTitle("Client");
		getFrmRexster().setBounds(100, 100, 837, 692);
		getFrmRexster().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPaneAllMain = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(getFrmRexster().getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(tabbedPaneAllMain, GroupLayout.PREFERRED_SIZE, 821, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tabbedPaneAllMain, GroupLayout.PREFERRED_SIZE, 605, Short.MAX_VALUE)
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
		
		JPanel panelMain = new JPanel();
		tabbedPaneAllMain.addTab("Main", null, panelMain, null);
		
		JTabbedPane tabbedPaneMainView = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panelMain = new GroupLayout(panelMain);
		gl_panelMain.setHorizontalGroup(
			gl_panelMain.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPaneMainView, GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
		);
		gl_panelMain.setVerticalGroup(
			gl_panelMain.createParallelGroup(Alignment.TRAILING)
				.addComponent(tabbedPaneMainView, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
		);
		
		JPanel panelTimesheet = new JPanel();
		tabbedPaneMainView.addTab("Timesheet", null, panelTimesheet, null);
		panelMain.setLayout(gl_panelMain);
		
		JPanel panelResources = new JPanel();
		tabbedPaneAllMain.addTab("Resources (Admin)", null, panelResources, null);
		
		JScrollPane scrollPaneResources = new JScrollPane();
		
		JButton btnAddNewResource = new JButton("Add New Resource");
		btnAddNewResource.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        guiMainView.personForm.reset();
		    }
		});
		
		JButton btnResourceTerminate = new JButton("Terminate Resource");
		
		JTabbedPane tabbedPane_resources = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panelResources = new GroupLayout(panelResources);
		gl_panelResources.setHorizontalGroup(
			gl_panelResources.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResources.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelResources.createParallelGroup(Alignment.LEADING)
						.addComponent(btnResourceTerminate, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddNewResource, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneResources, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tabbedPane_resources, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelResources.setVerticalGroup(
			gl_panelResources.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResources.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelResources.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tabbedPane_resources, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panelResources.createSequentialGroup()
							.addComponent(scrollPaneResources)
							.addGap(18)
							.addComponent(btnAddNewResource)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnResourceTerminate)))
					.addContainerGap())
		);
		
		JPanel panelResourceForm = new JPanel();
		tabbedPane_resources.addTab("Resource", null, panelResourceForm, null);
		panelResourceForm.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblFirstName = new JLabel("First Name");
		
		txtFirstName = new JTextField();
		txtFirstName.setEnabled(false);
		txtFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				guiMainView.personForm.validate();
			}
		});
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		
		txtLastName = new JTextField();
		txtLastName.setEnabled(false);
		txtLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				guiMainView.personForm.validate();
			}
		});
		txtLastName.setColumns(10);
		
		btnSavePersonForm = new JButton("Save");
		btnSavePersonForm.setEnabled(false);
		btnSavePersonForm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        guiMainController.savePersonOnForm(); 
		    }
		});
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		
		JLabel lblPassword = new JLabel("Password");
		
		pwdResourcepassword = new JPasswordField();
		pwdResourcepassword.setEnabled(false);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setEnabled(false);
		txtPhoneNumber.setColumns(10);
		GroupLayout gl_panelResourceForm = new GroupLayout(panelResourceForm);
		gl_panelResourceForm.setHorizontalGroup(
			gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResourceForm.createSequentialGroup()
					.addContainerGap(544, Short.MAX_VALUE)
					.addComponent(btnSavePersonForm)
					.addContainerGap())
				.addGroup(gl_panelResourceForm.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblPhoneNumber)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtPhoneNumber))
						.addGroup(Alignment.LEADING, gl_panelResourceForm.createSequentialGroup()
							.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName)
								.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword))
							.addGap(34)
							.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pwdResourcepassword)
								.addComponent(txtLastName)
								.addComponent(txtFirstName, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
					.addContainerGap(374, Short.MAX_VALUE))
		);
		gl_panelResourceForm.setVerticalGroup(
			gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResourceForm.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhoneNumber)
						.addComponent(txtPhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pwdResourcepassword, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
					.addComponent(btnSavePersonForm)
					.addContainerGap())
		);
		panelResourceForm.setLayout(gl_panelResourceForm);
		
		lstResources = new JList<Person>();
		lstResources.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    int index = lstResources.locationToIndex(e.getPoint());
                    ListModel<Person> dlm = lstResources.getModel();
                    Person person = (Person) dlm.getElementAt(index);
                    lstResources.ensureIndexIsVisible(index);
                    //System.out.println("Double clicked on " + person.toStringFull());
                    guiMainView.personForm.show(person);//  showPersonOnForm(person);
                }
		    }
		});
		
				lstResources.setModel(Models.getListModel());
				scrollPaneResources.setViewportView(lstResources);
				panelResources.setLayout(gl_panelResources);
				
				JPanel panelTasks = new JPanel();
				tabbedPaneAllMain.addTab("Tasks (Admin)", null, panelTasks, null);
				
				JButton btnAddTask = new JButton("Add New Task");
				
				JButton btnDeleteTask = new JButton("Delete Task");
				
				JTabbedPane tabbedPaneTasksMain = new JTabbedPane(JTabbedPane.TOP);
				
				JScrollPane scrollPaneTasks = new JScrollPane();
				GroupLayout gl_panelTasks = new GroupLayout(panelTasks);
				gl_panelTasks.setHorizontalGroup(
					gl_panelTasks.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTasks.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelTasks.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAddTask, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(btnDeleteTask, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(scrollPaneTasks, 0, 0, Short.MAX_VALUE))
							.addGap(20)
							.addComponent(tabbedPaneTasksMain, GroupLayout.PREFERRED_SIZE, 627, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_panelTasks.setVerticalGroup(
					gl_panelTasks.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelTasks.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panelTasks.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tabbedPaneTasksMain, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelTasks.createSequentialGroup()
									.addComponent(scrollPaneTasks, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddTask)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDeleteTask)))
							.addGap(56))
				);
				
				JPanel panelTask = new JPanel();
				tabbedPaneTasksMain.addTab("Task", null, panelTask, null);
				
				JList<Person> lstTasks = new JList<Person>();
				scrollPaneTasks.setViewportView(lstTasks);
				panelTasks.setLayout(gl_panelTasks);
				
				JPanel panelProjects = new JPanel();
				tabbedPaneAllMain.addTab("Projects (Admin)", null, panelProjects, null);
				
				JButton btnAddProject = new JButton("Add New Project");
				
				JButton btnDeleteProject = new JButton("Delete Project");
				
				JScrollPane scrollPaneProjects = new JScrollPane();
				
				JTabbedPane tabbedPaneProjectMain = new JTabbedPane(JTabbedPane.TOP);
				GroupLayout gl_panelProjects = new GroupLayout(panelProjects);
				gl_panelProjects.setHorizontalGroup(
					gl_panelProjects.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProjects.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelProjects.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneProjects, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(btnDeleteProject, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(btnAddProject, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tabbedPaneProjectMain, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE)
							.addGap(8))
				);
				gl_panelProjects.setVerticalGroup(
					gl_panelProjects.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProjects.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelProjects.createParallelGroup(Alignment.BASELINE)
								.addComponent(tabbedPaneProjectMain, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelProjects.createSequentialGroup()
									.addComponent(scrollPaneProjects, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(btnAddProject)
									.addGap(8)
									.addComponent(btnDeleteProject)))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				
				JPanel panelProject = new JPanel();
				tabbedPaneProjectMain.addTab("Project", null, panelProject, null);
				
				JList<Person> lstProjects = new JList<Person>();
				scrollPaneProjects.setViewportView(lstProjects);
				panelProjects.setLayout(gl_panelProjects);
				frmRexster.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panelStatus, lblStatus, panelMain, panelTimesheet, panelResources, scrollPaneResources, lstResources, btnAddNewResource, tabbedPaneMainView, btnResourceTerminate, tabbedPane_resources, panelResourceForm, tabbedPaneAllMain, btnSavePersonForm, lblFirstName, lblLastName, txtFirstName, txtLastName, panelTasks, panelProjects}));
		getFrmRexster().getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		getFrmRexster().setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiMainController.exit();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
		    
		    
		    public void actionPerformed(ActionEvent e) {
		        
		        if(guiMainController.getAboutWindow() == null){
		            EventQueue.invokeLater(new Runnable() {
	                    public void run() {
	                        try {
	                            About window = new About(guiMainController, viewBind);
	                            window.getFrmAbout().setVisible(true);
	                        } catch (Exception ex) {
	                            ex.printStackTrace();
	                        }
	                    }
	                });
		        }
		  
		    }
		});
		mnHelp.add(mntmAbout);
		//last lines
		guiMainView.center();
		guiMainController.loadPeople();
		logger.debug("Main Window Boot");
	}

    public JFrame getFrmRexster() {
        return frmRexster;
    }

    public void setFrmRexster(JFrame frmRexster) {
        this.frmRexster = frmRexster;
        frmRexster.setMinimumSize(new Dimension(837, 692));
        frmRexster.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent arg0) {
        		 Dimension d=getFrmRexster().getSize();
                 Dimension minD=getFrmRexster().getMinimumSize();
                 
                 if(d.width<minD.width)
                     d.width=minD.width;
                 if(d.height<minD.height)
                     d.height=minD.height;
                 getFrmRexster().setSize(d);
        	}
        });
    }
}
