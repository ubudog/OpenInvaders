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
import java.util.ArrayList;

import javax.swing.ImageIcon;

import net.ubudog.openinvaders.map.Map;
import net.ubudog.openinvaders.sound.SoundManager;

public class Enemy {
	
	Image img;
	Image deathimg;
	
	public static int ammo = 1000;
	public static int reloads; 
	public static int score;
	
	static boolean canMove = true;
	public static boolean isAlive = true;
	public static boolean cangoportal = false; 
	
	static int x; 
	static int y; 
		
	Map map;

	public static ArrayList enemyBullets; 
	
	public Enemy() {
		x = 44; 
		y = 100;
		map = new Map();
		
		ammo = 20;
		reloads = 5; 
		score = 0;
		
		ImageIcon i = new ImageIcon("res/drawable/hostile.png");
		img = i.getImage();
		
		i = new ImageIcon("res/drawable/explosion.gif");
		deathimg = i.getImage();
		
		enemyBullets = new ArrayList();
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
	
	public Image getEnemy() {
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
		return enemyBullets;
	}
	
	public void fireBullet() {
		if (ammo > 0) {
			ammo--; 
			new SoundManager("res/sounds/bullet.wav").start();
			Bullet b = new Bullet(getX() + 2, getY());
			enemyBullets.add(b);
		}
	}	
}
