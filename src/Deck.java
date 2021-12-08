

import java.util.LinkedList;
import java.util.Random;
public class Deck {
	
	private LinkedList<Card> deck;
	
	public Deck(){
		deck = new LinkedList<Card>();
		for(int i = 2; i <= 14; ++i){
			Card card = new Card(i);
			deck.add(card); //accounting 2 to Ace for diamonds
		}
		for(int i = 2; i <= 14; ++i){
			Card card = new Card(i);
			deck.add(card); //accounting for clubs
		}
		for(int i = 2; i <= 14; ++i){
			Card card = new Card(i);
			deck.add(card); //accounting for spades
		}
		for(int i = 2; i <= 14; ++i){
			Card card = new Card(i);
			deck.add(card); //accounting for hearts
		}
	}
	
	public Card deal(){ //returns a random card from the deck to shuffle
		Random random = new Random();
		Card card = deck.get(random.nextInt(deck.size()));
		deck.remove(card);
		return card;
	}

}