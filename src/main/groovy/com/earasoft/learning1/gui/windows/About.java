package com.earasoft.learning1.gui.windows;

import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earasoft.learning1.gui.Controller;
import com.earasoft.learning1.gui.Init;
import com.earasoft.learning1.gui.ViewBind;

public class About {
    private static final Logger logger = LoggerFactory.getLogger(About.class);
    
    private JFrame frmAbout;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	Init init = new Init();
    }
    
    /**
     * Create the application.
     */
    
    public boolean isRunning(){
        return true;
    }
    private Controller controller;
    private ViewBind view;
    
    /**
     * @wbp.parser.entryPoint
     */
    public About(Controller controller, ViewBind view) {
        this.controller = controller;
        this.view = view;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setFrmAbout(new JFrame());
        getFrmAbout().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                controller.setAboutWindow(null);
                logger.debug("Closed Window");
            }
        });
        getFrmAbout().setTitle("About");
        getFrmAbout().setResizable(false);
        getFrmAbout().setType(Type.POPUP);
        getFrmAbout().setBounds(100, 100, 558, 197);
        getFrmAbout().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        JTextArea txtdesc = new JTextArea();
        txtdesc.setLineWrap(true);
        txtdesc.setEditable(false);
        txtdesc.setWrapStyleWord(true);
        txtdesc.setBackground(UIManager.getColor("InternalFrame.borderColor"));
        txtdesc.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtdesc.setText("This Software was written for Database Class Fall 2014");
        GroupLayout groupLayout = new GroupLayout(getFrmAbout().getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtdesc, GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        				.addComponent(btnOk, Alignment.TRAILING))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(txtdesc, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnOk)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getFrmAbout().getContentPane().setLayout(groupLayout);
        
        controller.setAboutWindow(this);
        ViewBind.centerWindow(this.frmAbout);
        logger.debug("About Window Initialized");
    }
    
    public void dispose(){
    	logger.debug("Disposing About Window");
        getFrmAbout().setVisible(false); //Hide Window
        getFrmAbout().dispose(); //Destroy the JFrame object
    }

    public JFrame getFrmAbout() {
        return frmAbout;
    }

    public void setFrmAbout(JFrame frmAbout) {
        this.frmAbout = frmAbout;
        frmAbout.setAlwaysOnTop(true);
    }
}
