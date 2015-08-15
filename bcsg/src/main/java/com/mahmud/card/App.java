package com.mahmud.card;

/*****
 * 	Farrukh Mahmud
 *  Looks like a Shelock Holmes novel I'm guessing A Study in Scarlet
 * 
 ****/

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mahmud.card.model.Card;
import com.mahmud.card.parser.IParser;
import com.mahmud.card.service.ICardService;


public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");

		if(args.length!=2)
		{
			System.out.println("Need to pass in path of file and true or false for descending");
			System.out.println("eg java -jar CardApp-0.0.1-SNAPSHOT.jar ./mid-test.csv true");
			System.exit(1);
		}
		
	
		
		App app = new App();
		app.startService(context, args[0],Boolean.parseBoolean(args[1]));
		
	}
	
	
	private void startService(ApplicationContext context, String file,boolean descending)
	{
		
 
		ICardService service = (ICardService) context.getBean("cardService");
		IParser parser = service.getParser();
		parser.setFile(file);
		List<Card> cards = service.processCards(descending);
		System.out.print(service.printCardDetails(cards));
		
	}
	
	
}
