package net.ubudog.openinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.ubudog.openinvaders.entity.Bullet;
import net.ubudog.openinvaders.entity.Player;
import net.ubudog.openinvaders.entity.Portal;
import net.ubudog.openinvaders.entity.Ship;
import net.ubudog.openinvaders.map.Map;

public class Game extends JPanel implements ActionListener, KeyListener {

	// I know my coding is sub-par
	// I am still learning :D

	private static final long serialVersionUID = 1L;
	static Font font = new Font("SanSerif", Font.BOLD, 25);
	static Font italics = new Font("SanSerif", Font.ITALIC, 25);

	private Player player;
	private Bullet bullet;
	private Ship ship;
	private Ship ship2;
	private Ship ship3;
	private Ship ship4;
	private Ship ship5;
	private Ship ship6;
	private Ship ship7;
	private Ship ship8;
	private Ship ship9;
	private Ship ship10;
	private Ship ship11;
	private Ship ship12;
	private Ship ship13;
	private Ship ship14;
	private Ship ship15;
	private Ship ship16;
	private Ship ship17;
	private Ship ship18;
	private Ship ship19;
	private Ship ship20;
	private Ship ship21;
	private Ship ship22;
	private Ship ship23;
	private Ship ship24;
	private Ship ship25;
	private Ship ship26;
	private Ship ship27;
	private Ship ship28;
	private Ship ship29;
	private Ship ship30;
	private Ship ship31;
	private Ship ship32;
	private Ship ship33;
	private Ship ship34;
	private Ship ship35;
	private Ship ship36;
	private Ship ship37;
	private Ship ship38;
	private Ship ship39;
	private Ship ship40;
	private Ship ship41;
	private Ship ship42;
	private Ship ship43;
	private Ship ship44;
	private Portal portal;
	private Portal portal2;
	private Portal portal3;
	private Map map;
	private Timer time;
	public static int level = 1;
	static boolean levelonenew = true;
	static boolean leveltwonew = true;
	static boolean playercanmoveup = false;
	static boolean playercanmovedown = false;
	static File firstDir;

	static String USER_HOME = System.getProperty("user.home");
	static String MAIN_DIR = USER_HOME + "/.openinvaders";
	static String GAME_NAME = "OpenInvaders - Work-in-progress";
	static String GAME_VERSION = "0.1";
	static String LEVEL1_MUSIC = MAIN_DIR + "/songlevel1.ogg";
	static String ICON_LOCATION = MAIN_DIR + "/player.png";
	// static String LEVEL2_MUSIC = MAIN_DIR + "/songlevel2.ogg";
	// static String LEVEL3_MUSIC = MAIN_DIR + "/songlevel3.ogg";
	static int startErrors = 0;
	static boolean firstRun;

	static String serverName = "ubudog.net";
	static Socket socket;
	static InputStream inputStream;
	static OutputStream outputStream;

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

	int ship9x;
	int ship9y;

	int ship10x;
	int ship10y;

	int ship11x;
	int ship11y;

	int ship12x;
	int ship12y;

	int ship13x;
	int ship13y;

	int ship14x;
	int ship14y;

	int ship15x;
	int ship15y;

	int ship16x;
	int ship16y;

	int ship17x;
	int ship17y;

	int ship18x;
	int ship18y;

	int ship19x;
	int ship19y;

	int ship20x;
	int ship20y;

	int ship21x;
	int ship21y;

	int ship22x;
	int ship22y;

	int ship23x;
	int ship23y;

	int ship24x;
	int ship24y;

	int ship25x;
	int ship25y;

	int ship26x;
	int ship26y;

	int ship27x;
	int ship27y;

	int ship28x;
	int ship28y;

	int ship29x;
	int ship29y;

	int ship30x;
	int ship30y;

	int ship31x;
	int ship31y;

	int ship32x;
	int ship32y;

	int ship33x;
	int ship33y;

	int ship34x;
	int ship34y;

	int ship35x;
	int ship35y;

	int ship36x;
	int ship36y;

	int ship37x;
	int ship37y;

	int ship38x;
	int ship38y;

	int ship39x;
	int ship39y;

	int ship40x;
	int ship40y;

