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
import com.earasoft.db.dao.Project;
import com.earasoft.db.database.manager.DatabaseManagerBuilder;
import com.earasoft.db.database.manager.DatabaseManagerImpl;
import com.earasoft.learning1.gui.SharedController;
import com.earasoft.learning1.gui.Boot;
import com.earasoft.learning1.gui.Models;
import com.earasoft.learning1.gui.ViewBind;
import com.earasoft.learning1.gui.login.LoginController;
import com.earasoft.learning1.gui.windows.About;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.AbstractListModel;
import javax.swing.JEditorPane;
import javax.swing.DefaultComboBoxModel;

import com.earasoft.db.security.RolesE;
import com.earasoft.demo.DbMgrDemo;
import com.mysql.jdbc.Connection;

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
    private JTextField txtProjectName;
    private JTextField txtStartDate;
    private JTextField txtEndDate;
    private JTextField txtTaskName;
    private JTextField txtTaskStart;
    private JTextField txtTaskEnd;
    private JTextField txtCalculated_1;
    private JTextField txtTaskTotalHours;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTextField textField_15;
    private JButton btnAddNewTask;
    private JButton btnDeleteNewTask;
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
		
		JButton btnRefreshResources = new JButton("Refresh Resources");
		btnRefreshResources.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        guiMainController.loadPeople();
		    }
		});
		GroupLayout gl_panelResources = new GroupLayout(panelResources);
		gl_panelResources.setHorizontalGroup(
			gl_panelResources.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResources.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelResources.createParallelGroup(Alignment.LEADING)
						.addComponent(btnResourceTerminate, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddNewResource, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefreshResources, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPaneResources, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tabbedPane_resources, GroupLayout.PREFERRED_SIZE, 631, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panelResources.setVerticalGroup(
			gl_panelResources.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResources.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelResources.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane_resources, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelResources.createSequentialGroup()
							.addComponent(scrollPaneResources, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRefreshResources)
							.addPreferredGap(ComponentPlacement.UNRELATED)
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
		        guiMainController.loadPeople();
		    }
		});
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		
		JLabel lblPassword = new JLabel("Password");
		
		pwdResourcepassword = new JPasswordField();
		pwdResourcepassword.setEnabled(false);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setEnabled(false);
		txtPhoneNumber.setColumns(10);
		
		JLabel lblAddress = new JLabel("Street Address");
		
		JLabel lblPosition = new JLabel("Position");
		
		JLabel lblTeam = new JLabel("Team");
		
		JLabel lblCity = new JLabel("City");
		
		JLabel lblState = new JLabel("State");
		
		JLabel lblZip = new JLabel("Zip");
		
		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setEnabled(false);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setEnabled(false);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setEnabled(false);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setEnabled(false);
		textField_15.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		
		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setModel(new DefaultComboBoxModel(RolesE.values()));
		GroupLayout gl_panelResourceForm = new GroupLayout(panelResourceForm);
		gl_panelResourceForm.setHorizontalGroup(
			gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResourceForm.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(btnSavePersonForm)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pwdResourcepassword, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
							.addGap(148))
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(511, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(511, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblZip, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(511, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblPosition, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(196, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblTeam, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(196, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelResourceForm.createSequentialGroup()
							.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelResourceForm.createSequentialGroup()
									.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPhoneNumber)
										.addComponent(lblFirstName)
										.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_panelResourceForm.createSequentialGroup()
									.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addGap(3)))
							.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panelResourceForm.createSequentialGroup()
									.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtFirstName, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
										.addComponent(txtPhoneNumber)
										.addComponent(txtLastName))
									.addGap(224))
								.addGroup(gl_panelResourceForm.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE))
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblRole, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(comboBox_12, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCity)
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelResourceForm.createSequentialGroup()
							.addComponent(lblState)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblZip)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPosition)
								.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTeam)
								.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelResourceForm.createSequentialGroup()
							.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRole)
						.addComponent(comboBox_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelResourceForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pwdResourcepassword, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
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
				
				JPanel panelProjects = new JPanel();
				tabbedPaneAllMain.addTab("Projects & Tasks (Admin)", null, panelProjects, null);
				
				JButton btnAddProject = new JButton("Add New Project");
				btnAddProject.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	PropertiesConfiguration propertiesConfiguration;
						try {
							propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
							DatabaseManagerImpl Connection = DatabaseManagerBuilder.getDatabaseManager(propertiesConfiguration);
					    	//DbMgrDemo.clearDatabase();
					    	Connection.openDatabase();					    	
					    	String sql = "INSERT INTO project VALUES('"+ txtProjectName.getText() +"','"+ txtStartDate.getText() +"','"+ txtEndDate.getText() +"');";
					    	Statement statement = Connection.getConnection().prepareStatement(sql);
					    	statement.executeUpdate(sql);
					    	Connection.getConnection().commit();   	
					    	
					    	Statement st = Connection.getConnection().createStatement();
				            String query="SELECT projectID FROM project";
				            ResultSet res = st.executeQuery(query);				            
				            Vector<String> temp = new Vector<String>();				            
				            while (res.next()) {
				                temp.add(res.getString("projectID"));
				            }
				            
				            JList lstProjects = new JList(temp);
				    
					    	
					    	
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				    	
				        
				    }
				});
				
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
				
				JLabel lblProjectName = new JLabel("Project Name");
				
				JLabel lblStartDate = new JLabel("Start Date");
				
				JLabel lblEndDate = new JLabel("End Date");
				
				JLabel lblManager = new JLabel("Manager");
				
				txtProjectName = new JTextField();
				txtProjectName.setColumns(10);
				
				txtStartDate = new JTextField();
				txtStartDate.setColumns(10);
				
				txtEndDate = new JTextField();
				txtEndDate.setColumns(10);
				
				JComboBox comboBox = new JComboBox();
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				GroupLayout gl_panelProject = new GroupLayout(panelProject);
				gl_panelProject.setHorizontalGroup(
					gl_panelProject.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProject.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelProject.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelProject.createSequentialGroup()
									.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 606, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_panelProject.createSequentialGroup()
									.addGroup(gl_panelProject.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelProject.createSequentialGroup()
											.addComponent(lblProjectName)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtProjectName, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
										.addGroup(gl_panelProject.createSequentialGroup()
											.addComponent(lblStartDate)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtStartDate, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))
									.addGap(43)
									.addGroup(gl_panelProject.createParallelGroup(Alignment.LEADING)
										.addComponent(lblManager, Alignment.TRAILING)
										.addComponent(lblEndDate, Alignment.TRAILING))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelProject.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, 0, 145, Short.MAX_VALUE)
										.addComponent(txtEndDate, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
									.addGap(161))))
				);
				gl_panelProject.setVerticalGroup(
					gl_panelProject.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProject.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelProject.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProjectName)
								.addComponent(txtProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblManager)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelProject.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStartDate)
								.addComponent(txtStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEndDate))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				
				JPanel panel = new JPanel();
				tabbedPane.addTab("Tasks", null, panel, null);
				
				JScrollPane scrollPane = new JScrollPane();
				
				JLabel lblTaskName = new JLabel("Task Name");
				
				txtTaskName = new JTextField();
				txtTaskName.setColumns(10);
				
				JLabel label = new JLabel("Start Date");
				
				JLabel label_1 = new JLabel("End Date");
				
				txtTaskStart = new JTextField();
				txtTaskStart.setColumns(10);
				
				txtTaskEnd = new JTextField();
				txtTaskEnd.setColumns(10);
				
				JLabel lblRemainingHours = new JLabel("Remaining Hours");
				
				txtCalculated_1 = new JTextField();
				txtCalculated_1.setText("Calculated");
				txtCalculated_1.setColumns(10);
				
				btnAddNewTask = new JButton("Add New Task");
				btnAddNewTask.addActionListener(new ActionListener() {

				    public void actionPerformed(ActionEvent e) {
				    	PropertiesConfiguration propertiesConfiguration;
						try {
							propertiesConfiguration = new PropertiesConfiguration("config/settings.properties");
							DatabaseManagerImpl Connection = DatabaseManagerBuilder.getDatabaseManager(propertiesConfiguration);
					    	//DbMgrDemo.clearDatabase();
					    	Connection.openDatabase();					    	
					    	String sql = "INSERT INTO task VALUES(" + txtTaskName.getText() + "," + txtProjectName.getText()+ "," + txtStartDate.getText() + "," + txtEndDate.getText() + ");";
					    	Statement statement = Connection.getConnection().prepareStatement(sql);
					    	statement.executeUpdate(sql);
					    	Connection.getConnection().commit();
					    	
					    	
					    	
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				    	
				        
				    }
				});
				
				
				
				btnDeleteNewTask = new JButton("Delete Task");
				btnDeleteNewTask.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				
				JLabel lblHours = new JLabel("Total Hours");
				
				txtTaskTotalHours = new JTextField();
				txtTaskTotalHours.setColumns(10);
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
				    gl_panel.createParallelGroup(Alignment.LEADING)
				        .addGroup(gl_panel.createSequentialGroup()
				            .addContainerGap()
				            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				                .addGroup(gl_panel.createSequentialGroup()
				                    .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
				                        .addComponent(btnAddNewTask, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				                        .addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
				                    .addPreferredGap(ComponentPlacement.RELATED)
				                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				                        .addGroup(gl_panel.createSequentialGroup()
				                            .addComponent(lblHours)
				                            .addPreferredGap(ComponentPlacement.RELATED)
				                            .addComponent(txtTaskTotalHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				                        .addGroup(gl_panel.createSequentialGroup()
				                            .addComponent(lblRemainingHours)
				                            .addPreferredGap(ComponentPlacement.RELATED)
				                            .addComponent(txtCalculated_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				                        .addGroup(gl_panel.createSequentialGroup()
				                            .addComponent(lblTaskName)
				                            .addPreferredGap(ComponentPlacement.RELATED)
				                            .addComponent(txtTaskName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				                        .addGroup(gl_panel.createSequentialGroup()
				                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				                                .addComponent(label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
				                                .addComponent(label_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				                            .addPreferredGap(ComponentPlacement.UNRELATED)
				                            .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				                                .addComponent(txtTaskEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				                                .addComponent(txtTaskStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
				                .addComponent(btnDeleteNewTask, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
				            .addContainerGap(210, Short.MAX_VALUE))
				);
				gl_panel.setVerticalGroup(
				    gl_panel.createParallelGroup(Alignment.LEADING)
				        .addGroup(gl_panel.createSequentialGroup()
				            .addContainerGap()
				            .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
				                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
				                .addGroup(gl_panel.createSequentialGroup()
				                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				                        .addGroup(gl_panel.createSequentialGroup()
				                            .addComponent(lblTaskName)
				                            .addGap(12)
				                            .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
				                                .addComponent(label)
				                                .addComponent(txtTaskStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				                            .addGap(9)
				                            .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
				                                .addComponent(label_1)
				                                .addComponent(txtTaskEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				                        .addComponent(txtTaskName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				                    .addGap(12)
				                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
				                        .addComponent(lblHours)
				                        .addComponent(txtTaskTotalHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				                    .addGap(8)
				                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
				                        .addComponent(lblRemainingHours)
				                        .addComponent(txtCalculated_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				            .addGap(18)
				            .addComponent(btnAddNewTask)
				            .addPreferredGap(ComponentPlacement.UNRELATED)
				            .addComponent(btnDeleteNewTask)
				            .addContainerGap(59, Short.MAX_VALUE))
				);
				
				JList list = new JList();
				scrollPane.setViewportView(list);
				panel.setLayout(gl_panel);
				panelProject.setLayout(gl_panelProject);
				
				JList<Project> lstProjects = new JList<Project>();
				scrollPaneProjects.setViewportView(lstProjects);
				panelProjects.setLayout(gl_panelProjects);
				
				JPanel panelReports = new JPanel();
				tabbedPaneAllMain.addTab("Reports", null, panelReports, null);
				
				JScrollPane scrollPane_2 = new JScrollPane();
				
				JButton btnGenerateReport = new JButton("Generate Report");
				
				JComboBox comboBox_11 = new JComboBox();
				
				JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
				GroupLayout gl_panelReports = new GroupLayout(panelReports);
				gl_panelReports.setHorizontalGroup(
				    gl_panelReports.createParallelGroup(Alignment.LEADING)
				        .addGroup(gl_panelReports.createSequentialGroup()
				            .addContainerGap()
				            .addGroup(gl_panelReports.createParallelGroup(Alignment.LEADING)
				                .addGroup(gl_panelReports.createSequentialGroup()
				                    .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 796, GroupLayout.PREFERRED_SIZE)
				                    .addContainerGap())
				                .addGroup(gl_panelReports.createSequentialGroup()
				                    .addComponent(comboBox_11, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
				                    .addPreferredGap(ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
				                    .addComponent(btnGenerateReport)
				                    .addGap(249))
				                .addGroup(gl_panelReports.createSequentialGroup()
				                    .addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, 784, GroupLayout.PREFERRED_SIZE)
				                    .addContainerGap())))
				);
				gl_panelReports.setVerticalGroup(
				    gl_panelReports.createParallelGroup(Alignment.LEADING)
				        .addGroup(Alignment.TRAILING, gl_panelReports.createSequentialGroup()
				            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				            .addGroup(gl_panelReports.createParallelGroup(Alignment.BASELINE)
				                .addComponent(btnGenerateReport)
				                .addComponent(comboBox_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				            .addPreferredGap(ComponentPlacement.RELATED)
				            .addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
				            .addGap(11)
				            .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE)
				            .addContainerGap())
				);
				
				JPanel panel_1 = new JPanel();
				tabbedPane_1.addTab("Report 1", null, panel_1, null);
				
				JPanel panel_2 = new JPanel();
				tabbedPane_1.addTab("Report2", null, panel_2, null);
				
				JEditorPane editorPane = new JEditorPane();
				scrollPane_2.setViewportView(editorPane);
				panelReports.setLayout(gl_panelReports);
				
				JPanel panelMain = new JPanel();
				tabbedPaneAllMain.addTab("Timesheet", null, panelMain, null);
				
				JLabel lblTask = new JLabel("Task");
				
				JComboBox comboBox_1 = new JComboBox();
				
				JScrollPane scrollPane_1 = new JScrollPane();
				
				JLabel lblDateSubmitted = new JLabel("Date Submitted");
				
				JLabel lblProject = new JLabel("Project");
				
				JComboBox comboBox_2 = new JComboBox();
				
				JButton btnSubmit = new JButton("Submit");
				
				JButton btnSave = new JButton("Save");
				
				JLabel lblWeekHours = new JLabel("Week Hours");
				
				textField_5 = new JTextField();
				textField_5.setColumns(10);
				
				JLabel label_2 = new JLabel("Project");
				
				JComboBox comboBox_3 = new JComboBox();
				
				JLabel label_3 = new JLabel("Project");
				
				JComboBox comboBox_4 = new JComboBox();
				
				JLabel label_4 = new JLabel("Project");
				
				JComboBox comboBox_5 = new JComboBox();
				
				JLabel label_5 = new JLabel("Project");
				
				JComboBox comboBox_6 = new JComboBox();
				
				JLabel label_6 = new JLabel("Task");
				
				JComboBox comboBox_7 = new JComboBox();
				
				JLabel label_7 = new JLabel("Week Hours");
				
				textField_6 = new JTextField();
				textField_6.setColumns(10);
				
				JLabel label_8 = new JLabel("Task");
				
				JComboBox comboBox_8 = new JComboBox();
				
				JLabel label_9 = new JLabel("Week Hours");
				
				textField_7 = new JTextField();
				textField_7.setColumns(10);
				
				JLabel label_10 = new JLabel("Task");
				
				JComboBox comboBox_9 = new JComboBox();
				
				JLabel label_11 = new JLabel("Week Hours");
				
				textField_8 = new JTextField();
				textField_8.setColumns(10);
				
				JLabel label_12 = new JLabel("Task");
				
				JComboBox comboBox_10 = new JComboBox();
				
				JLabel label_13 = new JLabel("Week Hours");
				
				textField_9 = new JTextField();
				textField_9.setColumns(10);
				GroupLayout gl_panelMain = new GroupLayout(panelMain);
				gl_panelMain.setHorizontalGroup(
					gl_panelMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMain.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelMain.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelMain.createSequentialGroup()
											.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelMain.createSequentialGroup()
													.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panelMain.createSequentialGroup()
													.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
											.addGroup(gl_panelMain.createParallelGroup(Alignment.TRAILING)
												.addComponent(label_10)
												.addComponent(label_12)))
										.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING, false)
											.addGroup(gl_panelMain.createSequentialGroup()
												.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_panelMain.createSequentialGroup()
														.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_panelMain.createSequentialGroup()
														.addComponent(lblProject)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
												.addGap(47)
												.addGroup(gl_panelMain.createParallelGroup(Alignment.TRAILING)
													.addComponent(lblTask)
													.addComponent(label_6)))
											.addGroup(gl_panelMain.createSequentialGroup()
												.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label_8))))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelMain.createParallelGroup(Alignment.TRAILING)
											.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
												.addComponent(comboBox_10, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox_9, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
										.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_8, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelMain.createSequentialGroup()
											.addGap(158)
											.addComponent(btnSave))
										.addGroup(gl_panelMain.createSequentialGroup()
											.addGap(14)
											.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelMain.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(lblWeekHours, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(label_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(label_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(label_11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(label_13))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textField_5, 0, 0, Short.MAX_VALUE)
												.addComponent(textField_8, 0, 0, Short.MAX_VALUE)
												.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
												.addComponent(textField_7, 0, 0, Short.MAX_VALUE)
												.addComponent(textField_6, 0, 0, Short.MAX_VALUE))))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSubmit)
									.addGap(36))
								.addComponent(lblDateSubmitted))
							.addGap(101))
				);
				gl_panelMain.setVerticalGroup(
					gl_panelMain.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelMain.createSequentialGroup()
							.addComponent(lblDateSubmitted)
							.addGroup(gl_panelMain.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelMain.createSequentialGroup()
									.addGap(15)
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(17, Short.MAX_VALUE))
								.addGroup(gl_panelMain.createSequentialGroup()
									.addGap(35)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblProject)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTask)
										.addComponent(lblWeekHours)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(22)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_2)
										.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_6)
										.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_7)
										.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(22)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3)
										.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_9)
										.addComponent(label_8)
										.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(24)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4)
										.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_11)
										.addComponent(label_10)
										.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(22)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_5)
										.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_13)
										.addComponent(label_12)
										.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
									.addGroup(gl_panelMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSave)
										.addComponent(btnSubmit))
									.addGap(25))))
				);
				
				JList list_1 = new JList();
				list_1.setModel(new AbstractListModel() {
				    String[] values = new String[] {"12/07/2014"};
				    public int getSize() {
				        return values.length;
				    }
				    public Object getElementAt(int index) {
				        return values[index];
				    }
				});
				scrollPane_1.setViewportView(list_1);
				panelMain.setLayout(gl_panelMain);
				frmRexster.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panelStatus, lblStatus, panelMain, panelResources, scrollPaneResources, lstResources, btnAddNewResource, btnResourceTerminate, tabbedPane_resources, panelResourceForm, tabbedPaneAllMain, btnSavePersonForm, lblFirstName, lblLastName, txtFirstName, txtLastName, panelProjects}));
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
