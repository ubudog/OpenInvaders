package net.ubudog.openinvaders.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Portal {
	Image img;
	
	int x = 460;
	int y = 8;
	
	public static boolean alive;
	
	private String USER_HOME = System.getProperty("user.home");
	private String MAIN_DIR = USER_HOME + "/.openinvaders";
	
	public Portal() {
		alive = true;
		
		if (getAlive() == true) { 
			ImageIcon i = new ImageIcon(MAIN_DIR + "/portal.png");
			img = i.getImage();
		} else if (getAlive() == false) {
			ImageIcon i = new ImageIcon(MAIN_DIR + "/portaldead.png");
			img = i.getImage();
		}
	}
	
	public boolean getAlive() { 
		return alive;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getPortal() {
		return img;
	}
}
