package net.ubudog.openinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	static Font font = new Font("SanSerif", Font.BOLD, 25);
	
	private Player player; 
	private Ship ship;
	private Ship ship2; 
	private Ship ship3; 
	private Ship ship4;
	private Ship ship5; 
	private Ship ship6;
	private Ship ship7;
	private Ship ship8;
	private Map map;
	private Timer time;
	static int level = 1;
	

	int ship2x;
	int ship2y;
	
	int ship3x;
	int ship3y;
	
	int ship4x;
	int ship4y;
	
	int ship5x;
	int ship5y; 
	
	int ship6x;
	int ship6y;
	
	int ship7x; 
	int ship7y;
	
	int ship8x;
	int ship8y;
	
	static boolean win = false;
	static boolean fail = false;
	
	public Board() {
		ship = new Ship(); 
		ship2 = new Ship();
		ship3 = new Ship();
		ship4 = new Ship();
		ship5 = new Ship();
		ship6 = new Ship();
		ship7 = new Ship();
		ship8 = new Ship();
		map = new Map();
		player = new Player(); 
		addKeyListener(new AL()); 
		setFocusable(true);
		
		// Level 1 ships
		ship2x = ship.getX() + 50;
		ship2y = ship.getY();
		
		ship3x = ship.getX() + 100;
		ship3y = ship.getY();
		
		ship4x = ship.getX() + 150;
		ship4x = ship.getY();
		
		ship5x = ship.getX() + 200;
		ship5y = ship.getY();
		
		ship6x = ship.getX() + 250;
		ship6y = ship.getY();
		
		ship7x = ship.getX() + 300;
		ship7y = ship.getY();
		
		// Level 2 ships
		ship8x = ship.getX() + 50;
		ship8y = ship.getY();
		
		time = new Timer(25, this);
		time.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public boolean getWin() {
		return win; 
	}
	
	public boolean getFail() {
		return fail;
	}
	
	public ArrayList bullets() {
		return player.bullets;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getShip2X() {
		return ship2x;
	}
	
	public int getShip2Y() {
		return ship2y;
	}
	
	public void paint(Graphics g) {
		if (getLevel() == 1) {
			if (getWin() == true && getFail() == false) {
				g.setFont(font);
				g.setColor(Color.ORANGE);
				g.drawString("Level 1 Passed, WOO!", 100, 200);
			} else {
				for (int y = 0; y < 16; y++) {
					for (int x = 0; x < 16; x++) {				
						if(map.getMap(x, y).equals("w")) {
							g.drawImage(map.getWall(), x*32, y*32, null);
						}
						
						if(map.getMap(x, y).equals("s")) {
							g.drawImage(map.getSpace(), x*32, y*32, null);
						}
					}
					
					g.drawImage(player.getPlayer(), player.getX(), player.getY(), null);
					g.drawImage(ship.getShip(), ship.getX(), ship.getY(), null);
					g.drawImage(ship2.getShip(), ship2x, ship2y, null);
					g.drawImage(ship3.getShip(), ship3x, ship3y, null);
					g.drawImage(ship4.getShip(), ship4x, ship4y, null);	
					g.drawImage(ship5.getShip(), ship5x, ship5y, null);
					g.drawImage(ship6.getShip(), ship6x, ship6y, null);
					g.drawImage(ship7.getShip(), ship7x, ship7y, null);
					
					// Ship movement				
					if (getShip2X() == 400) {
						//ship.alive = false;
					} else {
						ship2x++;
					}
					
					// Simple collision detection for the ships and the player
					
					// Get a random fail message :P
					Random r = new Random();
					String[] failmsg = {"YOU FAIL.  :(", "Failness.", "Wow, fail.", "'Ouch', you say, as you look at the ruins of your ship."};
					
					
					if (ship.getX() == player.getX() && ship.getY() == player.getY()) {
						g.setFont(font);
						g.setColor(Color.RED);
						g.drawString(failmsg[r.nextInt(failmsg.length)], 50, 200);
						level = 1; 
					}
					
					if (ship2x == player.getX() && ship2y == player.getY()) {
						g.setFont(font);
						g.setColor(Color.RED);
						g.drawString(failmsg[r.nextInt(failmsg.length)], 50, 200);
						level = 1;
					}
					
					if (ship3x == player.getX() && ship3y == player.getY()) {
						g.setFont(font);
						g.setColor(Color.RED);
						g.drawString(failmsg[r.nextInt(failmsg.length)], 50, 200);
						level = 1;
					}
					
					if (ship4x == player.getX() && ship4y == player.getY()) {
						g.setFont(font);
						g.setColor(Color.RED);
						g.drawString(failmsg[r.nextInt(failmsg.length)], 50, 200);
						level = 1;
					}
					
					if (ship5x == player.getX() && ship5y == player.getY()) {
						g.setFont(font);
						g.setColor(Color.RED);
						g.drawString(failmsg[r.nextInt(failmsg.length)], 50, 200);
						level = 1; 
					}
					
					if (ship6x == player.getX() && ship6y == player.getY()) {
						g.setFont(font);
						g.setColor(Color.RED);
						g.drawString(failmsg[r.nextInt(failmsg.length)], 50, 200);
						level = 1;
					}
					
					if (player.ammo > 0) {
						g.setFont(font);
						g.setColor(Color.WHITE);
						g.drawString("Remaining Ammo: " + player.ammo, 0, 500);
					} else if (player.ammo == 0) {
						g.setFont(font);
						g.setColor(Color.WHITE);
						g.drawString("Reload! (R)", 0, 500);
					}
				}
			}
		} else if (getLevel() == 2) {
			g.setFont(font);
			g.setColor(Color.ORANGE);
			g.drawString("You've made it to Level 2!", 50, 250);
			g.drawString("We're still working on this...", 50, 300);
			
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {				
					if(map.getMap(x, y).equals("w")) {
						g.drawImage(map.getWall(), x*32, y*32, null);
					}
					
					if(map.getMap(x, y).equals("s")) {
						g.drawImage(map.getSpace(), x*32, y*32, null);
					}
				}
			}
			g.drawImage(ship8.getShip(), ship8x, ship8y, null);
			g.drawImage(player.getPlayer(), player.getX(), player.getY(), null);
			
			if (player.ammo > 0) {
				g.setFont(font);
				g.setColor(Color.WHITE);
				g.drawString("Remaining Ammo: " + player.ammo, 0, 500);
			} else if (player.ammo == 0) {
				g.setFont(font);
				g.setColor(Color.WHITE);
				g.drawString("Reload! (R)", 0, 500);
			}
		}
	}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}
}
