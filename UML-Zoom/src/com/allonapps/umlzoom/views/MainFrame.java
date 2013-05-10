package com.allonapps.umlzoom.views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private ArrayList<JPanel> panels;
	
	public MainFrame() {
	}
	
	public void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 0;
        int height=0;
        //for (int i=0; i<panels.size(); i++){

            width =panels.get(0).getPreferredSize().width;
            height =panels.get(0).getPreferredSize().height + panels.get(1).getPreferredSize().height;
            add(panels.get(0), BorderLayout.CENTER);
            add(panels.get(1), BorderLayout.LINE_END);
       // }
        
		this.setSize(width, height);
		setVisible(true);
	}

	public void setPanels(ArrayList<JPanel> p) {
		panels = p;
		
	}
	
}
