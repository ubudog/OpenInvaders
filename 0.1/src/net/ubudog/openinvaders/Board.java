package net.ubudog.openinvaders;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private Map map;
	private Timer time;
	

	int ship2x;
	int ship2y;
	
	int ship3x;
	int ship3y;
	
	int ship4x;
	int ship4y;
	
	
	public Board() {
		ship = new Ship(); 
		ship2 = new Ship();
		ship3 = new Ship();
		ship4 = new Ship();
		map = new Map();
		player = new Player(); 
		addKeyListener(new AL()); 
		setFocusable(true);
		
		ship2x = ship.getX() + 50;
		ship2y = ship.getY();
		
		ship3x = ship.getX() + 100;
		ship3y = ship.getY();
		
		ship4x = ship.getX() + 150;
		ship4x = ship.getY();
		
		time = new Timer(25, this);
		time.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paint(Graphics g) {
		
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {				
				if(map.getMap(x, y).equals("w")) {
					g.drawImage(map.getWall(), x*32, y*32, null);
				}
			}
			
			g.drawImage(player.getPlayer(), player.getX(), player.getY(), null);
			g.drawImage(ship.getShip(), ship.getX(), ship.getY(), null);
			g.drawImage(ship2.getShip(), ship2x, ship2y, null);
			g.drawImage(ship3.getShip(), ship3x, ship3y, null);
			g.drawImage(ship4.getShip(), ship4x, ship4y, null);			
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
