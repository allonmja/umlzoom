package com.allonapps.umlzoom.controllers;

import com.allonapps.umlzoom.models.UMLBox;
import com.allonapps.umlzoom.views.UtilityJPanel;

public class UtilityJPanelController {
	private MainFrameController controller;
	private UtilityJPanel utilityJPanel;
	
	UtilityJPanelController(MainFrameController c){
		controller = c;
		utilityJPanel = new UtilityJPanel();
		utilityJPanel.initUI();
		utilityJPanel.setController(this);
	}
	
	public UtilityJPanel getUtilityJPanel(){return utilityJPanel;}
	public void addBox(UMLBox box){
		controller.addBox(box);
	}

	public void setLineMode(boolean lm) {
		controller.setLineMode(lm);
	}

	public void deleteLastLine() {
		controller.deleteLastLine();
		
	}
	
}
