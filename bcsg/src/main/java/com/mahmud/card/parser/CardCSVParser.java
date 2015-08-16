package com.mahmud.card.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.mahmud.card.model.Card;
import com.mahmud.card.utils.CardUtils;

@Component
public class CardCSVParser implements IParser {
	
	private String dir;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy"); 

	@Override
	public void setDir(String dir) {
		this.dir = dir;
		
	}
	
	
	@Override
	public ArrayList<Card> parse()
	{
		return processFiles(dir);
	}
	
	
	private ArrayList<Card> processFiles(String directoryName){

        File directory = new File(directoryName);
        ArrayList<Card> cards = new ArrayList<Card>();
        
        File[] fList = directory.listFiles();

        for (File file : fList){
            if (file.isFile()&&file.getName().endsWith("csv")){
                System.out.println("Processing "+file.getName());
                cards.addAll(processFile(directoryName+File.separator+file.getName()));
            }
        }
        
        return cards;
    }
	
	
	

	
	private ArrayList<Card> processFile(String file) {

		ArrayList<Card> cards = new ArrayList<Card>();


		CSVFormat format = CSVFormat.RFC4180.withDelimiter(',');

		CSVParser parser;
		try {
			parser = new CSVParser(new FileReader(file), format);


			List<Card> emps = new ArrayList<Card>();
			for(CSVRecord record : parser){
				Card card = new Card();
			
				card.setBankName(record.get(0));
				card.setCardNo(record.get(1));
				card.setExpiryDate(CardUtils.createExpiryDate(dateFormat, record.get(2)));
				cards.add(card);
			}
			//close the parser
			parser.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cards;

	}

	public String getDir() {
		return dir;
	}

	public SimpleDateFormat getDt() {
		return dateFormat;
	}

	public void setDt(SimpleDateFormat dt) {
		this.dateFormat = dt;
	}

}
