package Palabrejas;

import com.opencsv.exceptions.CsvException;
import com.opencsv.CSVReader;

import java.util.Collections;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * La clase HandlerCsv, es una interfaz para poder capturar las palabras almacenadas en un archivo .csv.
 */
public class HandlerCsv {

    /**
     * Obtener todas las palabras de un archivo .csv.
     *
     * @return Todas las palabras de un archivo .csv
     * @throws IOException Se activa cuando hay un error de I/O.
     * @throws CsvException, Se activa cuando hay un problema al leer el archivo .csv
     */
    public List<String> getAllWordsFromCsvFile() throws IOException, CsvException {
    	
        String fileName = "src/Palabrejas/words.csv";
        List<String> wordsParsed = new ArrayList<String>();
        
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> words = reader.readAll();
            
            for(String[] listWords : words) {
            	for(int i=0; i<12; i++) {
            		wordsParsed.add(listWords[i]);
            	}
            }
            Collections.shuffle(wordsParsed);
            return wordsParsed;
        }
    }
}