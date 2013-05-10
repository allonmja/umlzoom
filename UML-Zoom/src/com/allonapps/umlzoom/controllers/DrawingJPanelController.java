package com.allonapps.umlzoom.controllers;

import java.util.ArrayList;

import com.allonapps.umlzoom.interfaces.IUMLBoxObserver;
import com.allonapps.umlzoom.models.UMLBox;
import com.allonapps.umlzoom.views.DrawingJPanel;
import com.allonapps.umlzoom.views.UMLBoxJPanel;
import com.allonapps.umlzoom.views.UMLJPopupMenu;

/**
 * 
 * @author amimja0
 *
 */
public class DrawingJPanelController implements IUMLBoxObserver {
	private MainFrameController controller;
	private DrawingJPanel drawingJPanel;
	private ArrayList<UMLBox> boxes;
	
	DrawingJPanelController(MainFrameController c){
		controller = c;
		drawingJPanel = new DrawingJPanel(this);
		sendBoxPanels();
		drawingJPanel.initUI();
	}
	public DrawingJPanel getDrawingJPanel(){return drawingJPanel;}

	@Override
	public void updateBoxes(ArrayList<UMLBox> b) {boxes = b; sendBoxPanels(); drawingJPanel.repaint();}
		
	/**
	 * function: sendBoxPanels()
	 * description: Sends boxPanels to the DrawingJPanel
	 */
	public void sendBoxPanels(){

		ArrayList<UMLBoxJPanel>boxPanels = new ArrayList<UMLBoxJPanel>();
		if (boxes!=null){
			for (int i=0; i<boxes.size(); i++){
				UMLBoxJPanel panelToAdd = new UMLBoxJPanel(boxes.get(i), drawingJPanel);
				UMLJPopupMenu popup = new UMLJPopupMenu(panelToAdd);
				panelToAdd.setComponentPopupMenu(popup);
				
				boxPanels.add(panelToAdd);
			}
			drawingJPanel.setBoxPanels(boxPanels);
		}

		boxPanels = null;
	}

	/**
	 * function: bubleBox()
	 * description: Sends the UMLBoxJPanel to the controller
	 */
	public void bubbleBox(UMLBoxJPanel umlBoxJPanel) {
		controller.processBubbleBox(umlBoxJPanel);	
	}

	/**
	 * function: setLineMode()
	 * description: Specifies mode for drawing lines
	 */
	public void setLineMode(boolean lineMode) {
		System.out.println("lineMode: " + lineMode);
		drawingJPanel.setLineMode(lineMode);
	}
	
	/**
	 * function: deleteLastLine()
	 * description: Used to undo last line addition
	 */
	public void deleteLastLine() {
		drawingJPanel.deleteLastLine();
	}

}
