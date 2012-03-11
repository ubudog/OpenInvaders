package net.ubudog.openinvaders;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	
	Image img;
	Image shootingimage;
	
	static int ammo;
	
	static boolean canMove = true;
	
	static int x; 
	static int y; 
	
	static String HOME_DIR = System.getProperty("user.home");
	static String MAIN_DIR = HOME_DIR + "/.openinvaders";
	
	Map map;
	
	static ArrayList<Bullet> bullets;
	
	public Player() {
		x = 44; 
		y = 456;
		map = new Map();
		
		ammo = 20;
		
		ImageIcon i = new ImageIcon(MAIN_DIR + "/player.png");
		img = i.getImage();
		
		i = new ImageIcon(MAIN_DIR + "/playershooting.gif");
		shootingimage = i.getImage();
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
	
	public boolean getCanMove() {
		return canMove;
	}
	
	public void fireBullet() {
		if (ammo > 0) {
			ammo--;
			Bullet z = new Bullet(getX() + 10, getY());
			bullets.add(z);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (getCanMove() == true) {
			
			if (key == KeyEvent.VK_W) {
				if (new Board().getPlayerCanMoveUp() == true) {
					y = y - 32;
				} else {
					System.out.println("Secure the area first!");
				}
			}
			if (key == KeyEvent.VK_A) {
				x = x - 32; 
			}
			
			if (key == KeyEvent.VK_S) {
				if (new Board().getPlayerCanMoveDown() == true) {
					y = y + 32; 
				} else {
					System.out.println("Secure the area first!");
				}
			}
			
			if (key == KeyEvent.VK_D) {
				
				x = x + 32;
			}
			
			if (key == KeyEvent.VK_UP) {
				//y = y - 32; 
			}
			
			if (key == KeyEvent.VK_LEFT) {
				x = x - 32; 
			}
			
			if (key == KeyEvent.VK_DOWN) {
				//y = y + 32; 
			}
			
			if (key == KeyEvent.VK_RIGHT) {
				x = x + 32;
			}
			
			if (key == KeyEvent.VK_R) {
				ammo = 20;
			}
			
			if (key == KeyEvent.VK_SPACE) { 
				//new Ship().alive = false;
				fireBullet();
			}
			
			if (key == KeyEvent.VK_H) { 
				// Tell the player the controls
				System.out.println("Here are the controls:"); 
				System.out.println("A/Left - Move Left");
				System.out.println("D/Right - Move Right");
				System.out.println("Space - Shoot");
				System.out.println("H - List these controls");
				
			}
			
		} else {
			System.out.println("I can't move.  I'm out of bounds!");
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
