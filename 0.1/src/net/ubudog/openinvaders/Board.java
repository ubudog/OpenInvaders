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
	private Map map;
	
	private Timer time;
	
	public Board() {
		ship = new Ship(); 
		map = new Map();
		player = new Player(); 
		addKeyListener(new AL()); 
		setFocusable(true);
		
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
