package net.ubudog.openinvaders.map;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import net.ubudog.openinvaders.Game;

public class Map {
	
	private Scanner scanner; 
	
	private String map[] = new String[32];
	 
	private Image wall; 
	private Image space;
	private Image goo;
	private Image portal;
	
	static String MAP_LOCATION;
	
	public Map() {		
		
		if (getLevel() == 1) { 
			MAP_LOCATION = "res/map/level1.txt";
		} else if (getLevel() == 2) {
			MAP_LOCATION = "res/map/level2.txt"; 
		}
		
		ImageIcon img = new ImageIcon("res/drawable/barrier.png"); 
		wall = img.getImage(); 
		
		img = new ImageIcon("res/drawable/space.png");
		space = img.getImage();
						
		openFile(); 
		readFile();
		closeFile(); 
	}
	/**
	public void levelChecker() {
		while(true) {
			if (new Board().getLevel() == 1) { 
				MAP_LOCATION = MAIN_DIR + ""
			}
		}
	}
	*/

	public Image getWall() {
		return wall;
	}
	
	public Image getSpace() {
		return space;
	}
	
	// Goo is only used in Level 2, atm.
	public Image getGoo() {
		return goo;
	}
	
	public Image getPortal() {
		return portal;
	}
	
	public String getMap(int x, int y) {
		String index = map[y].substring(x, x + 1);
		return index; 
	}
	
	public void openFile() {
		try {
			scanner = new Scanner(new File(MAP_LOCATION));
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	public void readFile() {
		while (scanner.hasNext()) {
			for (int i = 0; i < 16; i++) {
				map[i] = scanner.next(); 
				
			}
		}
	}
	
	public int getLevel() {
		return Game.level;
	}
	
	public void closeFile() {
		scanner.close(); 
	}
}
