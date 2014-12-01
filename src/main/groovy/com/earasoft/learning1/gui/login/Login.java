package com.earasoft.learning1.gui.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;

import com.earasoft.learning1.gui.SharedController;
import com.earasoft.learning1.gui.Boot;
import com.earasoft.learning1.gui.ViewBind;
import com.earasoft.learning1.gui.guiMain.GuiMain;
import com.earasoft.learning1.gui.guiMain.GuiMainController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.JTextArea;

import org.shared.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class Login {
	private static final Logger logger = LoggerFactory.getLogger(Login.class);
   
	private final LoginController loginController;
    private final SharedController sharedController;
    private final ViewBind viewBind;
    
	protected JFrame frmLogin;
	protected JPasswordField passwordField;
	protected JTextField txtUsername;
	protected JTextArea txtStatus;
    
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
    public Login(LoginController controller, ViewBind viewBind, SharedController sharedController) {
    	this.loginController = controller;
    	this.viewBind = viewBind;
    	this.sharedController = sharedController;
    	initialize();    	
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	viewBind.setLoginView(this);
    	
        setFrmLogin(new JFrame());
        getFrmLogin().setResizable(false);
        getFrmLogin().setTitle("Login Window");
        getFrmLogin().setBounds(100, 100, 494, 468);
        getFrmLogin().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        JLabel lblWelcome = new JLabel("Management");
        lblWelcome.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JPanel panelStatus = new JPanel();
        panelStatus.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelStatus.setToolTipText("Status");
        
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setFont(new Font("Dialog", Font.BOLD, 18));
        GroupLayout groupLayout = new GroupLayout(getFrmLogin().getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panelStatus, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblWelcome)
                        .addComponent(lblStatus))
                    .addContainerGap(26, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(16)
                    .addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(lblStatus)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panelStatus, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE))
        );
        
        txtStatus = new JTextArea();
        txtStatus.setWrapStyleWord(true);
        txtStatus.setLineWrap(true);
        txtStatus.setBackground(SystemColor.window);
        txtStatus.setEditable(false);
        GroupLayout gl_panelStatus = new GroupLayout(panelStatus);
        gl_panelStatus.setHorizontalGroup(
        	gl_panelStatus.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelStatus.createSequentialGroup()
        			.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panelStatus.setVerticalGroup(
        	gl_panelStatus.createParallelGroup(Alignment.LEADING)
        		.addComponent(txtStatus, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );
        panelStatus.setLayout(gl_panelStatus);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent arg0) {       		
        		if(arg0.getKeyCode() == 10){
        			loginController.authenticate();
        		}
        	}
        });
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	loginController.authenticate(); 
            }
        });
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Dialog", Font.PLAIN, 18));
        txtUsername.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent arg0) {
        		if(arg0.getKeyCode() == 10){
        			loginController.authenticate();;
        		}
        	}
        });
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 18));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                            .addComponent(btnLogin)
                            .addContainerGap())
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                                .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                                    .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(passwordField))
                                .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                                    .addComponent(lblUsername)
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblUsername)
                        .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(82)
                            .addComponent(btnLogin))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(18)
                            .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblPassword)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(12, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        getFrmLogin().getContentPane().setLayout(groupLayout);
        
        viewBind.getLoginView().center();
        sharedController.initialize();
    }

    public JFrame getFrmLogin() {
        return frmLogin;
    }

    public void setFrmLogin(JFrame frmLogin) {
        this.frmLogin = frmLogin;
    }
}
