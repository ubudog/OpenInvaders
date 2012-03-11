package net.ubudog.openinvaders;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Portal {
	Image img;
	
	int x = 460;
	int y = 8;
	
	private String USER_HOME = System.getProperty("user.home");
	private String MAIN_DIR = USER_HOME + "/.openinvaders";
	
	public Portal() {
		ImageIcon i = new ImageIcon(MAIN_DIR + "/portal.png");
		img = i.getImage();
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
