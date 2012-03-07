package net.ubudog.openinvaders;

import java.io.File;

import javax.swing.JFrame;

public class Frame {
	
	static File firstDir;
	
	static String USER_HOME = System.getProperty("user.home");
	static String MAIN_DIR = USER_HOME + "/.openinvaders";
	static String GAME_VERSION = "0.1";
	
	public static void main(String[] args) {		
		firstDir = new File(MAIN_DIR);
		
		if (firstDir.exists()) {
			System.out.println("Not first run.");
		} else {
			System.out.println("First run.");
			firstDir.mkdir();
		}
		
		System.out.println("New game starting."); 
		System.out.println("Game version: " + GAME_VERSION);
		System.out.println("Home directory detected as: " + USER_HOME);
		JFrame frame = new JFrame("OpenInvaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(515, 543);
		frame.add(new Board());
		frame.setVisible(true);
	}
}