	int ship41x;
	int ship41y;

	int ship42x;
	int ship42y;

	int ship43x;
	int ship43y;

	int ship44x;
	int ship44y;

	int portal2x;
	int portal2y;

	int portal3x;
	int portal3y;

	static boolean win = false;
	static boolean fail = false;

	public Game() {
		ship = new Ship();
		ship2 = new Ship();
		ship3 = new Ship();
		ship4 = new Ship();
		ship5 = new Ship();
		ship6 = new Ship();
		ship7 = new Ship();
		ship8 = new Ship();
		ship9 = new Ship();
		ship10 = new Ship();
		ship11 = new Ship();
		ship12 = new Ship();
		ship13 = new Ship();
		ship14 = new Ship();
		ship15 = new Ship();
		ship16 = new Ship();
		ship17 = new Ship();
		ship18 = new Ship();
		ship19 = new Ship();
		ship20 = new Ship();
		ship21 = new Ship();
		ship22 = new Ship();
		ship23 = new Ship();
		ship24 = new Ship();
		ship25 = new Ship();
		ship26 = new Ship();
		ship27 = new Ship();
		ship28 = new Ship();
		ship29 = new Ship();
		ship30 = new Ship();
		ship31 = new Ship();
		ship32 = new Ship();
		ship33 = new Ship();
		ship34 = new Ship();
		ship35 = new Ship();
		ship36 = new Ship();
		ship37 = new Ship();
		ship38 = new Ship();
		ship39 = new Ship();
		ship40 = new Ship();
		ship41 = new Ship();
		ship42 = new Ship();
		ship43 = new Ship();
		ship44 = new Ship();
		portal = new Portal();
		portal2 = new Portal();
		portal3 = new Portal();
		map = new Map();
		player = new Player();
		addKeyListener(this);
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

		// Block of ships
		ship8x = ship.getX() + 50;
		ship8y = ship.getY();

		ship9x = ship.getX() + 100;
		ship9y = ship.getY();

		ship10x = ship8x;
		ship10y = ship.getY() + 40;

		ship11x = ship9x;
		ship11y = ship.getY() + 40;

		// Block of ships
		ship12x = ship.getX() + 200;
		ship12y = ship.getY();

		ship13x = ship.getX() + 250;
		ship13y = ship.getY();

		ship14x = ship12x;
		ship14y = ship.getY() + 40;

		ship15x = ship13x;
		ship15y = ship.getY() + 40;

		// 'Nother block of ships
		ship16x = ship.getX() + 400;
		ship16y = ship.getY();

		ship17x = ship.getX() + 399 + 51;
		ship17y = ship.getY();

		ship18x = ship17x;
		ship18y = ship.getY() + 40;

		ship19x = ship.getX() + 400;
		ship19y = ship.getY() + 40;

		// ship17x = ship

		// Level 3
		// Block of ships

		ship20x = ship.getX() + 50;
		ship20y = ship.getY();

		ship21x = ship.getX() + 100;
		ship21y = ship.getY();

		ship22x = ship.getX() + 50;
		ship22y = ship.getY() + 40;

		ship23x = ship.getX() + 100;
		ship23y = ship.getY() + 40;

		// Block of ships
		ship24x = ship.getX() + 200;
		ship24y = ship.getY();

		ship25x = ship.getX() + 250;
		ship25y = ship.getY();

		ship26x = ship.getX() + 200;
		ship26y = ship.getY() + 40;

		ship27x = ship.getX() + 250;
		ship27y = ship.getY() + 40;

		// Block of ships
		ship28x = ship.getX() + 400;
		ship28y = ship.getY();

		ship29x = ship.getX() + 450;
		ship29y = ship.getY();

		ship30x = ship.getX() + 400;
		ship30y = ship.getY() + 40;

		ship31x = ship.getX() + 450;
		ship31y = ship.getY() + 40;

		// Some filler ships, this is the last level
		ship32x = ship.getX() + 350;
		ship32y = ship.getY();

		ship33x = ship.getX() + 300;
		ship33y = ship.getY();

		ship34x = ship.getX() + 350;
		ship34y = ship.getY() + 40;

		ship35x = ship.getX() + 300;
		ship35y = ship.getY() + 40;

		ship36x = ship.getX() + 150;
		ship36y = ship.getY();

		ship37x = ship.getX() + 150;
		ship37y = ship.getY() + 40;

		ship38x = ship.getX() + 10;
		ship38y = ship.getY();

		ship39x = ship.getX() + 10;
		ship39y = ship.getY() + 40;
		/**
		 * ship40x = ship.getX() + 500; ship40y = ship.getY();
		 * 
		 * ship41x = ship.getX() + 500; ship41y = ship.getY() + 40;
		 */

		ship42x = ship.getX() + 10;
		ship42y = ship.getY() + 80;

		ship43x = ship.getX() + 50;
		ship43y = ship.getY() + 80;

		ship44x = ship.getX() + 90;
		ship44y = ship.getY() + 80;

		portal2x = 172;
		portal2y = 8;

		portal3x = 108;
		portal3y = 104;

		time = new Timer(25, this);
		time.start();
	}

