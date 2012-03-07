package net.ubudog.openinvaders;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {
	Image img;
	
	int x; 
	int y;
	
	private String USER_HOME = System.getProperty("user.home");
	private String MAIN_DIR = USER_HOME + "/.openinvaders";
	
	public Bullet(int x, int y) {
		ImageIcon i = new ImageIcon(MAIN_DIR + "/bullet.png");
		img = i.getImage();
	}
	
	public void move() {	
		
	}
	
	public int getX() {
		return y;
	}
	
	public int getY() {
		return x;
	}
	
	public Image getBullet() {
		return img;
	}
}
