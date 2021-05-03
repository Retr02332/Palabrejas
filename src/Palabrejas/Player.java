package Palabrejas;

import java.io.Serializable;

/**
 * La clase Player, tiene los atributos y metodos necesarios para interactuar con el jugador durante la partida.
 */
public class Player implements Serializable {
	
	/** El usuario completo la serie. */
	private boolean accomplishedSerie = false;
	
	/** El usuario ha perdido la partida. */
	private boolean gameOver = false;
	
	/** El nombre de usuario del jugador. */
	private String userName = "";
	
	/** El numero de palabras adivinadas en la partida actual. */
	private int currentRememberWords = 0;
	
	/** EL numero de palabras adivinadas en total*/
	private int allRememberWords = 0;
	
	/** El numero de palabras adivinadas por serie. */
	private int guessedWords  = 0;
	
	/** Las palabras por serie. */
	private int wordsForSerie = 4;
	
	/** El nivel del jugador. */
	private int level = 1;
	
	/** La serie en la que se encuentra el jugador. */
	private int serie = 1;
	
	/**
	 * Obtener las estadisticas del usuario en la partida actual.
	 *
	 * @return Las estadisticas del jugador en la partida actual.
	 */
	public String getStadistics() {
		return "<html><body><br>Nombre del jugador: "+userName+"<br>Palabras recordadas: "+
				currentRememberWords+"<br>Nivel: "+level+"<br><br></body></html>";
	}
	
	/**
	 * Obtener el nombre de usuario del jugador.
	 *
	 * @return El nombre de usuario.
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Obtener el numero de palabras adivinadas por serie.
	 *
	 * @return El numero de palabras adivinadas por serie.
	 */
	public int getGuessedWords() {
		return guessedWords;
	}
	
	/**
	 * Obtener el numero de palabras adivinadas en total.
	 *
	 * @return El numero de palabras adivinadas en total.
	 */
	public int getAllRememberWords() {
		return allRememberWords;
	}
	
	/**
	 * Obtener el numero de palabras adivinadas en la partida actual.
	 *
	 * @return El numero de palabras adivinadas en la partida actual.
	 */
	public int getCurrentRememberWords() {
		return currentRememberWords;
	}
	
	/**
	 * Obtener el nivel del jugador.
	 *
	 * @return El nivel del jugador.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Obtener la serie en la que se encuentra el jugador.
	 *
	 * @return La serie.
	 */
	public int getSerie() {
		return serie;
	}
	
	/**
	 * Obtener las palabras por serie.
	 *
	 * @return Las palabras por serie.
	 */
	public int getWordsForSerie() {
		return wordsForSerie;
	}
	
	/**
	 * Obtener el estado del jugador en la partida (gano / perdio).
	 *
	 * @return Obtener el estado del jugador en la partida.
	 */
	public Boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * Obtener el estado del jugador en cada serie (completo la serie / no completo la serie).
	 *
	 * @return El estado del jugador en cada serie.
	 */
	public Boolean getAccomplishedSerie() {
		return accomplishedSerie;
	}
	
	/**
	 * Configurar un nombre de usuario para el jugador.
	 *
	 * @param El nombre de usuario introducido por el jugador.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Configurar el numero de palabras adivinadas por serie.
	 *
	 * @param El numero de palabras adivinadas por serie.
	 */
	public void setGuessedWords(int guessedWords) {
		this.guessedWords = guessedWords;
	}
	
	/**
	 * Configurar el numero de palabras adivinadas en total.
	 *
	 * @param EL numero de palabras adivinadas en total.
	 */
	public void setAllRememberWords(int rememberWords) {
		this.allRememberWords = rememberWords;
	}
	
	/**
	 * Configurar el numero de palabras adivinadas en la partida actual.
	 *
	 * @param El numero de palabras adivinadas en la partida actual.
	 */
	public void setCurrentRememberWords(int rememberWords) {
		this.currentRememberWords = rememberWords;
	}
	
	/**
	 * Configurar el nivel actual del jugador.
	 *
	 * @param El nivel actual del jugador.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Configurar la serie actual del jugador.
	 *
	 * @param La serie actual del jugador.
	 */
	public void setSerie(int serie) {
		this.serie = serie;
	}
	
	/**
	 * Configurar el numero de palabras requeridas por serie.
	 *
	 * @param El numero de palabras requeridas por serie.
	 */
	public void setWordsForSerie(int wordsForSerie) {
		this.wordsForSerie = wordsForSerie;
	}
	
	/**
	 * Configurar el estado del jugador (gano / perdio).
	 *
	 * @param El estado del jugador en la partida.
	 */
	public void setGameOver(Boolean status) {
		this.gameOver = status;
	}
	
	/**
	 * Configurar el estado del jugador en la serie.
	 *
	 * @param El estado del jugador en la serie (completo la serie / no completo la serie).
	 */
	public void setAccomplishedSerie(Boolean status) {
		accomplishedSerie = status;
	}
}