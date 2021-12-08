

import java.util.LinkedList;
import java.util.Random;
public class Player {

	private LinkedList<Card> player1Deck = new LinkedList<Card>();
	private LinkedList<Card> player2Deck = new LinkedList<Card>();
	private LinkedList<Card> placedCards = new LinkedList<Card>();

	public Player(){ //constructor
		Deck deck = new Deck();
		for(int i = 0; i < 26; ++i){ //shuffling cards between players
			player1Deck.add(deck.deal());
		}
		for(int i = 0; i < 26; ++i){ //shuffling cards between players
			player2Deck.add(deck.deal());
		}
	}
	
	public LinkedList<Card> getPlayer1Deck(){ //accessor
		return player1Deck;
	}
	
	public LinkedList<Card> getPlayer2Deck(){ //accessor
		return player2Deck;
	}
	
	public LinkedList<Card> getPlacedCards(){
		return placedCards;
	}
	
	public void collect(LinkedList<Card> deck){ //adds first card of a deck to the pile
		placedCards.add(deck.getFirst());
	}
	
	public void flip(LinkedList<Card> deck){ //removes the first card of a deck
		deck.removeFirst();
	}
	
	public void player1Wins(){ //if player 1 wins a battle/war
		player1Deck.addAll(placedCards);
		placedCards.clear();
	}
	
	public void player2Wins(){ //if player 2 wins a battle/war
		player2Deck.addAll(placedCards);
		placedCards.clear();
	}
	
	public boolean hasWon(){ //checks if someone has all 52 cards or 0 cards
		if(player1Deck.size() == 52|| player2Deck.size() == 52){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean hasOneCard(){ //checks to see if someone has one card left
		if(player1Deck.size() == 1 || player2Deck.size() == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean hasThreeCards(){ //checks to see if someone has three cards left
		if(player1Deck.size() == 3 || player2Deck.size() == 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean hasTwoCards(){ //checks to see if someone has two cards left
		if(player1Deck.size() == 2 || player2Deck.size() == 2){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int war(){ //plays through war, throwing down 3 cards and flipping up the 4th. Takes in account if someone runs out of cards
		int warCounter = 0;
//		System.out.println("WAR");
		do{
			while(player1Deck.getFirst().getCardValue() == player2Deck.getFirst().getCardValue() && hasTwoCards() && hasOneCard() == false){ // a person has 2 cards left
//				System.out.println("TYPE 1");
					collect(player1Deck);
					collect(player2Deck);			
					flip(player1Deck);
					flip(player2Deck);
//					System.out.println("Player 1 = " + player1Deck.getFirst().getCardValue());
//					System.out.println("Player 2 = " + player2Deck.getFirst().getCardValue());
					warCounter++;
					break;
			}
			while(player1Deck.getFirst().getCardValue() == player2Deck.getFirst().getCardValue() && hasThreeCards() && hasOneCard() == false){ //a person has 3 cards left
//				System.out.println("TYPE 2");
				for(int i = 0; i < 2; ++i){
					collect(player1Deck);
					flip(player1Deck);
					collect(player2Deck);
					flip(player2Deck);
					if(hasOneCard()){
						break;
					}	
//					System.out.println("Player 1 = " + player1Deck.getFirst().getCardValue());
//					System.out.println("Player 2 = " + player2Deck.getFirst().getCardValue());
				}
				warCounter++;
			}
			while(player1Deck.getFirst().getCardValue() == player2Deck.getFirst().getCardValue() && hasTwoCards() == false && hasThreeCards() == false && hasOneCard() == false){ // more than 3 cards left
//				System.out.println("TYPE 3");
				for(int i = 0; i < 3; ++i){
					collect(player1Deck);
					flip(player1Deck);
					collect(player2Deck);
					flip(player2Deck);
					if(hasOneCard()){
						break;
					}
//					System.out.println("Player 1 = " + player1Deck.getFirst().getCardValue());
//					System.out.println("Player 2 = " + player2Deck.getFirst().getCardValue());
				}
				warCounter++;
		}
		}while(player1Deck.getFirst().getCardValue() == player2Deck.getFirst().getCardValue() && hasOneCard() == false);
		while(player1Deck.getFirst().getCardValue() == player2Deck.getFirst().getCardValue() && hasOneCard()){ // takes in account if on the last flip, and someone has 1 card left, to keep flipping the other persons deck until they dont match
//			System.out.println("TYPE 4");
			if(player2Deck.size() == 1){
				for(int i = 0; i < 3; ++i){
					collect(player1Deck);
					flip(player1Deck);
//					System.out.println("Player 1 = " + player1Deck.getFirst().getCardValue());
//					System.out.println("Player 2 = " + player2Deck.getFirst().getCardValue());
				}
			}else if(player1Deck.size() == 1){
				for(int i = 0; i < 3; ++i){
					collect(player2Deck);
					flip(player2Deck);
//					System.out.println("Player 1 = " + player1Deck.getFirst().getCardValue());
//					System.out.println("Player 2 = " + player2Deck.getFirst().getCardValue());
				}
			}
		}
		if(player1Deck.getFirst().getCardValue() > player2Deck.getFirst().getCardValue()){
			player1Wins();
//			System.out.println("Player 1 Wins" + "Size : " + getPlayer1Deck().size());
		}
		else if(player2Deck.getFirst().getCardValue() > player1Deck.getFirst().getCardValue()){
			player2Wins();
//			System.out.println("Player 2 Wins" + "Size : " + getPlayer2Deck().size());
		}
//		System.out.println("WAR END");
		return warCounter;
	}
	
	 //shuffler
	 public void shuffle(LinkedList<Card> list){ //I found that we had problems with game simulations being ran with 50+ games, and messed around and found a shuffle method that shuffles the cards every 52 games helps for some weird instance
		 Random random = new Random();
		 LinkedList<Card> shuffleDeck = new LinkedList<Card>();
		 for(int i = 0; i < list.size(); ++i){
			 shuffleDeck.add(list.remove(random.nextInt(list.size()))); //shuffles players cards
		 }
		 list.addAll(shuffleDeck); //re-adds shuffled deck
	 }
}