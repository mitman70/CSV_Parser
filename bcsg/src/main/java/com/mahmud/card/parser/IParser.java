package com.mahmud.card.parser;

import java.util.ArrayList;

import com.mahmud.card.model.Card;

public interface IParser {

	void setDir(String dir);

	ArrayList<Card> parse();

}
