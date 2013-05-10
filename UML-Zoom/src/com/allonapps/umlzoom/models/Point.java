package com.allonapps.umlzoom.models;

public class Point{
	public enum SnapSide{
		NONE, LEFT, RIGHT, TOP, BOTTOM
	}
	public int x;
	public int y;
	public SnapSide side;
	public Point(int x, int y, SnapSide side){
		this.x = x;
		this.y = y;
		this.side = side;
	}
}
