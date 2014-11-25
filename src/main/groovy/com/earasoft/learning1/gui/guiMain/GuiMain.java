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
import com.earasoft.learning1.gui.Controller;
import com.earasoft.learning1.gui.Init;
import com.earasoft.learning1.gui.Models;
import com.earasoft.learning1.gui.ViewBind;
import com.earasoft.learning1.gui.windows.About;

public class GuiMain {
    private static final Logger logger = LoggerFactory.getLogger(GuiMain.class);

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
		Init init = new Init();
	}

	/**
     * Create the application.
     * @wbp.parser.entryPoint
     */
	public GuiMain(Controller controller, ViewBind view) {
	    this.controller = controller;
	    this.view = view;
	    initialize();
    }

    /**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        view.setGuiMainView(this);
        
		setFrmRexster(new JFrame());
		getFrmRexster().addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        About aboutWindowInstance = controller.getAboutWindow();
		        if(aboutWindowInstance != null){
		            aboutWindowInstance.dispose();
		        }
		    }
		});
		
		getFrmRexster().setTitle("Client");
		getFrmRexster().setBounds(100, 100, 837, 657);
		getFrmRexster().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout groupLayout = new GroupLayout(getFrmRexster().getContentPane());
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
		
		JScrollPane scrollPanePeople = new JScrollPane();
		
		JButton btnPersonAdd = new JButton("Add");
		btnPersonAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        view.getGuiMainView().personForm.reset();
		    }
		});
		
		JButton btnPersonRemove = new JButton("Remove");
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panelPeople = new GroupLayout(panelPeople);
		gl_panelPeople.setHorizontalGroup(
		    gl_panelPeople.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panelPeople.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_panelPeople.createParallelGroup(Alignment.LEADING)
		                .addComponent(scrollPanePeople, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
		                .addGroup(gl_panelPeople.createSequentialGroup()
		                    .addGap(10)
		                    .addGroup(gl_panelPeople.createParallelGroup(Alignment.TRAILING, false)
		                        .addComponent(btnPersonRemove, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                        .addComponent(btnPersonAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))))
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(51, Short.MAX_VALUE))
		);
		gl_panelPeople.setVerticalGroup(
		    gl_panelPeople.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panelPeople.createSequentialGroup()
		            .addGap(12)
		            .addGroup(gl_panelPeople.createParallelGroup(Alignment.LEADING, false)
		                .addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addGroup(gl_panelPeople.createSequentialGroup()
		                    .addComponent(scrollPanePeople, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
		                    .addGap(49)
		                    .addComponent(btnPersonAdd)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(btnPersonRemove)))
		            .addGap(15))
		);
		
		JPanel panelPersonForm = new JPanel();
		tabbedPane_1.addTab("Person Info", null, panelPersonForm, null);
		panelPersonForm.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblFirstName = new JLabel("First Name");
		
		txtFirstName = new JTextField();
		txtFirstName.setEnabled(false);
		txtFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				view.getGuiMainView().personForm.validate();
			}
		});
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		
		txtLastName = new JTextField();
		txtLastName.setEnabled(false);
		txtLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				view.getGuiMainView().personForm.validate();
			}
		});
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
		        .addGroup(Alignment.LEADING, gl_panelPersonForm.createSequentialGroup()
		            .addContainerGap()
		            .addComponent(lblFirstName)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
		            .addPreferredGap(ComponentPlacement.RELATED)
		            .addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(25, Short.MAX_VALUE))
		        .addGroup(gl_panelPersonForm.createSequentialGroup()
		            .addContainerGap(502, Short.MAX_VALUE)
		            .addComponent(btnSavePersonForm)
		            .addContainerGap())
		);
		gl_panelPersonForm.setVerticalGroup(
		    gl_panelPersonForm.createParallelGroup(Alignment.TRAILING)
		        .addGroup(Alignment.LEADING, gl_panelPersonForm.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(gl_panelPersonForm.createParallelGroup(Alignment.BASELINE)
		                .addComponent(lblFirstName)
		                .addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		                .addComponent(lblLastName)
		                .addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addPreferredGap(ComponentPlacement.RELATED, 414, Short.MAX_VALUE)
		            .addComponent(btnSavePersonForm)
		            .addContainerGap())
		);
		panelPersonForm.setLayout(gl_panelPersonForm);
		
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
                    
                    view.getGuiMainView().personForm.show(person);//  showPersonOnForm(person);
                    
                }
		    }
		});

		lstPeople.setModel(Models.getListModel());
		scrollPanePeople.setViewportView(lstPeople);
		panelPeople.setLayout(gl_panelPeople);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		getFrmRexster().getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		getFrmRexster().setJMenuBar(menuBar);

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
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
		    
		    
		    public void actionPerformed(ActionEvent e) {
		        
		        if(controller.getAboutWindow() == null){
		            EventQueue.invokeLater(new Runnable() {
	                    public void run() {
	                        try {
	                            About window = new About(controller, view);
	                            
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
		view.getGuiMainView().center();
		controller.loadPeople();
		logger.debug("Main Window Init");
		
		
	}

    public JFrame getFrmRexster() {
        return frmRexster;
    }

    public void setFrmRexster(JFrame frmRexster) {
        this.frmRexster = frmRexster;
        frmRexster.setMinimumSize(new Dimension(837, 671));
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
