package Palabrejas;

import java.io.Serializable;

public class Player implements Serializable {
	
	private boolean accomplishedSerie = false;
	private boolean gameOver = false;
	private String userName = "";
	private int currentRememberWords = 0; // Numero de palabras adivinadas en  la partida actual
	private int allRememberWords = 0;     // Numero de palabras adivinadas en  total
	private int guessedWords  = 0;        // Numero de palabras adivinadas por serie
	private int wordsForSerie = 4;
	private int level = 1;
	private int serie = 1;
	
	public String getStadistics() {
		return "<html><body><br>Nombre del jugador: "+userName+"<br>Palabras recordadas: "+
				currentRememberWords+"<br>Nivel: "+level+"<br><br></body></html>";
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getGuessedWords() {
		return guessedWords;
	}
	
	public int getAllRememberWords() {
		return allRememberWords;
	}
	
	public int getCurrentRememberWords() {
		return currentRememberWords;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getSerie() {
		return serie;
	}
	
	public int getWordsForSerie() {
		return wordsForSerie;
	}
	
	public Boolean getGameOver() {
		return gameOver;
	}
	
	public Boolean getAccomplishedSerie() {
		return accomplishedSerie;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setGuessedWords(int guessedWords) {
		this.guessedWords = guessedWords;
	}
	
	public void setAllRememberWords(int rememberWords) {
		this.allRememberWords = rememberWords;
	}
	
	public void setCurrentRememberWords(int rememberWords) {
		this.currentRememberWords = rememberWords;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setSerie(int serie) {
		this.serie = serie;
	}
	
	public void setWordsForSerie(int wordsForSerie) {
		this.wordsForSerie = wordsForSerie;
	}
	
	public void setGameOver(Boolean status) {
		this.gameOver = status;
	}
	
	public void setAccomplishedSerie(Boolean status) {
		accomplishedSerie = status;
	}
}