

public class Game {

	private int battles;
	private int wars;
	private int warsDub;
	
	public Game(){
		this.battles = battles;
		this.wars = wars;
		this.warsDub = warsDub;
	}
	
	public void play(){
		Player play = new Player();
		battles = 0;
		wars = 0;
		warsDub = 0;
		play.shuffle(play.getPlayer1Deck());
		play.shuffle(play.getPlayer2Deck());
		while(play.hasWon() == false){
			if(battles % 52 == 0 && battles > 0){
				play.shuffle(play.getPlayer1Deck());
				play.shuffle(play.getPlayer2Deck());
			}
//			System.out.println("Player 1 = " + play.getPlayer1Deck().getFirst().getCardValue());
//			System.out.println("Player 2 = " + play.getPlayer2Deck().getFirst().getCardValue());
			if(play.getPlayer1Deck().getFirst().getCardValue() == play.getPlayer2Deck().getFirst().getCardValue()){
				wars += play.war();
				if(play.war() == 2){
					warsDub++;
				}
			}else if(play.getPlayer1Deck().getFirst().getCardValue() > play.getPlayer2Deck().getFirst().getCardValue()){
				
				battles++;
				play.collect(play.getPlayer1Deck());
				play.collect(play.getPlayer2Deck());
				play.flip(play.getPlayer1Deck());
				play.flip(play.getPlayer2Deck());
				play.player1Wins();
//				System.out.println("Player 1 Wins" + "Size : " + play.getPlayer1Deck().size());
			}
			else{
				battles++;
				play.collect(play.getPlayer1Deck());
				play.collect(play.getPlayer2Deck());
				play.flip(play.getPlayer1Deck());
				play.flip(play.getPlayer2Deck());
				play.player2Wins();
//				System.out.println("Player 2 Wins" + "Size : " + play.getPlayer2Deck().size());
					
			}
		}
		play.getPlacedCards().clear();
		play.getPlayer1Deck().clear();
		play.getPlayer2Deck().clear();
//		System.out.println("Game Over");
	}
	
	public int getBattles(){
		return battles;
	}
	
	public int getWars(){
		return wars;
	}
	
	public int getWarsDub(){
		return warsDub;
	}
}