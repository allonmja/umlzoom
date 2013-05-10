package com.allonapps.umlzoom.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;

import com.allonapps.umlzoom.controllers.DrawingJPanelController;
import com.allonapps.umlzoom.models.Line;
import com.allonapps.umlzoom.models.Point;
import com.allonapps.umlzoom.models.Point.SnapSide;

public class DrawingJPanel extends JPanelMouseListener {

	private static final long serialVersionUID = 1L;
	DrawingJPanelController controller;
	private ArrayList<UMLBoxJPanel> boxPanels;
	private Line line;
	private ArrayList<Line> lineArray;
	boolean lineMode = false;
	boolean drawingLine = false;

	public DrawingJPanel(DrawingJPanelController c) {
		super();
		controller = c;
	}

	public void initUI() {
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public Dimension getPreferredSize() {return new Dimension(700, 600);}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < boxPanels.size(); i++) {
			add(boxPanels.get(i));
			boxPanels.get(i).repaint();
		}
		if (lineArray !=null){
			for (int m =0; m<lineArray.size(); m++){
				Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6));
				g2.drawLine(lineArray.get(m).first.x, lineArray.get(m).first.y, lineArray.get(m).last.x, lineArray.get(m).last.y);
			}
		}

	}

	public void setBoxPanels(ArrayList<UMLBoxJPanel> bp) {
		clearBoxPanels();
		boxPanels = bp;
		invalidate();
	}
	
	public void clearBoxPanels(){
		removeAll();
		boxPanels=null;
		repaint();
	}
	public void bubbleBox(UMLBoxJPanel umlBoxJPanel) { 

		controller.bubbleBox(umlBoxJPanel);}

	public void setLineMode(boolean lineMode) {
		this.lineMode = lineMode;
		for (UMLBoxJPanel b : boxPanels){
			b.setLineMode(lineMode);
		}
	}
	
	@Override 
	public void mouseMoved(MouseEvent e){
		if (drawingLine){
			line.last = new Point(e.getX(), e.getY(), SnapSide.NONE);
			if (!lineArray.contains(line)){
				System.out.println("adding line");
				lineArray.add(line);
			}
			repaint();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		if (lineArray!=null && drawingLine){ deleteLastLine(); drawingLine = false;}
	}

	public void deleteLastLine() {
		if (lineArray !=null){
			lineArray.remove(lineArray.size()-1);
			repaint();
		}
	}

	public void addLine(Line l) {
		line = l;
		if (lineArray == null){lineArray = new ArrayList<Line>();}
//		lineArray.add(line);
		drawingLine = true;
	}
	
	public Line getLine(){return line;}
}