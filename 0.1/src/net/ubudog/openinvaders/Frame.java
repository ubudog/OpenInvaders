package net.ubudog.openinvaders;

import javax.swing.JFrame;

public class Frame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("OpenInvaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		//frame.add(new Board());
		frame.setVisible(true);
	}
}
