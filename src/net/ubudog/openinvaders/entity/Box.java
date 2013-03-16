package net.ubudog.openinvaders.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

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

public class Box {
	int x, y;
	
	Image img; 
	
	public boolean visible; 
	
	public Box() {
		ImageIcon box = new ImageIcon("res/drawable/box.png"); 
		img = box.getImage(); 
		
		visible = true; 
	}
	
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 32); 
	}
	
	public boolean getVisible() { 
		return visible;
	}
	
	public Image getBox() { 
		return img; 
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() { 
		return y; 
	}
}