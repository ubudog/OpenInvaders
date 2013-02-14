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

import javax.swing.ImageIcon;

public class EnemyBullet { 
	
	int x; 
	int y; 
	Image img;
	
	public boolean visible;
	
	public EnemyBullet(int startX, int startY) {
		x = startX;
		y = startY; 
		
		ImageIcon newBullet = new ImageIcon("res/drawable/enemybullet.png");
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
		y = y + 5;
		if (y > 515) {
			visible = false;
		}
	}
}