	public void actionPerformed(ActionEvent e) {
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

	public void checkCollisions() {
		// Level 1 rects
		Rectangle r1 = ship.getBounds();
		Rectangle r2 = ship2.getBounds();
		Rectangle r3 = ship3.getBounds();
		Rectangle r4 = ship4.getBounds();
		Rectangle r5 = ship5.getBounds();
		Rectangle r6 = ship6.getBounds();
		Rectangle r7 = ship7.getBounds();
		Rectangle r8 = portal.getBounds();
		// Level 2 rects
		Rectangle r9 = portal2.getBounds();
		ArrayList bullets = player.getBullets();
		for (int w = 0; w < bullets.size(); w++) {
			Bullet m = (Bullet) bullets.get(w);
			Rectangle m1 = m.getBounds();
			// Level 1
			if (r1.intersects(m1) && ship.getAlive() == true) {
				ship.alive = false;
			} else if (r2.intersects(m1) && ship2.getAlive() == true) {
				ship2.alive = false;
			} else if (r3.intersects(m1) && ship3.getAlive() == true) {
				ship3.alive = false;
			} else if (r4.intersects(m1) && ship4.getAlive() == true) {
				ship4.alive = false;
			} else if (r5.intersects(m1)) {
				ship5.alive = false;
			} else if (r6.intersects(m1)) {
				ship6.alive = false;
			} else if (r7.intersects(m1)) {
				ship7.alive = false;
			} else if (r8.intersects(m1)) {
				portal.alive = false;
				player.cangoportal = false;
			} else {
				// player.cangoportal = true;
			}
			// Level 2
			if (r9.intersects(m1)) {
				portal2.alive = false;
				player.cangoportal = false;
			} else {
				// player.cangoportal = true;
			}
		}

		Rectangle d = player.getBounds();
		if (d.intersects(r1) || d.intersects(r2)) {
			player.isAlive = false;
		}
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

	public int getShip2X() {
		return ship2x;
	}

	public int getShip2Y() {
		return ship2y;
	}

	public void paint(Graphics g) {
		if (getLevel() == 1) {
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

				g.drawImage(ship.getShip(), ship.getX(), ship.getY(), null);
				g.drawImage(ship2.getShip(), ship2x, ship2y, null);
				g.drawImage(ship3.getShip(), ship3x, ship3y, null);
				g.drawImage(ship4.getShip(), ship4x, ship4y, null);
				g.drawImage(ship5.getShip(), ship5x, ship5y, null);
				g.drawImage(ship6.getShip(), ship6x, ship6y, null);
				g.drawImage(ship7.getShip(), ship7x, ship7y, null);

				if (portal.getAlive() == true) {
					g.drawImage(portal.getPortal(), portal.getX(),
							portal.getY(), null);
				} else if (portal.getAlive() == false) {
					fail = true;
				}

				// Ship movement
				if (getShip2X() == 400) {
					// ship.alive = false;
				} else {
					ship2x++;
				}

				Random r = new Random();
				String[] failmsg = { "YOU FAIL.  :(", "Failness.",
						"Wow, fail.",
						"'Ouch', you say, as you look at the ruins of your ship." };

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

				if (getFail() == true && portal.getAlive() == false) {
					g.setFont(font);
					g.setColor(Color.GREEN);
					g.drawString("Don't hit the - ...", 100, 300);

					g.setColor(Color.RED);
					g.drawString("... portal.", 100, 320);
				}

				if (getFail() == true) {
					g.setFont(font);
					g.setColor(Color.GREEN);
					g.drawString("Failed!", 100, 200);
				}

				if (player.ammo == 17) {
					levelonenew = false;
				}

				if (ship.getAlive() == false) {
					playercanmoveup = true;
					playercanmovedown = true;

					win = true;
					level = 2;
					ship.alive = true;
				}

				if (player.getX() == portal.getX()
						&& player.getY() == portal.getY()) {
					level = 2;
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

			g.drawImage(ship8.getShip(), ship8x, ship8y, null);
			g.drawImage(ship9.getShip(), ship9x, ship9y, null);
			g.drawImage(ship10.getShip(), ship10x, ship10y, null);
			g.drawImage(ship11.getShip(), ship11x, ship11y, null);
			g.drawImage(ship12.getShip(), ship12x, ship12y, null);
			g.drawImage(ship13.getShip(), ship13x, ship13y, null);
			g.drawImage(ship14.getShip(), ship14x, ship14y, null);
			g.drawImage(ship15.getShip(), ship15x, ship15y, null);
			g.drawImage(ship16.getShip(), ship16x, ship16y, null);
			g.drawImage(ship17.getShip(), ship17x, ship17y, null);
			g.drawImage(ship18.getShip(), ship18x, ship18y, null);
			g.drawImage(ship19.getShip(), ship19x, ship19y, null);
			// g.drawImage(ship20.getShip(), ship20x, ship20y, null);

			if (player.getAlive() == true) {
				g.drawImage(player.getPlayer(), player.getX(), player.getY(),
						null);
			} else if (player.getAlive() == false) {
				g.drawImage(player.getDead(), player.getX(), player.getY(),
						null);
			}

			g.drawImage(portal2.getPortal(), portal2x, portal2y, null);

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

				if (player.getX() == portal2x && player.getY() == portal2y) {
					level = 3;
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

					g.drawImage(ship20.getShip(), ship20x, ship20y, null);
					g.drawImage(ship21.getShip(), ship21x, ship21y, null);
					g.drawImage(ship22.getShip(), ship22x, ship22y, null);
					g.drawImage(ship23.getShip(), ship23x, ship23y, null);

					g.drawImage(ship24.getShip(), ship24x, ship24y, null);
					g.drawImage(ship25.getShip(), ship25x, ship25y, null);
					g.drawImage(ship26.getShip(), ship26x, ship26y, null);
					g.drawImage(ship27.getShip(), ship27x, ship27y, null);

					g.drawImage(ship28.getShip(), ship28x, ship28y, null);
					g.drawImage(ship29.getShip(), ship29x, ship29y, null);
					g.drawImage(ship30.getShip(), ship30x, ship30y, null);
					g.drawImage(ship31.getShip(), ship31x, ship31y, null);

					g.drawImage(ship32.getShip(), ship32x, ship32y, null);
					g.drawImage(ship33.getShip(), ship33x, ship33y, null);
					g.drawImage(ship34.getShip(), ship34x, ship34y, null);
					g.drawImage(ship35.getShip(), ship35x, ship35y, null);
					g.drawImage(ship36.getShip(), ship36x, ship36y, null);
					g.drawImage(ship37.getShip(), ship37x, ship37y, null);
					g.drawImage(ship38.getShip(), ship38x, ship38y, null);
					g.drawImage(ship39.getShip(), ship39x, ship39y, null);
					// g.drawImage(ship40.getShip(), ship40x, ship40y,
					// null);
					// g.drawImage(ship41.getShip(), ship41x, ship41y,
					// null);
					g.drawImage(ship42.getShip(), ship42x, ship42y, null);
					g.drawImage(ship43.getShip(), ship43x, ship43y, null);
					g.drawImage(ship44.getShip(), ship44x, ship44y, null);

					g.drawImage(portal3.getPortal(), portal3x, portal3y, null);

					if (getFail() == true) {
						g.setFont(italics);
						g.setColor(Color.GREEN);
						g.drawString("You have failed level 3.  So close!", 10,
								200);
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

					if (player.getX() == portal3x && player.getY() == portal3y) {
						win = true;
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

	public static void doUnzip(String inputZip, String destinationDirectory)
			throws IOException {
		int BUFFER = 2048;
		List zipFiles = new ArrayList();
		File sourceZipFile = new File(inputZip);
		File unzipDestinationDirectory = new File(destinationDirectory);
		unzipDestinationDirectory.mkdir();

		ZipFile zipFile;
		zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);

		Enumeration zipFileEntries = zipFile.entries();

		while (zipFileEntries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

			String currentEntry = entry.getName();

			File destFile = new File(unzipDestinationDirectory, currentEntry);
			destFile = new File(unzipDestinationDirectory, destFile.getName());

			if (currentEntry.endsWith(".zip")) {
				zipFiles.add(destFile.getAbsolutePath());
			}

			File destinationParent = destFile.getParentFile();

			destinationParent.mkdirs();

			try {
				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(
							zipFile.getInputStream(entry));
					int currentByte;
					byte data[] = new byte[BUFFER];

					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos,
							BUFFER);

					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		zipFile.close();

		for (Iterator iter = zipFiles.iterator(); iter.hasNext();) {
			String zipName = (String) iter.next();
			doUnzip(zipName, destinationDirectory + File.separatorChar
					+ zipName.substring(0, zipName.lastIndexOf(".zip")));
		}
	}

	public static void main(String[] args) throws IOException,
			MalformedURLException {
		firstDir = new File(MAIN_DIR);

		if (firstDir.exists()) {
			System.out.println("Not first run.");
			firstRun = false;
		} else {
			System.out.println("First run.");
			firstRun = true;
			firstDir.mkdir();

			// Download sprites, music, etc.
			long startTime = System.currentTimeMillis();
			System.out
					.println("Connecting to our main server to get needed files...");
			URL url = new URL("http://ubudog.net/openinvadersdownload.zip");
			url.openConnection();
			InputStream reader = url.openStream();

			FileOutputStream writer = new FileOutputStream(MAIN_DIR
					+ "/openinvadersdownload.zip");
			byte[] buffer = new byte[153600];
			int totalBytesRead = 0;
			int bytesRead = 0;

			System.out.println("Connected!");
			System.out
					.println("Reading 150KB blocks at a time.  This might take a while depending on your connection.");
			System.out
					.println("You only have to do this once. (well, unless you delete $HOME/.openinvaders...)");
			System.out.println("");
			while ((bytesRead = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, bytesRead);
				buffer = new byte[153600];
				totalBytesRead += bytesRead;
			}

			long endTime = System.currentTimeMillis();

			doUnzip(MAIN_DIR + "/openinvadersdownload.zip", MAIN_DIR);

			System.out.println("Done with first run stuff woohoo");
			System.out.println("");
			writer.close();
			reader.close();

		}

		// No music for now
		if (getLevel() == 1) {
			/**
			 * try { FileInputStream fis = new FileInputStream(LEVEL1_MUSIC);
			 * OggClip clip = new OggClip(fis); clip.loop(); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
		} else if (getLevel() == 2) {
			/**
			 * try { FileInputStream fis = new FileInputStream(LEVEL2_MUSIC);
			 * OggClip clip = new OggClip(fis); clip.loop(); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
		} else if (getLevel() == 3) {
			/**
			 * try { FileInputStream fis = new FileInputStream(LEVEL3_MUSIC);
			 * OggClip clip = new OggClip(fis); clip.loop(); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
		}

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
		JFrame frame = new JFrame(GAME_NAME);
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
			// Cheat key to get to level 2
			// Mainly for testing.. :)
			if (level == 1) {
				level++;
			} else if (level == 2) {
				level++;
			} else if (level == 3) {
				System.out.println("At the last level, can't go forward.");
			}
		}

		if (key == KeyEvent.VK_B) {
			// Cheat to get to the previous level
			// Also for testing :)
			if (level == 1) {
				System.out.println("At the first level, can't go back.");
			} else if (level == 2) {
				level--;
			} else if (level == 3) {
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
}
