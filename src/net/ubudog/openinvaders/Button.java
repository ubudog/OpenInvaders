package net.ubudog.openinvaders;

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

public class Button {
	int x, y;
	
	Image img; 
		
	public Button(String type) {
		ImageIcon newGameStandard = new ImageIcon("res/drawable/buttons/newgamebtn.png"); 
		ImageIcon newGameHighlighted = new ImageIcon("res/drawable/buttons/newgamebtnhighlighted.png");
		ImageIcon newGameClicked = new ImageIcon("res/drawable/buttons/newgamebuttonclicked.png"); 
		
		if (type.equalsIgnoreCase("standard")) { 
			img = newGameStandard.getImage(); 
		}
		
		if (type.equalsIgnoreCase("highlighted")) { 
			img = newGameHighlighted.getImage();
		}
		
		if (type.equalsIgnoreCase("clicked")) { 
			img = newGameClicked.getImage(); 
		}
		
	}
	
	/**
	public Rectangle getBounds() { 
		return new Rectangle(x, y, 32, 32); 
	}
	*/ 
		
	public Image getButton() {
		return img; 
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() { 
		return y; 
	}
}