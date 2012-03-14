package net.ubudog.openinvaders;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Ship {
	
	int x; 
	int y; 
	
	Image img; 
	Image death;
	
	static boolean alive;
	
	public static boolean getAlive() {
		return alive;
	}
	
	String USER_HOME = System.getProperty("user.home");
	String MAIN_DIR = USER_HOME + "/.openinvaders";
	
	public Ship() {
		ImageIcon i = new ImageIcon(MAIN_DIR + "/ship.png");
		img = i.getImage();
		
		alive = true;
		
		i = new ImageIcon(MAIN_DIR + "/explode.gif");
		death = i.getImage();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
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
		if (getAlive() == true) {
			return img;
		} else {
			return death;
		}
	}
}
