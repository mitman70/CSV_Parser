package com.mahmud.card.service;

import java.util.List;

import com.mahmud.card.model.Card;
import com.mahmud.card.parser.IParser;

public interface ICardService {
	
	public List<Card> processCards(boolean ascending);
	public IParser getParser();
	public String printCardDetails(List<Card> cards);

}
