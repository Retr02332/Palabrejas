package Palabrejas;

import java.util.Map;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La clase Controller, contiene toda la logica del juego.
 */
public class Controller {
	
	/** Un HashMap en donde se almacenaran todos los usuarios que ingresan al juego */
	private static Map<String, Player> users = new HashMap<String, Player>();
	
	/** Las palabras introducidas por el usuario */
	private ArrayList<String> userEnteredWords = new ArrayList<String>();
	
	/** Las palabras del backend. */
	private List<String> backendWords = new ArrayList<String>();
	
	/** Una interfaz de I/O para la base de datos. */
	private HandlerFile fileStream = new HandlerFile();
	
	/** Una interfaz de lectura para el archivo .csv. */
	private HandlerCsv csvStream = new HandlerCsv();
	
	/** El jugador */
	private Player player = new Player();
	
	/** El tamaño de las palabras de backend empleadas para la partida actual. */
	private int sizeWordsBackend = 0;
	
	/** El tiempo habil para ingresar las palabras memorizadas. */
	private int timeLimit = 0;
	
	/**
	 * Constructor de la clase controller.
	 */
	public Controller() {
		updateUserRecords();
	}
	
	/**
	 * Actualizar el HashMap de usuarios, con sus registros asociados de todos los usuarios en la base de datos.
	 */
	public void updateUserRecords() {
		users = (recoveryMapPlayers() == null)? new HashMap<String, Player>() : recoveryMapPlayers();
	}
	
	/**
	 * Actualizar las estadisticas del usuario actual.
	 *
	 * @param DatabasePlayer, el jugador asociado el jugador actual en la base de datos.
	 */
	public void updateStadisticsCurrentPlayer(Player DatabasePlayer) {
		player.setUserName(DatabasePlayer.getUserName());
		player.setAllRememberWords(DatabasePlayer.getAllRememberWords());
		player.setWordsForSerie(DatabasePlayer.getWordsForSerie());
		player.setLevel(DatabasePlayer.getLevel());
	}
	
	/**
	 * Obtener las estadisticas.
	 *
	 * @return Las estadisticas
	 */
	public String getStadistics() {
		return player.getStadistics();
	}
	
	/**
	 * Obtener el nombre de usuario.
	 *
	 * @return El nombre de usuario
	 */
	public String getUserName() {
		return player.getUserName();
	}
	
	/**
	 * Obtener las palabras adivinadas por serie.
	 *
	 * @return Las palabras adivinadas por serie.
	 */
	public int getGuessedWords() {
		return player.getGuessedWords();
	}
	
	/**
	 * Obtener todas las palabras recordadas.
	 *
	 * @return Todas las palabras recordadas por el usuario.
	 */
	public int getAllRememberWords() {
		return player.getAllRememberWords();
	}
	
	/**
	 * Obtener las palabras recordadas por el usuario en la partida actual.
	 *
	 * @return Las palabras recordadas por el usuario en la partida actual.
	 */
	public int getCurrentRememberWords() {
		return player.getCurrentRememberWords();
	}
	
	/**
	 * Obtener el nivel actual del usuario.
	 *
	 * @return El nivel actual del usuario.
	 */
	public int getLevel() {
		return player.getLevel();
	}
	
	/**
	 * Obtener la serie actual del usuario en la partida.
	 *
	 * @return La serie del usuario.
	 */
	public int getSerie() {
		return player.getSerie();
	}
	
	/**
	 * Obtener las palabras admitidas en la serie actual.
	 *
	 * @return Las palabras por serie.
	 */
	public int getWordsForSerie() {
		return player.getWordsForSerie();
	}
	
	/**
	 * Obtener le tamaño de las palabras obtenidas del backend.
	 *
	 * @return El tamaño de las palabras obtenidas del backend
	 */
	public int getSizeWordsBackend() {
		return sizeWordsBackend;
	}
	
	/**
	 * Obtener el tiempo limite disponible para introducir las palabras memorizadas.
	 *
	 * @return El tiempo limite disponible.
	 */
	// solo debe hacerse al subir de nivel, no al cambiar de serie
	public int getTimeLimit() {
		return timeLimit;
	}
	
