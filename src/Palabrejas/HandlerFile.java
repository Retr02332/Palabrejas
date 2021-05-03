package Palabrejas;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * La clase HandlerFile, tiene los atributos y metodos necesarios, para serializar y deserializar.
 */
public class HandlerFile {
	
	/** La ubicacion de la base de datos */
	private final String database = "src/Palabrejas/database.ser";
	private FileOutputStream fileOut;
	private FileInputStream fileIn;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	
	/**
	 * Serializar.
	 *
	 * @param mapPlayers, Un HashMap que contiene los usuarios registrados en el juego.
	 * @return Un booleano que indica si la operación se realizo con exito o no
	 */
	public Boolean writeObjectFile(Map mapPlayers) {
		
		try {
			fileOut = new FileOutputStream(database);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(mapPlayers);
			return true;
			
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
		finally {
			try {
				objectOut.close();
				fileOut.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	/**
	 * Deserializar.
	 *
	 * @return un HashMap que contiene los usuarios registrados en el juego
	 */
	public Map<String, Player> readObjectFile() {
		
		Map<String, Player> mapPlayers = null;
		
		try {
			fileIn = new FileInputStream(database);
			objectIn = new ObjectInputStream(fileIn);
			mapPlayers = (Map<String, Player>)objectIn.readObject();
			return mapPlayers;
		    
		} catch (IOException | ClassNotFoundException e) {
			//e.printStackTrace();
			return null;
		}
	}
}