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

	private static final long serialVersionUID = 1L;
	static Font font = new Font("SanSerif", Font.BOLD, 25);
	static Font italics = new Font("SanSerif", Font.ITALIC, 25);

	private Player player;
	private Bullet bullet;
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

	static boolean win = false;
	static boolean fail = false;

	public Game() {
		map = new Map();
		player = new Player();
		addKeyListener(this);
		setFocusable(true);

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

			if (player.getAlive() == true) {
				g.drawImage(player.getPlayer(), player.getX(), player.getY(),
						null);
			} else if (player.getAlive() == false) {
				g.drawImage(player.getDead(), player.getX(), player.getY(),
						null);
			}


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
			// Will be gone soon
			long startTime = System.currentTimeMillis();
			System.out
					.println("Connecting to ubudog.net to get needed files...");
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