	/**
	 * Obtener el estado del jugador en la partida (Gano / Perdio).
	 *
	 * @return El estado del jugador en la partida.
	 */
	public Boolean getGameOver() {
		return player.getGameOver();
	}
	
	/**
	 * Obtener el estado del jugador en la serie (completo la serie / no completo la serie).
	 *
	 * @return El estado del jugador en la serie.
	 */
	public Boolean getAccomplishedSerie() {
		return player.getAccomplishedSerie();
	}
	
	/**
	 * Obtener los requerimientos para avanzar de nivel.
	 *
	 * @return Los requerimientos para avnzar de nivel.
	 */
	public int getRequirementToAdvanceLevel() {
		int currentLevel = getLevel();
		int wordsForSerie = getWordsForSerie();
		
		if(currentLevel == 1) {
			return ( (wordsForSerie * 2) - currentLevel );
		}
		else {
			return ( (wordsForSerie * 2) - (currentLevel + 1) );
		}
	}
	
	/**
	 * Obtener un listado de palabras del backend, acorde al nivel actual.
	 *
	 * @return Una lista de palabras del backenkd para el nivel actual.
	 */
	public List<String> getWords() {
		
		try {
			backendWords = csvStream.getAllWordsFromCsvFile().subList(0, getWordsForSerie());
			//System.out.println(backendWords.subList(0, getWordsForSerie()));
			sizeWordsBackend = backendWords.size();
			return backendWords;
		}
		catch (IOException e) {
			//e.printStackTrace();
		}
		catch (CsvException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Obtener las palabras introducidas por el usuario.
	 *
	 * @return Las palabras introducidas por el usuario.
	 */
	public String getUserEnteredWords() {
		String currentWords = "";
		for(String word : userEnteredWords) {
			currentWords += word.toUpperCase()+"\n";
		}
		return (currentWords.length() == 0)? "No hay palabras para mostrar" : currentWords;
	}
	
	/**
	 * Obtener las estadisticas de todos los jugadores.
	 *
	 * @return las estadisticas de todos los jugadores.
	 */
	public String getStadisticsAllPlayers() {
		
		String stadisticsAllPlayers = "";
		
		for (Player objUser : users.values()) {
			stadisticsAllPlayers += "\nUsername: " + objUser.getUserName() + "\nAll Remember Words: " + 
			objUser.getAllRememberWords() + "\nCurrent Remember Words: " + objUser.getCurrentRememberWords() + 
			"\nLevel: " + objUser.getLevel() + "\n";
		}
		
		return stadisticsAllPlayers;
	}
	
	/**
	 * Configurar un nombre de usuario, elegido por el jugador.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		Boolean isValid = isAvailableUsername(userName);
		if(isValid) {
			player.setUserName(userName);
			users.put(userName, player);
		}
		else {
			updateStadisticsCurrentPlayer(users.get(userName));
		}
		calculateTimeLimitWithLevel(player.getLevel());
	}
	
	/**
	 * Configurar las palabras recordadas por serie.
	 *
	 * @param Las palabras recordadas por serie.
	 */
	public void setGuessedWords(int guessedWords) {
		player.setGuessedWords(guessedWords);
	}
	
	/**
	 * Configurar la cantidad de todas las palabras recordadas por el usuario.
	 *
	 * @param Todas las palabras recordadas por el usuario.
	 */
	public void setAllRememberWords(int rememberWords) {
		player.setAllRememberWords(rememberWords);
	}
	
	/**
	 * Configurar la cantidad de palabras recordadas por el usuario actualmente.
	 *
	 * @param Las cantidad de palabras recordadas por el usuario actualmente.
	 */
	public void setCurrentRememberWords(int rememberWords) {
		player.setCurrentRememberWords(rememberWords);
	}
	
	/**
	 * Configurar el nivel del jugador.
	 *
	 * @param El nivel del jugador.
	 */
	public void setLevel(int level) {
		player.setLevel(level);
	}
	
	/**
	 * Configurar la serie actual del jugador.
	 *
	 * @param La serie actual del jugador.
	 */
	public void setSerie(int serie) {
		player.setSerie(serie);
	}
	
	/**
	 * Configurar las palabras admitidas en la serie actual.
	 *
	 * @param Las palabras admitidas en la serie actual.
	 */
	public void setWordsForSerie(int wordsForSerie) {
		player.setWordsForSerie(wordsForSerie);
	}
	
	/**
	 * Configurar el estado del jugador en el juego (Gano / Perdio).
	 *
	 * @param El estado del jugador en el juego.
	 */
	public void setGameOver(Boolean status) {
		player.setGameOver(status);
	}
	
	/**
	 * Configurar el esatdo del jugador en la serie (completo la serie / no completo la serie).
	 *
	 * @param El estado del jugador en la serie.
	 */
	public void setAccomplishedSerie(Boolean status) {
		player.setAccomplishedSerie(status);
	}
	
	/**
	 * Serializar el HashMap que contiene los usuarios del juego.
	 *
	 * @return Un booleano que indica si la operación fue exitosa o no.
	 */
	public Boolean saveMapPlayers() {
		return fileStream.writeObjectFile(users);
	}
	
	/**
	 * Deserializar el archivo .ser para obtener de nuevo el HashMap con los usuario del juego.
	 *
	 * @return Un HashMap con los usuarios del juego.
	 */
	public Map<String, Player> recoveryMapPlayers() {
		return fileStream.readObjectFile();
	}
	
	/**
	 * Verificar si el nombre de usuario esta disponible.
	 *
	 * @param Un nombre de usuario a verificar
	 * @return Un booleano que indica si el nombre esta o no disponible
	 */
	public Boolean isAvailableUsername(String userName) {
		Player player = users.get(userName);
		return (player == null)? true:false;
	}
	
	/**
	 * Calcular el tiempo limite para introducir las palabras recordadas en base al nivel actual del jugador.
	 *
	 * @param El nivel actual del jugador.
	 */
	public void calculateTimeLimitWithLevel(int currentLevel) {
		timeLimit = ((((currentLevel + 1) * 60) + 1) * 1000);
	}
	
	/**
	 * Compare.
	 *
	 * @param Las palabras introducidas por el usuario
	 * @param Las palabras asignadas para la ronda en backend
	 */
	public void compare(ArrayList<String> userEnteredWords, List<String> backendWords) {
		
		for(String word : userEnteredWords) {
			for(String backendWord : backendWords) {
				if(word.equalsIgnoreCase(backendWord)) {
					setCurrentRememberWords(getCurrentRememberWords() + 1);
					setAllRememberWords(getAllRememberWords() + 1);
					setGuessedWords(getGuessedWords() + 1);
					break;
				}
			}
		}
	}
	
	/**
	 * Validar el juego, comparando las palabras introducidas por el usuario y las palabras asignadas en backend.
	 */
	public void validateGame() {
		compare(userEnteredWords, backendWords);
	}
	
	/**
	 * Todas las operaciones a nivel de backend que se debe realizar cada vez que se finaliza una serie.
	 */
	public void endSerie() {
		
		int serie = getSerie();
		int currentLevel = getLevel();
		int currentWordsForSerie = getWordsForSerie();
		int requirementToAdvanceLevel = getRequirementToAdvanceLevel();
		
		validateGame();

		if(serie == 2) {
			setSerie(1);
			if(getGuessedWords() >= requirementToAdvanceLevel) {
				if(getLevel() != 5) {
					setLevel(currentLevel + 1);
					setWordsForSerie(currentWordsForSerie + 2);
					calculateTimeLimitWithLevel(getLevel());
				}
				users.replace(getUserName(), player);
				setAccomplishedSerie(true);
				setGuessedWords(0);
				userEnteredWords.clear();
			}
			else {
				users.replace(getUserName(), player);
				setGameOver(true);
				saveMapPlayers();
			}
		}
		else {
			setSerie(2);
			setAccomplishedSerie(true);
			calculateTimeLimitWithLevel(currentLevel);
			userEnteredWords.clear();
		}
	}
	
	/**
	 * Agregar las palabras introducidas por el usuario a una lista interna en el backend.
	 *
	 * @param La palabra introducida por el usuario.
	 */
	public void addWord(String word) {
		
		int limitWordsForSerie = getWordsForSerie();
		
		userEnteredWords.add(word);
		if(userEnteredWords.size() == limitWordsForSerie) {
			endSerie();
		}
	}
}