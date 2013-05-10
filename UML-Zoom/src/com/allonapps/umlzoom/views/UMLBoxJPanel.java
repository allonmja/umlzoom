package com.allonapps.umlzoom.views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import com.allonapps.umlzoom.models.UMLBox;

public class UMLBoxJPanel extends LineSnapPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int PADDING = 15;
	private static final int FONT_SIZE = 18;
	private static final int MIN_WIDTH = 100;
	private static final int MIN_HEIGHT = 100;
		
	private int id;	//hack for comparing this to real UMLBoxes for location reasons
	private String title;
	private String fileName;
	private ArrayList<String> privateMethods;
	private ArrayList<String> publicMethods;
	private boolean lineMode;
	private static boolean isFirst = true;
	
	public static UMLBoxJPanel cur_parent;				//static parent UMLBoxJPanel when in line mode
	public static UMLBoxJPanel cur_child;				//static child UMLBoxJPanel when in line mode
	
	public ArrayList<UMLBoxJPanel> parents;				//list of the object's parent UMLBoxJPanels
	public ArrayList<UMLBoxJPanel> children;			//list of the object's child UMLBoxJPanels
	
	private DrawingJPanel panel;
	
	public UMLBoxJPanel(DrawingJPanel p){
		super();
		panel = p;
        setBorder(BorderFactory.createLineBorder(Color.black));
        privateMethods = new ArrayList<String>();
        publicMethods = new ArrayList<String>();
        width= MIN_WIDTH;
		height= MIN_HEIGHT;
		setBounds(x, y, width, height);
	}
	
	public UMLBoxJPanel(UMLBox b, DrawingJPanel p){
		super();
		setBorder(BorderFactory.createLineBorder(Color.black));
		panel = p;
		title=b.getTitle();
		fileName = b.getFileName();
		x = b.getX();					//hack for getting saved x location on screen. ugly but ok.
		y = b.getY();  					//hack for getting saved y location on screen. ugly but ok.
		id = b.getID(); 				//hack for comparing this to a real UMLBox.
		privateMethods = b.getPrivateMethods();
		publicMethods = b.getPublicMethods();
		width= MIN_WIDTH;
		height= MIN_HEIGHT;
		setBounds(x, y, width, height);
	}
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
		adjustDimensions(g);
		setBounds(x, y, width, height);
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        
        int loc = 1;
    	g.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE));
        g.drawString(title,PADDING , PADDING*loc); loc++;
        g.drawLine(PADDING, PADDING*2, width-PADDING, PADDING*loc ); loc++;

        if (privateMethods !=null){
	        for (int i=0; i<privateMethods.size(); i++){
	        	g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
	        	g.drawString(privateMethods.get(i), PADDING, PADDING*(loc));
	        	loc ++;
	        }
        }
        
        g.drawLine(PADDING, PADDING*loc, width-PADDING, PADDING*loc ); loc++;
        
        if (publicMethods !=null){
	        for (int i=0; i<publicMethods.size(); i++){
	        	g.drawString(publicMethods.get(i), PADDING, PADDING*(loc));
	        	loc ++;
	        }
        }      
    } 
    
    public void setPanel(DrawingJPanel p){panel = p;}
    public void setTitle(String t){title = t;}
    public void setFileName(String f){fileName = f;}
    
    public void addPrivateMethod(String priv){privateMethods.add(priv);repaint();}
    public void addPublicMethod(String pub){publicMethods.add(pub);repaint();}
    
    public int getID(){return id;}
    public String getTitle(){return title;}
    
	@Override
	public void mouseDragged(MouseEvent e){
		centerBox(x+e.getX(), y+e.getY());
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		if (lineMode){
			if (cur_parent==null){
				/*Line l = new Line(snapTop(), null); 
				panel.addLine(l);
				addSnapPoint(panel.getLine().getFirst());*/
				cur_parent = this;
			}
			else{ 
				/*panel.getLine().setLast(snapTop());
				addSnapPoint(panel.getLine().getLast());*/
				cur_child = this;
				;
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		if (e.getClickCount() == 2){
			try {
				Desktop.getDesktop().edit(new File(fileName));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void mouseReleased(MouseEvent arg0){
		bubbleBox();
	}
	
	/**
	 * function: adjustDimensions()
	 * description: Adjusts the dimensions of the UML box.  If there
	 * is more text, it will be wider.  If there are more methods, it
	 * will be shorter.
	 */
	private void adjustDimensions(Graphics graphics){
		FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
		int h = 0;
		for (int i=0; i<privateMethods.size(); i++){
			int w = metrics.stringWidth(privateMethods.get(i));
			if (w+4*PADDING >width){
				System.out.println("w is:" +w);
				setWidth(w+4*PADDING);
			}
			h+=metrics.getHeight();
		}
		for (int i=0; i<publicMethods.size(); i++){
			int w = metrics.stringWidth(publicMethods.get(i));
			if (w+4*PADDING >width){
				setWidth(w+4*PADDING);
			}
			h+=metrics.getHeight();
		}
		if (h+3*PADDING>MIN_HEIGHT){
			setHeight(h + 3*PADDING);
		}
	}
	
	
	public void bubbleBox() { panel.bubbleBox(this);}

	public void setLineMode(boolean lineMode) {this.lineMode = lineMode;}
}

