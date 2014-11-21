package com.earasoft.learning1.gui.views;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earasoft.learning1.gui.Controller;
import com.earasoft.learning1.gui.ViewBind;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About {
    private static final Logger logger = LoggerFactory.getLogger(About.class);
    
    private JFrame frmAbout;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        //Use Init.java to start window
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    About window = new About();
//                    window.frmAbout.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        
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
        getFrmAbout().setBounds(100, 100, 450, 197);
        getFrmAbout().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        GroupLayout groupLayout = new GroupLayout(getFrmAbout().getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                    .addContainerGap(387, Short.MAX_VALUE)
                    .addComponent(btnOk)
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                    .addContainerGap(135, Short.MAX_VALUE)
                    .addComponent(btnOk)
                    .addContainerGap())
        );
        getFrmAbout().getContentPane().setLayout(groupLayout);
        
        controller.setAboutWindow(this);
        
        logger.debug("About Window Initialized");
    }
    
    public void dispose(){
        getFrmAbout().setVisible(false); //you can't see me!
        getFrmAbout().dispose(); //Destroy the JFrame object
    }

    public JFrame getFrmAbout() {
        return frmAbout;
    }

    public void setFrmAbout(JFrame frmAbout) {
        this.frmAbout = frmAbout;
    }
}
