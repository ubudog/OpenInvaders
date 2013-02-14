package net.ubudog.openinvaders;

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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.ubudog.openinvaders.entity.Bullet;
import net.ubudog.openinvaders.entity.Explosion;
import net.ubudog.openinvaders.entity.Player;
import net.ubudog.openinvaders.map.Map;

public class Game extends JPanel implements ActionListener, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	static Font font = new Font("SanSerif", Font.BOLD, 25);
	static Font italics = new Font("SanSerif", Font.ITALIC, 25);

	private Player player;
	private Bullet bullet;
	private Map map;
	private Timer time;
	public static int level = 0;
	static boolean levelonenew = true;
	static boolean leveltwonew = true;
	static File firstDir;

	static String USER_HOME = System.getProperty("user.home");
	static String MAIN_DIR = USER_HOME + "/.openinvaders";
	static String GAME_NAME = "OpenInvaders - Alpha 0.1";
	static String GAME_VERSION = "0.1";
	static String LEVEL1_MUSIC = MAIN_DIR + "/songlevel1.ogg";
	static String ICON_LOCATION = MAIN_DIR + "/player.png";
	
	static String version; 
	
	Explosion explosion; 
	
	static JFrame frame;
	
	static int startErrors = 0;
	static boolean firstRun;

	static boolean win = false;
	static boolean fail = false;
	
	public static String firingMode;
	
	Font menuFont; 
	Font infoFont; 
	Font gameFont; 
	
	ArrayList enemies;
	
	public Game() {
		version = "Alpha 0.1"; 
		
		explosion = new Explosion(0, 0); 
		
		addKeyListener(this);
		addMouseListener(this); 
		
		setFocusable(true);

		menuFont = new Font("SansSerif", Font.BOLD, 25); 
		infoFont = new Font("SansSerif", Font.ITALIC, 10); 
		gameFont = new Font("SansSerif", Font.HANGING_BASELINE, 15);
		
		firingMode = "single"; 
		
		enemies = new ArrayList(); 
		
		if (level == 0) {
		} else { 
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (level == 0) { 
		} else { 
			checkCollisions();
			ArrayList bullets = player.getBullets();
			for (int w = 0; w < bullets.size(); w++) {
				Bullet m = (Bullet) bullets.get(w);
				if (m.getVisible() == true) {
					m.move();
				} else {
					bullets.remove(w);
				}
			}
			repaint();
		}
	}

	public void checkCollisions() {
		ArrayList bullets = player.getBullets();
		for (int w = 0; w < bullets.size(); w++) {
			Bullet m = (Bullet) bullets.get(w);
			Rectangle m1 = m.getBounds();			
		}
		
		Rectangle d = player.getBounds();
	}

	public ArrayList bullets() {
		return player.bullets;
	}

	public static int getLevel() {
		return level;
	}

	public void paint(Graphics g) {
		if (getLevel() == 0) { 
			// Menu level
			frame.setBackground(Color.BLACK);
			
			g.draw3DRect(150, 150, 200, 50, false); 
			g.draw3DRect(150, 225, 200, 50, false);
			g.draw3DRect(150, 300, 200, 50, false);
			g.draw3DRect(150, 375, 200, 50, false);
			g.draw3DRect(150, 450, 200, 50, false); 
			
			g.setFont(menuFont);
			g.setColor(Color.WHITE); 
			g.drawString("OpenInvaders", 150, 75); 
			g.drawString("New Game", 175, 180); 
			g.drawString("Load Game", 175, 255);
			g.drawString("N/A", 175, 330);
			g.drawString("N/A", 175, 405);
			g.drawString("Exit", 175, 480);
			
			g.setFont(infoFont); 
			g.drawString("Version: " + version.toString(),  425, 535); 
		}
		if (getLevel() == 500) { 
			// Load games level
			g.setFont(menuFont); 
			g.setColor(Color.WHITE); 
			g.drawString("Load Games", 150, 75); 
			
			g.drawString("Version: " + version.toString(), 425, 535); 
		}
		if (getLevel() == 1) {
			g.setFont(gameFont); 
						
			// Level 1
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					if (map.getMap(x, y).equals("w")) {
						g.drawImage(map.getWall(), x * 32, y * 32, null);
					}

					if (map.getMap(x, y).equals("s")) {
						g.drawImage(map.getSpace(), x * 32, y * 32, null);
					}
				}

				if (player.getAlive() == true) {
					g.drawImage(player.getPlayer(), player.getX(),
							player.getY(), null);
				} else if (player.getAlive() == false) {
					ArrayList explosions = explosion.getExplosions();
					for (int w = 0; w < explosions.size(); w++) {
						Explosion e = (Explosion) explosions.get(w);
						g.drawImage(e.getExplosion(), player.getX(), player.getY(), null);
					}
				}
				
				Random r = new Random();
				String[] failmsg = { "You have lost!" }; 

				if (player.ammo > 0) {
					g.setFont(gameFont);
					g.setColor(Color.WHITE);
					g.drawString("Remaining Ammo: " + player.ammo, 0, 500);
				} else if (player.ammo == 0) {
					g.setFont(gameFont);
					g.setColor(Color.WHITE);
					g.drawString("Reload! (R)", 0, 500);
				}
				
				g.drawString("Score: " + player.score, 0, 480);
				g.drawString("Firing mode: " + firingMode.toString(), 0, 520); 

				if (player.reloads > 0) {
					g.setFont(gameFont);
					g.setColor(Color.WHITE);
					g.drawString("Reloads: " + player.reloads, 350, 500);
				} else if (player.reloads == 0) {
					g.setFont(gameFont);
					g.setColor(Color.WHITE);
					g.drawString("Out!", 450, 500);
				}

				if (fail == true) {
					g.setFont(font);
					g.setColor(Color.GREEN);
					g.drawString("Failed!", 100, 200);
				}

				if (player.ammo == 17) {
					levelonenew = false;
				}

				ArrayList bullets = player.getBullets();
				for (int w = 0; w < bullets.size(); w++) {
					Bullet m = (Bullet) bullets.get(w);
					g.drawImage(m.getBullet(), m.getX(), m.getY(), null);
				}
			}
		}

		if (getLevel() == 2) {
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					if (map.getMap(x, y).equals("w")) {
						g.drawImage(map.getWall(), x * 32, y * 32, null);
					}

					if (map.getMap(x, y).equals("s")) {
						g.drawImage(map.getSpace(), x * 32, y * 32, null);
					}
				}
			}
			
			/**
			if (player.getAlive() == true) {
				g.drawImage(player.getPlayer(), player.getX(), player.getY(),
						null);
			} else if (player.getAlive() == false) {
				g.drawImage(player.getDead(), player.getX(), player.getY(),
						null);
			}
			*/
			
			g.setFont(font);
			g.setColor(Color.ORANGE);

			if (leveltwonew == true) {
				g.drawString("You've made it to Level 2!", 50, 250);
				g.drawString("We're still working on this...", 50, 300);
			}

			if (win == true) {
				g.setFont(font);
				g.setColor(Color.GREEN);
				g.drawString("You have won!  :)", 0, 200);
			}

				if (fail == true) {
					g.setFont(gameFont);
					g.setColor(Color.GREEN);
					g.drawString("You have failed!  :(", 0, 200);
				}
				if (player.ammo > 0) {
					g.setFont(gameFont);
					g.setColor(Color.WHITE);
					g.drawString("Remaining Ammo: " + player.ammo, 0, 500);
				} else if (player.ammo == 0) {
					g.setFont(gameFont);
					g.setColor(Color.WHITE);
					g.drawString("Reload! (R)", 0, 500);
				}

				if (player.reloads > 0) {
					g.setFont(italics);
					g.setColor(Color.WHITE);
					g.drawString("Reloads: " + player.reloads, 350, 500);
				} else if (player.reloads == 0) {
					g.setFont(italics);
					g.setColor(Color.WHITE);
					g.drawString("Out!", 450, 500);
				}

				if (player.ammo == 17) {
					leveltwonew = false;
				}

				ArrayList bullets = player.getBullets();
				for (int w = 0; w < bullets.size(); w++) {
					Bullet m = (Bullet) bullets.get(w);
					g.drawImage(m.getBullet(), m.getX(), m.getY(), null);
				}
			}
	}
	
	public static boolean getFirstRun() {
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

		/**
		if (getLevel() == 1) {
			 * try { FileInputStream fis = new FileInputStream(LEVEL1_MUSIC);
			 * OggClip clip = new OggClip(fis); clip.loop(); } catch
			 * (IOException e) { e.printStackTrace(); }
		} else if (getLevel() == 2) {
			 * try { FileInputStream fis = new FileInputStream(LEVEL2_MUSIC);
			 * OggClip clip = new OggClip(fis); clip.loop(); } catch
			 * (IOException e) { e.printStackTrace(); }
		} else if (getLevel() == 3) {
			 * try { FileInputStream fis = new FileInputStream(LEVEL3_MUSIC);
			 * OggClip clip = new OggClip(fis); clip.loop(); } catch
			 * (IOException e) { e.printStackTrace(); }
		}
		*/

		System.out.println("New game starting.");
		System.out.println("Game version: " + GAME_VERSION);
		System.out.println("Home directory detected as: " + USER_HOME);

		if (getFirstRun() == true) {
			System.out.println("Controls:");
			System.out.println("A/Left - Move Left");
			System.out.println("D/Right - Move Right");
			System.out.println("Space - Shoot");
			System.out.println("H - List these controls");
		}

		System.out.println("Start errors: " + startErrors);
		Game game = new Game();
		frame = new JFrame(GAME_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(515, 543);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setIconImage(new ImageIcon(ICON_LOCATION).getImage());
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_ESCAPE && level != 0) { 
			level = 0; 
		} else if (key == KeyEvent.VK_ESCAPE && level == 0){ 
			System.out.println("Exiting!"); 
			System.exit(0); 
		}
		
		if (key == KeyEvent.VK_K) { 
			// Kill the player
			player.isAlive = false; 
		}

		if (key == KeyEvent.VK_J) {
			// Go to next level
			if (level == 3) { 
			} else { 
				level++; 
			}
		}

		if (key == KeyEvent.VK_B) {
			// Go to previous level
			if (level == 1) { 
			} else { 
				level--; 
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		player.keyTyped(e);
	}

	public void mouseClicked(MouseEvent arg0) {
		if (level == 0) { 			
			if (arg0.getX() >= 150 && arg0.getX() < 350 &&  arg0.getY() >= 150 && arg0.getY() < 200) {
				System.out.println("Starting a new game..."); 
				level = 1; 
				map = new Map();
				player = new Player();
				time = new Timer(25, this);
				time.start();
			}
			
			if (arg0.getX() >= 150 && arg0.getX() < 350 && arg0.getY() >= 225 && arg0.getY() < 275) { 
				System.out.println("Load games..."); 
				level = 500; 
			}
			
			if (arg0.getX() >= 150 && arg0.getX() < 350 && arg0.getY() >= 300 && arg0.getY() < 350) { 
				System.out.println("N/A"); 
			}
			
			if (arg0.getX() >= 150 && arg0.getX() < 350 && arg0.getY() >= 375 && arg0.getY() < 425) {
				System.out.println("N/A"); 
			}
			
			if (arg0.getX() >= 150 && arg0.getX() < 350 && arg0.getY() >= 450 && arg0.getY() < 500) { 
				System.out.println("Exiting!"); 
				System.exit(0); 
			}
			
		} else { 
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}
}
