package Palabrejas;

import java.util.Map;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
	
	private static Map<String, Player> users = new HashMap<String, Player>();
	private ArrayList<String> userEnteredWords = new ArrayList<String>();
	private List<String> backendWords = new ArrayList<String>();
	private HandlerFile fileStream = new HandlerFile();
	private HandlerCsv csvStream = new HandlerCsv();
	private Player player = new Player();
	private int sizeWordsBackend = 0;
	private int timeLimit = 0;
	
	public Controller() {
		updateUserRecords();
	}
	
	public void updateUserRecords() {
		users = (recoveryMapPlayers() == null)? new HashMap<String, Player>() : recoveryMapPlayers();
	}
	
	public void updateStadisticsCurrentPlayer(Player DatabasePlayer) {
		player.setUserName(DatabasePlayer.getUserName());
		player.setAllRememberWords(DatabasePlayer.getAllRememberWords());
		player.setWordsForSerie(DatabasePlayer.getWordsForSerie());
		player.setLevel(DatabasePlayer.getLevel());
	}
	
	public String getStadistics() {
		return player.getStadistics();
	}
	
	public String getUserName() {
		return player.getUserName();
	}
	
	public int getGuessedWords() {
		return player.getGuessedWords();
	}
	
	public int getAllRememberWords() {
		return player.getAllRememberWords();
	}
	
	public int getCurrentRememberWords() {
		return player.getCurrentRememberWords();
	}
	
	public int getLevel() {
		return player.getLevel();
	}
	
	public int getSerie() {
		return player.getSerie();
	}
	
	public int getWordsForSerie() {
		return player.getWordsForSerie();
	}
	
	public int getSizeWordsBackend() {
		return sizeWordsBackend;
	}
	
	// solo debe hacerse al subir de nivel, no al cambiar de serie
	public int getTimeLimit() {
		return timeLimit;
	}
	
	public Boolean getGameOver() {
		return player.getGameOver();
	}
	
	public Boolean getAccomplishedSerie() {
		return player.getAccomplishedSerie();
	}
	
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
	
	public List<String> getWords() {
		
		try {
			backendWords = csvStream.getAllWordsFromCsvFile().subList(0, getWordsForSerie());
			System.out.println(backendWords.subList(0, getWordsForSerie())); //
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
	
	public String getUserEnteredWords() {
		String currentWords = "";
		for(String word : userEnteredWords) {
			currentWords += word.toUpperCase()+"\n";
		}
		return (currentWords.length() == 0)? "No hay palabras para mostrar" : currentWords;
	}
	
	public String getStadisticsAllPlayers() {
		
		String stadisticsAllPlayers = "";
		
		for (Player objUser : users.values()) {
			stadisticsAllPlayers += "\nUsername: " + objUser.getUserName() + "\nAll Remember Words: " + 
			objUser.getAllRememberWords() + "\nCurrent Remember Words: " + objUser.getCurrentRememberWords() + 
			"\nLevel: " + objUser.getLevel() + "\n";
		}
		
		return stadisticsAllPlayers;
	}
	
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
	
	public void setGuessedWords(int guessedWords) {
		player.setGuessedWords(guessedWords);
	}
	
	public void setAllRememberWords(int rememberWords) {
		player.setAllRememberWords(rememberWords);
	}
	
	public void setCurrentRememberWords(int rememberWords) {
		player.setCurrentRememberWords(rememberWords);
	}
	
	public void setLevel(int level) {
		player.setLevel(level);
	}
	
	public void setSerie(int serie) {
		player.setSerie(serie);
	}
	
	public void setWordsForSerie(int wordsForSerie) {
		player.setWordsForSerie(wordsForSerie);
	}
	
	public void setGameOver(Boolean status) {
		player.setGameOver(status);
	}
	
	public void setAccomplishedSerie(Boolean status) {
		player.setAccomplishedSerie(status);
	}
	
	public Boolean saveMapPlayers() {
		return fileStream.writeObjectFile(users);
	}
	
	public Map<String, Player> recoveryMapPlayers() {
		return fileStream.readObjectFile();
	}
	
	public Boolean isAvailableUsername(String userName) {
		Player player = users.get(userName);
		return (player == null)? true:false;
	}
	
	public void calculateTimeLimitWithLevel(int currentLevel) {
		timeLimit = ((((currentLevel + 1) * 60) + 1) * 1000);
	}
	
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
	
	public void validateGame() {
		compare(userEnteredWords, backendWords);
	}
	
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
	
	public void addWord(String word) {
		
		int limitWordsForSerie = getWordsForSerie();
		
		userEnteredWords.add(word);
		if(userEnteredWords.size() == limitWordsForSerie) {
			endSerie();
		}
	}
}