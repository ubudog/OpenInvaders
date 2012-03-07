package net.ubudog.openinvaders;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
	
	Image img;
	
	static int x; 
	static int y; 
	
	static String HOME_DIR = System.getProperty("user.home");
	static String MAIN_DIR = HOME_DIR + "/.openinvaders";
	
	public Player() {
		x = 44; 
		y = 456;
		
		ImageIcon i = new ImageIcon(MAIN_DIR + "/player.gif");
		img = i.getImage();
	}
	
	public void move(int dx, int dy) {
		x = dx; 
		y = dy;
	}
	
	public Image getPlayer() {
		return img; 
	}
	
	public int getX() {
		return x; 
	}
	
	public int getY() {
		return y; 
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) {
			//y = y - 32;
		}
		
		if (key == KeyEvent.VK_A) { 
			x = x - 32; 
		}
		
		if (key == KeyEvent.VK_S) {
			//y = y + 32;
		}
		
		if (key == KeyEvent.VK_D) {
			x = x + 32;
		}
		
		if (key == KeyEvent.VK_UP) {
			y = y - 32; 
		}
		
		if (key == KeyEvent.VK_LEFT) {
			x = x - 32; 
		}
		
		if (key == KeyEvent.VK_DOWN) {
			y = y + 32; 
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			x = x + 32;
		}
		
		if (key == KeyEvent.VK_ESCAPE) { 
			System.out.println("Exiting.");
			System.exit(0);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	}
	
}
