package net.ubudog.openinvaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;

import org.newdawn.easyogg.OggClip;

public class Frame {
	
	static File firstDir;
	
	static String USER_HOME = System.getProperty("user.home");
	static String MAIN_DIR = USER_HOME + "/.openinvaders";
	static String GAME_VERSION = "0.1";
	static String MUSIC_LOCATION = MAIN_DIR + "/song.ogg";
	static boolean firstRun;
	
	public boolean getFirstRun() {
		return firstRun; 
	}
	
	public static void main(String[] args) {		
		firstDir = new File(MAIN_DIR);
		
		if (firstDir.exists()) {
			System.out.println("Not first run.");
			firstRun = false;
		} else {
			System.out.println("First run.");
			firstRun = true;
			firstDir.mkdir();
		}
		
		// Start the music
		try {
			FileInputStream fis = new FileInputStream(MUSIC_LOCATION);
			OggClip clip = new OggClip(fis);
			clip.loop();
			} catch (IOException e) {
			e.printStackTrace();
			}
		
		System.out.println("New game starting."); 
		System.out.println("Game version: " + GAME_VERSION);
		System.out.println("Home directory detected as: " + USER_HOME);
		JFrame frame = new JFrame("OpenInvaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(515, 543);
		frame.setLocationRelativeTo(null);
		frame.add(new Board());
		frame.setVisible(true);
	}
}
