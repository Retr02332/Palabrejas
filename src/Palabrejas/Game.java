package Palabrejas;

import java.awt.EventQueue;

// TODO: Auto-generated Javadoc
/**
 * La clase Game, es el entrypoint del video juego.
 */
public class Game {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Gui GUI = new Gui();
			}
		});
	}
}