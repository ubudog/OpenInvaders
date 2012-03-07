package net.ubudog.openinvaders;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Map {
	
	private Scanner scanner; 
	
	private String map[] = new String[32];
	 
	private Image wall; 
	private Image space;
	
	static String USER_HOME = System.getProperty("user.home"); 
	static String MAIN_DIR = USER_HOME + "/.openinvaders"; 
	
	public Map() {		
		ImageIcon img = new ImageIcon(MAIN_DIR + "/wall.png"); 
		wall = img.getImage(); 
		
		img = new ImageIcon(MAIN_DIR + "/space.gif");
		space = img.getImage();
		
		openFile(); 
		readFile();
		closeFile(); 
	}
	
	public Image getWall() {
		return wall;
	}
	
	public Image getSpace() {
		return space;
	}
	
	public String getMap(int x, int y) {
		String index = map[y].substring(x, x + 1);
		return index; 
	}
	
	public void openFile() {
		try {
			scanner = new Scanner(new File(MAIN_DIR + "/map.txt"));
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
	
	public void closeFile() {
		scanner.close(); 
	}
}
