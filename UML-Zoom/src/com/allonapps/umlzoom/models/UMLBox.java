package com.allonapps.umlzoom.models;

import java.util.ArrayList;

public class UMLBox {
	
	private String title;
	private ArrayList<String> privateMethods;
	private ArrayList<String> publicMethods;
	private int x=0;	//for maintaining x location on screen.  Should really be in UMLBoxJPanel, but whatever.
	private int y=0;	//for maintaining y location on screen.  Should really be in UMLBoxJPanel, but whatever.
	private int id=0; //another hack for maintaining position on screen.  Lets us know which box has been moved.
	private String fileName;
	private ArrayList<Relationship> relationships;
	

	public UMLBox(){
		title = "";
		privateMethods = new ArrayList<String>();
		publicMethods = new ArrayList<String>();
		relationships = new ArrayList<Relationship>();
	}
	
	public void setTitle(String t){title=t;}
	public void addPrivateMethod(String m){privateMethods.add(m);}
	public void addPublicMethod(String m){publicMethods.add(m);}
	
	public String getTitle(){return title;}
	public ArrayList<String> getPrivateMethods(){return privateMethods;}
	public ArrayList<String> getPublicMethods(){return publicMethods;}
	public ArrayList<Relationship> getRelationships(){return relationships;}
	
	public int getX(){return x;}
	public void setX(int x){this.x = x;}
	public int getY(){return y;}
	public void setY(int y){this.y = y;}
	public void addRelationShip(Relationship r){relationships.add(r);}
	
	public int getID(){return id;}
	public void setID(int id){this.id = id;}
	
	public String getFileName(){return fileName;}
	public void setFileName(String f){fileName =f;}

	public enum RType{
		ONE, MANY, CHILD, PARENT
	}
	public class Relationship{
		public UMLBox box;
		public RType type;
	}
	
}
