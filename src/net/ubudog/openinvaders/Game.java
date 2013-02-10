package net.ubudog.openinvaders;

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
	static boolean playercanmoveup = false;
	static boolean playercanmovedown = false;
	static File firstDir;

	static String USER_HOME = System.getProperty("user.home");
	static String MAIN_DIR = USER_HOME + "/.openinvaders";
	static String GAME_NAME = "OpenInvaders - Alpha 0.1";
	static String GAME_VERSION = "0.1";
	static String LEVEL1_MUSIC = MAIN_DIR + "/songlevel1.ogg";
	static String ICON_LOCATION = MAIN_DIR + "/player.png";
	
	static JFrame frame;
	
	// static String LEVEL2_MUSIC = MAIN_DIR + "/songlevel2.ogg";
	// static String LEVEL3_MUSIC = MAIN_DIR + "/songlevel3.ogg";
	static int startErrors = 0;
	static boolean firstRun;

	static boolean win = false;
	static boolean fail = false;
	
	Font menuFont; 
	Font infoFont; 

	public Game() {
	//	map = new Map();
	//  player = new Player();
		addKeyListener(this);
		addMouseListener(this); 
		
		setFocusable(true);

		menuFont = new Font("SansSerif", Font.BOLD, 25); 
		infoFont = new Font("SansSerif", Font.ITALIC, 10); 
		
		time = new Timer(25, this);
		time.start();
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

	public boolean getWin() {
		return win;
	}

	public boolean getFail() {
		return fail;
	}

	public boolean getLevelOneNew() {
		return levelonenew;
	}

	public boolean getLevelTwoNew() {
		return leveltwonew;
	}

	public boolean getPlayerCanMoveUp() {
		return playercanmoveup;
	}

	public boolean getPlayerCanMoveDown() {
		return playercanmovedown;
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
			g.drawString("Version: Alpha 0.1", 425, 535); 
		}
		if (getLevel() == 1) {
			// Level 1
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					if (map.getMap(x, y).equals("w")) {
						g.drawImage(map.getWall(), x * 32, y * 32, null);
					}

					if (map.getMap(x, y).equals("s")) {
						g.drawImage(map.getSpace(), x * 32, y * 32, null);
					}

					if (map.getMap(x, y).equals("g")) {
						g.drawImage(map.getGoo(), x * 32, y * 32, null);
					}

					if (map.getMap(x, y).equals("p")) {
						g.drawImage(map.getPortal(), x * 32, y * 32, null);
					}
				}

				if (getLevelOneNew() == true) {
					g.drawString("Defend the area!", 30, 200);
					g.drawString("Then get to the portal!", 30, 220);
				}

				if (player.getAlive() == true) {
					g.drawImage(player.getPlayer(), player.getX(),
							player.getY(), null);
				} else if (player.getAlive() == false) {
					g.drawImage(player.getDead(), player.getX(), player.getY(),
							null);
				}

				Random r = new Random();
				String[] failmsg = { "You have lost!" }; 

				if (player.ammo > 0) {
					g.setFont(font);
					g.setColor(Color.WHITE);
					g.drawString("Remaining Ammo: " + player.ammo, 0, 500);
				} else if (player.ammo == 0) {
					g.setFont(font);
					g.setColor(Color.WHITE);
					g.drawString("Reload! (R)", 0, 500);
				}

				g.drawString("Score: " + player.score, 0, 480);

				if (player.reloads > 0) {
					g.setFont(italics);
					g.setColor(Color.WHITE);
					g.drawString("Reloads: " + player.reloads, 350, 500);
				} else if (player.reloads == 0) {
					g.setFont(italics);
					g.setColor(Color.WHITE);
					g.drawString("Out!", 450, 500);
				}

				if (getFail() == true) {
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

					if (map.getMap(x, y).equals("g")) {
						g.drawImage(map.getGoo(), x * 32, y * 32, null);
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

			if (getLevelTwoNew() == true) {
				g.drawString("You've made it to Level 2!", 50, 250);
				g.drawString("We're still working on this...", 50, 300);
			}

			if (getWin() == true) {
				g.setFont(font);
				g.setColor(Color.GREEN);
				g.drawString("You have won!  :)", 0, 200);
			}

				if (getFail() == true) {
					g.setFont(font);
					g.setColor(Color.GREEN);
					g.drawString("You have failed!  :(", 0, 200);
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

			if (getLevel() == 3) {
				for (int y = 0; y < 16; y++) {
					for (int x = 0; x < 16; x++) {
						if (map.getMap(x, y).equals("w")) {
							g.drawImage(map.getWall(), x * 32, y * 32, null);
						}

						if (map.getMap(x, y).equals("s")) {
							g.drawImage(map.getSpace(), x * 32, y * 32, null);
						}

						if (map.getMap(x, y).equals("g")) {
							g.drawImage(map.getGoo(), x * 32, y * 32, null);
						}
					}

					if (player.getAlive() == true) {
						g.drawImage(player.getPlayer(), player.getX(),
								player.getY(), null);
					} else if (player.getAlive() == false) {
						g.drawImage(player.getDead(), player.getX(),
								player.getY(), null);
					}

					if (getFail() == true) {
						g.setFont(italics);
						g.setColor(Color.GREEN);
						g.drawString("You have lost!", 10, 200);
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

					if (player.reloads > 0) {
						g.setFont(italics);
						g.setColor(Color.WHITE);
						g.drawString("Reloads: " + player.reloads, 350, 500);
					} else if (player.reloads == 0) {
						g.setFont(italics);
						g.setColor(Color.WHITE);
						g.drawString("Out!", 450, 500);
					}

					ArrayList bullets = player.getBullets();
					for (int w = 0; w < bullets.size(); w++) {
						Bullet m = (Bullet) bullets.get(w);
						g.drawImage(m.getBullet(), m.getX(), m.getY(), null);
					}
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
			// Tell the player the controls
			System.out.println("Here are the controls:");
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
			}
			
			if (arg0.getX() >= 150 && arg0.getX() < 350 && arg0.getY() >= 225 && arg0.getY() < 275) { 
				System.out.println("Load games..."); 
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
