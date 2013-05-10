package com.allonapps.umlzoom.controllers;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.allonapps.umlzoom.models.UMLBox;
import com.allonapps.umlzoom.views.MainFrame;
import com.allonapps.umlzoom.views.UMLBoxJPanel;

public class MainFrameController {
	
	private MainFrame mainFrame;
	private DrawingJPanelController drawingJPanelController;
	private UtilityJPanelController utilityJPanelController;
	private ArrayList<JPanel> panels;
	private ArrayList<UMLBox> boxes;
	private boolean lineMode = false;
	
	public MainFrameController(){
		init();
		mainFrame = new MainFrame();
		sendPanels();
		mainFrame.initUI();
	}

	private void init() {
		panels = new ArrayList<JPanel>();
		boxes = new ArrayList<UMLBox>();
		drawingJPanelController = new DrawingJPanelController(this);
		utilityJPanelController = new UtilityJPanelController(this);
		updateObservers();

		panels.add(drawingJPanelController.getDrawingJPanel());
		panels.add(utilityJPanelController.getUtilityJPanel());
		updateObservers();
		
	}

	private void updateObservers(){
		drawingJPanelController.updateBoxes(boxes);
		drawingJPanelController.setLineMode(lineMode);
	}
	
	public void addBox(UMLBox b){
		boxes.add(b);
		updateObservers();
	}
	
	public void sendPanels() {mainFrame.setPanels(panels);}
	
	/**
	 * function: processBubbleBox()
	 * description: Process a UMLBox JPanel to sync it with our list of
	 * UMLBoxes.
	 * 
	 */
	public void processBubbleBox(UMLBoxJPanel umlBoxJPanel) {

		for (int i=0; i<boxes.size(); i++){
			if (umlBoxJPanel.getID() == boxes.get(i).getID()){
				System.out.println("processBubbleBox with" + umlBoxJPanel.getTitle());
				UMLBox boxToEdit = boxes.get(i);
				boxToEdit.setX(umlBoxJPanel.getX());
				boxToEdit.setY(umlBoxJPanel.getY());
				boxToEdit.setTitle(umlBoxJPanel.getTitle());
				break;
			}
		}
	}

	public void setLineMode(boolean lm) {
		lineMode = lm;
		updateObservers();
	}

	public void deleteLastLine() {
		drawingJPanelController.deleteLastLine();
		
	}

}
