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
	
	private Timer time;
	
	public Board() {
		player = new Player(); 
		ship = new Ship(); 
		addKeyListener(new AL()); 
		setFocusable(true);
		
		time = new Timer(25, this);
		time.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paint(Graphics g) {
		
		/**
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.drawString("It works!", 300, 200);
		*/
		g.drawImage(player.getPlayer(), player.getX(), player.getY(), null);
		g.drawImage(ship.getShip(), ship.getX(), ship.getY(), null);
		
		ship.y = ship.getY() + 32;
		System.out.println("Ship's Y: " + ship.getY());
		
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
