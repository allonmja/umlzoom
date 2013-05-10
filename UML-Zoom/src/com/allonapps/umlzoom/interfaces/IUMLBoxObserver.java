package com.allonapps.umlzoom.interfaces;

import java.util.ArrayList;

import com.allonapps.umlzoom.models.UMLBox;

public interface IUMLBoxObserver {
	void updateBoxes(ArrayList<UMLBox> b);
}
