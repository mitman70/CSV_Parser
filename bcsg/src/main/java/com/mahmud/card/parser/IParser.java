package com.mahmud.card.parser;

import java.util.ArrayList;

import com.mahmud.card.model.Card;

public interface IParser {

	void setFile(String file);

	ArrayList<Card> parse();

}
