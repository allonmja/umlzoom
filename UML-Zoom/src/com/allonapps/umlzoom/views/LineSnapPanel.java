package com.allonapps.umlzoom.views;

import java.util.ArrayList;

import com.allonapps.umlzoom.models.Point;
import com.allonapps.umlzoom.models.Point.SnapSide;

public class LineSnapPanel extends JPanelMouseListener {

	private static final long serialVersionUID = 1L;
	
	protected int x, y, width, height;
	private ArrayList<Point> snapPoints;
	
	public Point snapTop(){
		return new Point(x+width/2, y, SnapSide.TOP);
	}
	
	public Point snapBottom(){
		return new Point(x+width/2, y+height, SnapSide.BOTTOM);
	}
	
	public Point snapRight(){
		return new Point(x, y+height/2, SnapSide.RIGHT);
	}
	
	public Point snapLeft(){
		return new Point(x+width, y+height/2, SnapSide.LEFT);
	}
	
	/**
	 * function: centerBox
	 * description: centers the Panel on X,Y.  Also refreshes
	 * the snapPoints so they are in the correct places.
	 */
	protected void centerBox(int x, int y){
		setX(x-width/2);
		setY(y-height/2);
		refreshSnapPoints();
	}
	
	public void addSnapPoint(Point p){
		if (snapPoints == null){
			snapPoints = new ArrayList<Point>();
		}
		snapPoints.add(p);
	}
	
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setWidth(int w){width=w;}
    public void setHeight(int h){height=h;}
    
    public int getX(){return x;}
    public int getY(){return y;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
	
    /**
     * function: refreshSnapPoints
     * description: Refreshes the location of the snap points.  Used
     * when the box moves, to make sure lines come with it.
     */
	public void refreshSnapPoints(){
		if (snapPoints != null){
			for (Point p : snapPoints){
				switch(p.side){
				case LEFT:
					Point sp = snapLeft();
					p.x = sp.x; p.y = sp.y; p.side = sp.side;
					break;
				case RIGHT:
					sp = snapRight();
					p.x = sp.x; p.y = sp.y; p.side = sp.side;
					break;
				case TOP:
					sp = snapTop();
					p.x = sp.x; p.y = sp.y; p.side = sp.side;
					break;
				case BOTTOM:
					sp = snapBottom();
					p.x = sp.x; p.y = sp.y; p.side = sp.side;
					break;
				default:
					break;
				}
			}
		}
	}
	
}
