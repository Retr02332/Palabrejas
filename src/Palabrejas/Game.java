package Palabrejas;

import java.awt.EventQueue;

public class Game {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Gui GUI = new Gui();
			}
		});
	}
}