package net.ubudog.openinvaders.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet { 
	
	int x; 
	int y; 
	Image img;
	
	boolean visible;
	
	public Bullet(int startX, int startY) {
		x = startX;
		y = startY; 
		
		ImageIcon newBullet = new ImageIcon("res/drawable/bullet.png");
		img = newBullet.getImage();
		
		visible = true;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
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
		y = y - 5;
		if (y > 515) {
			visible = false;
		}
	}
}