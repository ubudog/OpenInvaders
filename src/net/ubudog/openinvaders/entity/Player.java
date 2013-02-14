package net.ubudog.openinvaders.entity;

/*This file is part of OpenInvaders.

OpenInvaders is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

OpenInvaders is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with OpenInvaders.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import net.ubudog.openinvaders.Game;
import net.ubudog.openinvaders.map.Map;
import net.ubudog.openinvaders.sound.SoundManager;

public class Player {
	
	Image img;
	
	public static int ammo;
	public static int reloads; 
	public static int score;
	
	static boolean canMove = true;
	public static boolean isAlive = true;
	public static boolean cangoportal = false; 
	
	static int x; 
	static int y; 
		
	Map map;

	public static ArrayList bullets; 
	
	public Player() {
		x = 44; 
		y = 456;
		map = new Map();
		
		ammo = 20;
		reloads = 5; 
		score = 0;
		
		ImageIcon i = new ImageIcon("res/drawable/player.png");
		img = i.getImage();
		
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
			if (Game.firingMode.toString().equals("single")) { 
				new SoundManager("res/sounds/bullet.wav").start();
				Bullet b = new Bullet(getX() + 2, getY());
				bullets.add(b);
				ammo--;
			} else { 
				new SoundManager("res/sounds/bullet.wav").start();
				Bullet b = new Bullet(getX() + -5, getY());
				Bullet b2 = new Bullet(getX() + 12, getY()); 
				bullets.add(b);
				bullets.add(b2); 
				ammo = ammo - 2; 
			}
		}
	}
		
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (getAlive() == true) {
			if (getCanMove() == true) {
				if (key == KeyEvent.VK_F) { 
					// Change firing mode
					if (Game.firingMode.toString().equals("single")) { 
						Game.firingMode = "double"; 
					} else { 
						Game.firingMode = "single";
					}
				}
				
				if (key == KeyEvent.VK_W) {
					y = y - 32; 
				}
				if (key == KeyEvent.VK_A) {
					x = x - 32; 
				}
				
				if (key == KeyEvent.VK_S) {
					y = y + 32; 
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
			
		} else {
			
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
}
