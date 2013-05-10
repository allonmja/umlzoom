package com.allonapps.umlzoom.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.allonapps.umlzoom.controllers.UtilityJPanelController;
import com.allonapps.umlzoom.models.UMLBox;

public class UtilityJPanel extends JPanel implements ActionListener{

	private UtilityJPanelController controller;
	private static final long serialVersionUID = 1L;
	private static final int H_PADDING = 10;
	private static final int V_PADDING = 35;
	private SpringLayout layout;
	private static int ID_COUNT = 0;
	private boolean lineMode;
	
	private JButton addButton, lineButton, arrowButton;
	private JTextField tfTitle;
	private JTextField tfPublic;
	private JTextField tfPrivate;
	private JTextField tfFile;

	public UtilityJPanel(){
	}
	
	public void initUI() {
		
		layout = new SpringLayout();
		setLayout(layout);
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        
        
        BufferedImage lineButtonIcon;
        BufferedImage arrowButtonIcon;
		try {
			lineButtonIcon = ImageIO.read(new File("resources/images/line.png"));
	        lineButton = new JButton(new ImageIcon(lineButtonIcon));
	        lineButton.addActionListener(this);
	        arrowButtonIcon = ImageIO.read(new File("resources/images/arrow.png"));
	        arrowButton = new JButton(new ImageIcon(arrowButtonIcon));
	        arrowButton.addActionListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        JLabel lblTitle = new JLabel("Title");
        JLabel lblPublic = new JLabel("Public");
        JLabel lblPrivate = new JLabel("Private");
        JLabel lblFile = new JLabel("File");
        tfTitle = new JTextField();
        tfPublic = new JTextField();
        tfPrivate = new JTextField();
        tfFile = new JTextField();

        add(addButton);
        add(lineButton);
        add(arrowButton);
        add(lblTitle);
        add(lblPublic);
        add(lblPrivate);
        add(lblFile);
        add(tfTitle);
        add(tfPublic);
        add(tfPrivate);
        add(tfFile);
        
        layout.putConstraint(SpringLayout.WEST, addButton, H_PADDING, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, addButton, V_PADDING, SpringLayout.NORTH, this);
                
        MakeLeftColumn(lblTitle, addButton);
        MakeLeftColumn(lblPublic, lblTitle);
        MakeLeftColumn(lblPrivate, lblPublic);
        MakeLeftColumn(lblFile, lblPrivate);
        MakeLeftColumn(lineButton, lblFile);
        MakeLeftColumn(arrowButton, lineButton);
        
        MakeRightColumn(tfTitle, lblTitle, addButton);
        MakeRightColumn(tfPublic, lblPublic, tfTitle);
        MakeRightColumn(tfPrivate, lblPrivate, tfPublic);
        MakeRightColumn(tfFile, lblFile, tfPrivate);
        
        
        
	}
	public void MakeLeftColumn(JComponent toAdd, JComponent below){
		layout.putConstraint(SpringLayout.WEST, toAdd, H_PADDING, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, toAdd, V_PADDING, SpringLayout.NORTH, below);	
	}
	
	public void MakeRightColumn(JComponent toAdd, JComponent toRightOf, JComponent below){
		layout.putConstraint(SpringLayout.WEST, toAdd, H_PADDING, SpringLayout.EAST, toRightOf);
		layout.putConstraint(SpringLayout.EAST, toAdd, -H_PADDING, SpringLayout.EAST, this);	
		layout.putConstraint(SpringLayout.NORTH, toAdd, V_PADDING, SpringLayout.NORTH, below);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(200,600);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton ){
			addBox();
		}else if (e.getSource() == lineButton){
			toggleLineMode();
		}else if (e.getSource() == arrowButton){
			deleteLastLine();
		}
	}

	private void deleteLastLine() {
		controller.deleteLastLine();
	}

	private void toggleLineMode() {
		if (lineMode){lineMode=false;}
		else {lineMode = true;}
		controller.setLineMode(lineMode);
	}

	private void addBox() {
		UMLBox box = new UMLBox();
		box.setTitle(tfTitle.getText());
		box.setFileName(tfFile.getText());
		List<String> privateMethods = parseMethods(tfPrivate.getText());
		for (int i=0; i<privateMethods.size(); i++){
			box.addPrivateMethod(privateMethods.get(i));
		}
		List<String> publicMethods = parseMethods(tfPublic.getText());
		for (int i=0; i<publicMethods.size(); i++){
			box.addPublicMethod(publicMethods.get(i));
		}
		box.setID(ID_COUNT);
		ID_COUNT++;
		controller.addBox(box);
	}
	private List<String> parseMethods(String text) {
		List<String> items = Arrays.asList(text.split("\\s*,\\s*"));
		return items;
	}

	public void setController(UtilityJPanelController c){controller = c;}

}

