package com.earasoft.learning1.gui.views;

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

import com.earasoft.learning1.gui.Controller;
import com.earasoft.learning1.gui.ViewBind;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Login {
    
    private JFrame frmLogin;
    private JPasswordField passwordField;
    private JTextField txtUsername;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Login window = new Login();
//                    window.getFrmLogin().setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
    
    private Controller controller;
    private ViewBind view;
    
    /**
     * Create the application.
     */
    public Login(Controller controller, ViewBind view) {
        // TODO Auto-generated constructor stub
    	this.controller = controller;
    	this.view = view;
    	
    	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
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
        GroupLayout gl_panelStatus = new GroupLayout(panelStatus);
        gl_panelStatus.setHorizontalGroup(
            gl_panelStatus.createParallelGroup(Alignment.LEADING)
                .addGap(0, 459, Short.MAX_VALUE)
        );
        gl_panelStatus.setVerticalGroup(
            gl_panelStatus.createParallelGroup(Alignment.LEADING)
                .addGap(0, 146, Short.MAX_VALUE)
        );
        panelStatus.setLayout(gl_panelStatus);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(txtUsername.getText().equals("admin") && Arrays.toString(passwordField.getPassword()).hashCode() == 788759259){
                    System.out.println("Success");
                }else{
                    System.out.println(Arrays.toString(passwordField.getPassword()).hashCode());
                    System.out.println("Failed");
                }
                        
            }
        });
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Dialog", Font.PLAIN, 18));
        txtUsername.setColumns(10);
        
        passwordField = new JPasswordField();
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
    }

    public JFrame getFrmLogin() {
        return frmLogin;
    }

    public void setFrmLogin(JFrame frmLogin) {
        this.frmLogin = frmLogin;
    }
}
