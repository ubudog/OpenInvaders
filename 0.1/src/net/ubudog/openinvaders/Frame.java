package net.ubudog.openinvaders;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JFrame;

import org.newdawn.easyogg.OggClip;

public class Frame {
	
	static File firstDir;
	
	static String USER_HOME = System.getProperty("user.home");
	static String MAIN_DIR = USER_HOME + "/.openinvaders";
	static String GAME_VERSION = "0.1";
	static String MUSIC_LOCATION = MAIN_DIR + "/song.ogg";
	static boolean firstRun;
	
	static String serverName = "ubudog.net"; 
	static Socket socket; 
	static InputStream inputStream; 
	static OutputStream outputStream;
	
	public boolean getFirstRun() {
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
	                BufferedInputStream is =
	                        new BufferedInputStream(zipFile.getInputStream(entry));
	                int currentByte;
	                byte data[] = new byte[BUFFER];

	                FileOutputStream fos = new FileOutputStream(destFile);
	                BufferedOutputStream dest =
	                        new BufferedOutputStream(fos, BUFFER);

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
	        String zipName = (String)iter.next();
	        doUnzip(
	            zipName,
	            destinationDirectory +
	                File.separatorChar +
	                zipName.substring(0,zipName.lastIndexOf(".zip"))
	        );
	    }
	}
	
	public static void main(String[] args) throws IOException, MalformedURLException {		
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
						System.out.println("Connecting to our main server to get needed files...");
						URL url = new URL("http://ubudog.net/openinvadersdownload.zip");
						url.openConnection();
						InputStream reader = url.openStream();

						FileOutputStream writer = new FileOutputStream(MAIN_DIR + "/openinvadersdownload.zip");
						byte[] buffer = new byte[153600];
						int totalBytesRead = 0;
						int bytesRead = 0;
						
						System.out.println("Connected!"); 
						System.out.println("Reading 150KB blocks at a time.  This might take a while depending on your connection.");
						System.out.println("You only have to do this once. (well, unless you delete $HOME/.clash...)");
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
