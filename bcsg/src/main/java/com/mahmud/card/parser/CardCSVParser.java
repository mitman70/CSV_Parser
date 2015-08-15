package com.mahmud.card.parser;

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
	
	private String file;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy"); 

	@Override
	public void setFile(String file) {
		this.file = file;
		
	}

	@Override
	public ArrayList<Card> parse() {

		ArrayList<Card> cards = new ArrayList<Card>();


		CSVFormat format = CSVFormat.RFC4180.withDelimiter(',');

		CSVParser parser;
		try {
			parser = new CSVParser(new FileReader(getFile()), format);


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

	public String getFile() {
		return file;
	}

	public SimpleDateFormat getDt() {
		return dateFormat;
	}

	public void setDt(SimpleDateFormat dt) {
		this.dateFormat = dt;
	}

}
