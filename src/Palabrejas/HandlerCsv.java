package Palabrejas;

import com.opencsv.exceptions.CsvException;
import com.opencsv.CSVReader;

import java.util.Collections;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.List;

public class HandlerCsv {

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