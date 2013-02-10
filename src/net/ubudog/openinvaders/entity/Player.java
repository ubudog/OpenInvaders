package net.ubudog.openinvaders.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import net.ubudog.openinvaders.map.Map;
import net.ubudog.openinvaders.sound.SoundManager;

public class Player {
	
	Image img;
	Image deathimg;
	
	public static int ammo;
	public static int reloads; 
	public static int score;
	
	static boolean canMove = true;
	public static boolean isAlive = true;
	public static boolean cangoportal = false; 
	
	static int x; 
	static int y; 
	
	static String HOME_DIR = System.getProperty("user.home");
	static String MAIN_DIR = HOME_DIR + "/.openinvaders";
	
	Map map;

	public static ArrayList bullets; 
	
	public Player() {
		x = 44; 
		y = 456;
		map = new Map();
		
		ammo = 20;
		reloads = 5; 
		score = 0;
		
		ImageIcon i = new ImageIcon(MAIN_DIR + "/player.png");
		img = i.getImage();
		
		i = new ImageIcon(MAIN_DIR + "/explosion.gif");
		deathimg = i.getImage();
		
		bullets = new ArrayList();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void move(int dx, int dy) {
		x = dx; 
		y = dy;
	}
	
	public boolean getAlive() {
		return isAlive;
	}
	
	public Image getPlayer() {
		return img; 
	}
	
	public Image getDead() {
		return deathimg;
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
	
	public static ArrayList getBullets() {
		return bullets;
	}
	
	public void fireBullet() {
		if (ammo > 0) {
			ammo--; 
			new SoundManager("res/sounds/bullet.wav").start();
			Bullet b = new Bullet(getX() + 2, getY());
			bullets.add(b);
		}
	}
	
	public boolean getCanGoPortal() { 
		return cangoportal;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (getAlive() == true) {
			if (getCanMove() == true) {
				if (key == KeyEvent.VK_W) {
					if (getCanGoPortal() == true) {
						y = y - 32; 
					}
				}
				if (key == KeyEvent.VK_A) {
					x = x - 32; 
				}
				
				if (key == KeyEvent.VK_S) {
					if (getCanGoPortal() == true) { 
						y = y + 32; 
					}
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
				
				if (key == KeyEvent.VK_R) {
					if (reloads > 0 && ammo == 0) { 
						ammo = 20;
						reloads--; 
					}
					
					if (reloads == 0 && ammo == 0) {
						isAlive = false;
					}
				}
				
				if (key == KeyEvent.VK_SPACE) { 
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
			
		} else if (getAlive() == false) {
			
		}
		
		if (key == KeyEvent.VK_ESCAPE) { 
			System.out.println("Exiting.");
			System.exit(0);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
}
