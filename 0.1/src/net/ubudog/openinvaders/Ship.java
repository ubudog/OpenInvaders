package net.ubudog.openinvaders;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ship {
	
	int x; 
	int y; 
	
	Image img; 
	Image death;
	
	String USER_HOME = System.getProperty("user.home");
	String MAIN_DIR = USER_HOME + "/.openinvaders";
	
	public Ship() {
		ImageIcon i = new ImageIcon(MAIN_DIR + "/ship.png");
		img = i.getImage();
		
		i = new ImageIcon(MAIN_DIR + "/explode.gif");
		death = i.getImage();
	}
	
	public void move(int dx, int dy) {
		x = dx; 
		y = dy;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y; 
	}
	
	public Image getShip() {
		return img;
	}
	
	public Image getDeath() {
		return death;
	}
}
