package net.ubudog.openinvaders;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet { 
	
	int x; 
	int y; 
	Image img;
	String HOME_DIR = System.getProperty("user.home");
	String MAIN_DIR = HOME_DIR + "/.openinvaders";
	
	boolean visible;
	
	public Bullet(int startX, int startY) {
		x = startX;
		y = startY; 
		
		ImageIcon newBullet = new ImageIcon(MAIN_DIR + "/bullet.png");
		img = newBullet.getImage();
		
		visible = true;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public int getX() { 
		return x; 
	}
	
	public int getY() { 
		return y; 
	}
	
	public Image getBullet() {
		return img;
	}
	
	public void move() {
		y = y - 3; 
		if (y > 515) { 
			visible = false;
		}
	}
}